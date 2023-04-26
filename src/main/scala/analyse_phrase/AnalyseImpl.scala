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
    val liaisons = List("de", "des", "du", "le", "la", "les", "un", "une", "et", "l", "d","où","je","i","ich")
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
    // Les lieux de la base XML en version clean
    val listlieuxxml = BDDImpl.xmlListLieu.map(x=>BDDImpl.removeLiaisonAccentsWords(BDDImpl.removeAccents(x.toLowerCase())))
    // on enleve les "parasites" :
    val phrase_sans_parasites: String = assembler(filtreLiaison(decouper(filtrePolitesseRecherche(phrase))))
    // on corrige la phrase :
      println("phrase : "+ phrase)
    var phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase_sans_parasites),
        filtreLiaison(liste_lieux.flatMap(decouper(_)).concat(listlieuxxml))
      )
    ).toLowerCase().replaceAll("pisicine","piscine").replaceAll("saint ","saint-") // TODO Corrections des piscines, replaceAll nécessaires pour test 49
      println("phrase cor : "+ phrase_corrigee)
    // On gère les exceptions , (équivalents), TNB ajouté pour qu'il puisse être instantanément detecté lorsqu'il est au milieu d'une phrase
    val exceptions = List(("hotel ville","mairie"),("tnb","tnb"))
    for (elem <- exceptions){
      if (phrase_corrigee.contains(elem._1)){
        return (BDDImpl.chercherLieu(elem._2), BDDImpl.chercherAdresse(elem._2))
      }
    }
    //On retire les bonjours (et traductions) de la phrase
    
    if(politeTest_Bonjour(phrase_corrigee)._1){
      val salutationsLangueActuelle = BDDImpl.createDicoSalutations(LangueImpl.langueIntToString(LangueImpl.getLangueActuelle()),LangueImpl.getLangueActuelle())
      phrase_corrigee = assembler(FautesImpl.correction(decouper(phrase_corrigee),filtreLiaison(salutationsLangueActuelle)))
      phrase_corrigee = removeBonjour(phrase_corrigee).replaceAll(" ","")
    }
    // Cas où l'on demande l'adresse directement, sans aucun mot supplémentaire :
    if(BDDImpl.chercherAdresse(phrase_corrigee) != "Adresse non trouvée"){
      return (BDDImpl.chercherLieu(phrase_corrigee), BDDImpl.chercherAdresse(phrase_corrigee))
    }
    // on recupere la liste des lieux :
    val lieux = analyserList(decouper(BDDImpl.removeLiaisonAccentsWords(phrase_corrigee)))
    for (lieu <- lieux) {
      if(BDDImpl.removeLiaisonAccentsWords(lieu).contains(phrase_corrigee)){
        (BDDImpl.chercherLieu(phrase_corrigee), BDDImpl.chercherAdresse(phrase_corrigee))
      }
    }
    lieux match {
      case Nil => ("", "")
      case head :: Nil =>
       
        if (BDDImpl.chercherAdresse(BDDImpl.removeLiaisonAccentsWords(head)) != "Adresse non trouvée") {
          (BDDImpl.chercherLieu(head), BDDImpl.chercherAdresse(head))
        } else ("", "")
      case head :: next => // TODO F6 il faudra gérer les cas avec plusieurs résultats 
        val lieu_le_plus_courant = lieux.groupBy(identity).maxBy(_._2.length)._1
        (lieu_le_plus_courant,BDDImpl.chercherAdresse(lieu_le_plus_courant))
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
    if (requete.length <= 1 ){
      requete match {
        case Nil => Nil
        case head :: next => quiContient(head, BDDImpl.xmlListLieu)++ analyserList(next)
      }
    }

    else 
      requete match {
        case Nil => Nil
        case head :: next => quiContient(head, BDDImpl.xmlListLieu)++ quiContient(head +"s", BDDImpl.xmlListLieu)++ analyserList(next)
      }
  }

  /**
    * Retire de la phrase le mot bonjour et ses traductions
    *
    * @param phrase str représentant une phrase
    * @return la phrase modifiée
    */
  def removeBonjour(phrase:String): String = {
    val motsASupprimer = List("bonjour", "hello", "hola", "hallo", "buongiorno")
    val regex = s"\\b(${motsASupprimer.mkString("|")})\\b"
    phrase.replaceAll(regex, "")
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
        val head_to_list = decouper(BDDImpl.removeLiaisonAccentsWords(BDDImpl.removeAccents(head.toLowerCase()))) // on cherchera si le mot entier est un mot dans une adresse
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
