package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

case object ExceptionListeVide extends Exception

object AnalyseImpl extends AnalyseTrait {

  //Recherche Adresse

  /*
    on va stocker dans 'liste_mots_bdd_xml' la liste des mots qui composent les lieux de la bdd xml
    - sans accents
    - en minuscules
    - sans doublons (.distinct)
    - en séparant les mots comme 'saint-georges' en 2 mots distincts 'saint' et 'georges'
    .flatten sert à transformer une List[List[String]] en List[String]
  */
  private val tmp = BDDImpl.xmlListLieu.map(l => AnalyseImpl.decouper(BDDImpl.removeAccents(l.toLowerCase()))).flatten.distinct
  val liste_mots_bdd_xml = tmp.map(mot => mot.split("-").toList).flatten.distinct

  def analyser(phrase: String): (String, String) = {
    // on enleve les accents :
    val sans_accents = BDDImpl.removeAccents(phrase)
    print("sans_accents : " + sans_accents + " ; ")
    // on enleve les mots de liaisons :
    val sans_liaison = assembler(filtreLiaison(decouper(sans_accents)))
    print("sans_liaison : " + sans_liaison + " ; ")
    // on enleve les mots de recherche et de politesse :
    val sans_rech_poli = filtrePolitesseRecherche(sans_liaison)
    print("sans_rech_poli : " + sans_rech_poli + " ; ")
    // a ce stade, on cherche "tnb" et "hotel ville" :
    val exceptions = List(("hotel ville","mairie"),("tnb","tnb"))
    val correction = assembler(FautesImpl.correction(decouper(sans_rech_poli),List("hotel","ville","tnb")))
    for (elem <- exceptions){ 
      if (correction.contains(elem._1)){
        return (BDDImpl.chercherLieu(elem._2), BDDImpl.chercherAdresse(elem._2))
      }
    }
    // on selectionne des mots a corriger qui ne sont pas dans la bdd :
    val mots_a_corriger = decouper(sans_rech_poli).filter(mot => !liste_mots_bdd_xml.contains(mot.toLowerCase()))
    print("mots_a_corriger : " + mots_a_corriger + " ; ")
    // on stocke les autres mots sans les corriger :
    val mots_a_garder = decouper(sans_rech_poli).filter(mot => liste_mots_bdd_xml.contains(mot.toLowerCase()))
    print("mots à garder : " + mots_a_garder + " ; ")
    // on corrige ce qu'il faut corriger : // TODO il faut cacher un peu qu'on met gare au début non ?
    val liste_mots_corriges = FautesImpl.correction(mots_a_corriger, BDDImpl.recuplieuxBases ++ liste_mots_bdd_xml)
    // on concatene les mots corrigés et gardés séparés par des espaces :
    val requete_corrigee = assembler(mots_a_garder ++ liste_mots_corriges)
    println("requete corrigée : " + requete_corrigee)
    // Cas où l'on demande l'adresse directement, sans aucun mot supplémentaire :
    if(BDDImpl.chercherAdresse(requete_corrigee) != "Adresse non trouvée") {
      return (BDDImpl.chercherLieu(requete_corrigee), BDDImpl.chercherAdresse(requete_corrigee))
    }
    // on recupere la liste des lieux potentiels :
    val lieux = analyserList(decouper(requete_corrigee))
    // étude de cas en fonction du nombre de lieux trouvés :
    lieux match {
      case Nil => ("", "")
      case head :: Nil =>
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") {
          (head, BDDImpl.chercherAdresse(head))
        } else ("", "")
      case head :: next => // TODO F6 il faudra gérer les cas avec plusieurs résultats 
        val lieu_le_plus_courant = lieux.groupBy(identity).maxBy(_._2.length)._1
        (lieu_le_plus_courant,BDDImpl.chercherAdresse(lieu_le_plus_courant))
    }
  }

  /** permet de retirer les mots de liaisons de phrase sous formes de liste de string
    *  @param requete sous forme de liste de string
    *  @return la phrase sous forme de liste de string sans les mots de liaisons
    */
  def filtreLiaison(requete: List[String]): List[String] = {
    val liaisons = List("se", "de", "des", "du", "d", "le", "la", "les", "l", "un", "une", "et", "je", "for")
    requete.filter(mot => !liaisons.contains(mot.toLowerCase()))
  }

  /**
    * enleve de la requete du user tous les mots de Recherche ou de Politesse (on enleve "Rennes" aussi)
    *
    * @param requete la requete du user (String)
    * @return la requete sans les mots de Recherche ou de Politesse
    */
  def filtrePolitesseRecherche(requete: String): String = {
    val dicoUniversel = BDDImpl.getDicoPRN().flatten
    val requete_corrigee = FautesImpl.correction(decouper(requete),dicoUniversel)
    assembler(requete_corrigee.filter(mot => !dicoUniversel.contains(mot.toLowerCase()) && !mot.toLowerCase().equals("rennes")))
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
        // on cherchera si mot est bien un mot complet dans l'adresse :
        // (on prend la premiere adresse, on lui enleve les parasites, on le met en min, on remplace les "-" par des " ")
        val head_to_list = decouper(BDDImpl.removeLiaisonAccentsWords(BDDImpl.removeAccents(head.toLowerCase()).replace("-"," ")))
        if (head_to_list.contains(mot)) head :: quiContient(mot, next)
        else quiContient(mot, next)
    }
  }

  /**
    * découpe un string en liste de mots
    *
    * @param phrase qui est le string à découper
    * @return une liste de string représentant la phrase decoupee
    */
  def decouper(phrase: String): List[String] = phrase.split("[ .!?,;']+").toList

  /** 
    *  assemble une liste de mot (string) pour former une phrase sous forme de string avec un espace entre chaque mot
    * 
    *  @param list une list de mot
    *  @return un string qui sera la phrase décrit par les éléments de départ
    */
  def assembler(list: List[String]): String = {
    if (list.isEmpty) return ""
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
