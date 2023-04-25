package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

case object ExceptionListeVide extends Exception

object AnalyseImpl extends AnalyseTrait {

  //Recherche Adresse

  val liste_lieux = BDDImpl
    .recupLieux(Source.fromFile("doc/DonneesInitiales.txt"))
    .concat(List("TNB", "Hotel de Ville"))
  val listeAvecLiaison = liste_lieux.map(decouper(_))

  /** permet de retirer les mots de liaisons de phrase sous formes de liste de string
    *  @param requete sous forme de liste de string
    *  @return la phrase sous forme de liste de string sans les mots de liaisons
    */
  def filtreLiaison(requete: List[String]): List[String] = {
    val liaisons = List("de", "des", "du", "le", "la", "les", "un", "une", "et", "l", "d")
    requete.filter(mot => !liaisons.contains(mot.toLowerCase()))
  }

  /**
    * enleve de la requete du user tous les mots de Recherche ou de Politesse (on enleve "Rennes" aussi)
    *
    * @param requete la requete du user (String)
    * @return la requete sans les mots de Recherche ou de Politesse
    */
  def filtrePolitesseRecherche(requete: String): String = {
    val dicoRecherche = BDDImpl.createDicoRecherche("français",0)
    val dicoPolitesse = BDDImpl.createDicoSalutations("français",0)
    assembler(decouper(requete).filter(mot => !dicoPolitesse.contains(mot.toLowerCase()) && !dicoRecherche.contains(mot.toLowerCase()) && !mot.toLowerCase().equals("rennes")))
  }

  def analyser(phrase: String): (String, String) = {
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        filtreLiaison(liste_lieux.flatMap(decouper(_)))
      )
    )
    // Cas où l'on demande l'adresse directement , sans aucun mot supplémentaire
    if(BDDImpl.chercherAdresse(phrase_corrigee) != "Adresse non trouvée"){
      return (BDDImpl.chercherLieu(phrase_corrigee), BDDImpl.chercherAdresse(phrase_corrigee))
    }
    val mots = decouper(phrase_corrigee)
    mots match {
      case Nil => ("", "")
      case head :: Nil =>
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") {
          (BDDImpl.chercherLieu(head), BDDImpl.chercherAdresse(head))
        } else ("", "")
      case head :: next => {
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") {
          (BDDImpl.chercherLieu(head), BDDImpl.chercherAdresse(head))
        } else return analyser(next.mkString(" "))
      }
    }
  }

  /**
    * on cherche tous les lieux que cherche le user dans sa requete en regardant chaque mot un à un
    * attention : les filtres anti parasites sont déjà appliqués
    *
    * @param requete la requete du user en List[String]
    * @return la liste des lieux
    */
  def analyserList(requete: List[String]): List[String] = {
    requete match {
      case Nil => Nil
      case head :: next => quiContient(head, BDDImpl.xmlListLieu) ++ analyserList(next)
    }
  }

  /** 
    * cherche tous les lieux de la bdd qui contiennent le mot passé en param
    * 
    * @param mot le mot que l'on cherche dans la base de données
    * @param list, la liste des lieux de vAr.xml (à Rennes et ayant une adresse)
    * @return une liste de string représentant la liste des lieux contenant mot
    */
  def quiContient(mot: String, list: List[String]): List[String] = {
    list match {
      case Nil => Nil
      case head :: next => 
        val head_to_list = decouper(head.toLowerCase()) // on cherchera si le mot entier est un mot dans une adresse
        if (head_to_list.contains(mot.toLowerCase())) head :: quiContient(mot, next)
        else quiContient(mot, next)
    }
  }

  /**
    * découpe un string en plusieurs string éléments d'une liste de string 
    *
    * @param phrase qui est le string à découper
    * @return une liste de string représentant la phrase
    */
  def decouper(phrase: String): List[String] = phrase.split("[ .!?,;']+").toList

  // XXX fonction non utilisée :
  def separatewords(mots: List[String]): List[String] = {
    mots match {
      case head :: next => head.split(" ").toList ::: separatewords(next)
      case Nil          => Nil
    }
  }

  /** 
    *  assemble une liste de mot (string) pour former une phrase sous forme de string avec un espace entre chaque mot
    * 
    *  @param list une list de mot
    *  @return un string qui sera la phrase décrit par les éléments de départ
    */
  def assembler(list: List[String]): String = {
    if (list.isEmpty) throw ExceptionListeVide
    else list.reduce(_ + " " + _)
  }

  //Analyse politesse

  def politeTest_Bonjour(phrase: String): (Boolean,String) = {
    val salutationsLangueActuelle = BDDImpl.createDicoSalutations(LangueImpl.langueIntToString(LangueImpl.getLangueActuelle()),LangueImpl.getLangueActuelle())
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        filtreLiaison(salutationsLangueActuelle)
      )
    )
    var testBonjour = false
    var i = 0
    while (i < salutationsLangueActuelle.length && !testBonjour){
      if(phrase_corrigee.toLowerCase().contains(salutationsLangueActuelle(i))) {testBonjour = true}
      i += 1
    }
    (testBonjour,salutationsLangueActuelle(0))
  }

  def politeTest_OnlyBonjour(phrase: String): (Boolean,String) = {
    val salutationsLangueActuelle = BDDImpl.createDicoSalutations(LangueImpl.langueIntToString(LangueImpl.getLangueActuelle()),LangueImpl.getLangueActuelle())
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        filtreLiaison(salutationsLangueActuelle)
      )
    )
    var testOnlyBonjour = false
    var i = 0
    while (i < salutationsLangueActuelle.length && !testOnlyBonjour){
      if(phrase_corrigee.toLowerCase().equals(salutationsLangueActuelle(i))) {testOnlyBonjour = true}
      i += 1
    }
    (testOnlyBonjour,salutationsLangueActuelle(0))
  }

  // Analyse Langue

  def getDicoLangue(): List[String] = {
    val dicoExpr = BDDImpl.getDicoExpr
    val langue_actuelle = LangueImpl.getLangueActuelle
    dicoExpr(langue_actuelle).filter(_ != LangueImpl.langueIntToString(langue_actuelle))
  }

  /** meme chose que getDicoLangue à la difference qu'ici on peut choisir le dictionnaire de la langue que l'on veut
    *
    * @param lang un int compris entre 0 et 4 correspondant à la langue voulue
    * @return le dico de la langue choisie, renvoie une liste vide si le int n'est pas compris entre 0 et 4
    */
  def getDicoLangue(lang: Int): List[String] = {
    lang match {
      case n if (lang >= 0 && lang <= 4) =>
        val dicoExpr = BDDImpl.getDicoExpr
        dicoExpr(lang).filter(_ != LangueImpl.langueIntToString(lang))
      case _ => List()
    }
  }

  def detecLangue(phrase: String): (Boolean, Int, Int) = {
    detecLangue(decouper(phrase))
  }

  private def detecLangue(phrase: List[String]): (Boolean, Int, Int) = {
    var phrase_corrigee = FautesImpl.correction(filtreLiaison(phrase), List("français","english","español","deutsch","italiano"))
    phrase_corrigee match {
      case Nil => (false, LangueImpl.getLangueActuelle(), LangueImpl.getLangueActuelle())
      case head :: next =>
        val langue = BDDImpl.langueDuMot(head)
        langue match {
          case "langue non détectée" => detecLangue(next)
          case _ => (true, LangueImpl.langueStringToInt(langue), LangueImpl.getLangueActuelle())
        }
    }
  }

}
