package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl
import library._
import application.URLFiltres
import scala.annotation.varargs

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

  def analyser(phrase: String): List[(String, String)] = {
    // on enleve les accents :
    val sans_accents = BDDImpl.removeAccents(phrase)
    // print("sans_accents : " + sans_accents + " ; ")
    // on enleve les mots de liaisons :
    val sans_liaison = assembler(filtreLiaison(decouper(sans_accents)))
    // print("sans_liaison : " + sans_liaison + " ; ")
    // on enleve les mots de recherche et de politesse :
    val sans_rech_poli = filtrePolitesseRecherche(sans_liaison)
    // print("sans_rech_poli : " + sans_rech_poli + " ; ")
    // a ce stade, on cherche "tnb" et "hotel ville" :
    val exceptions = List(("hotel ville","mairie"),("tnb","tnb"))
    var correction = assembler(FautesImpl.correction(decouper(sans_rech_poli),List("hotel","ville","tnb")))
    for (elem <- exceptions){ 
      if (correction.contains(elem._1)){
        return List((BDDImpl.chercherLieu(elem._2), BDDImpl.chercherAdresse(elem._2)))
      }
    }
    val langue = LangueImpl.getLangueActuelle()
    val dicoExpr = BDDImpl.getDicoExpr()(langue)(8).split(",")
    correction =  FautesImpl.correction(correction.split(" ").toList, dicoExpr.toList).mkString(" ")
    println("corrrrrr : "+correction)
    if (correction.contains(dicoExpr(0)) || correction.contains(dicoExpr(1)) || correction.contains(dicoExpr(2))) {
      var url = ""
      
      correction match {
        case _ if correction.contains(dicoExpr(0)) =>
          var restaurant = correction.split(dicoExpr(0))(1).replaceAll(" ", "+")
          url = s"https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=$restaurant"
        case _ if correction.contains(dicoExpr(1)) =>
          val creperie = correction.split(dicoExpr(1))(1).trim.replaceAll(" ", "+")
          url = s"https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=$creperie"
        case _ if correction.contains(dicoExpr(2))=>
          val pizzeria = correction.split(dicoExpr(2))(1).trim.replaceAll(" ", "+")
          url = s"https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=$pizzeria"
      }
      println(url)
      val leHtml : Html = OutilsWebObjet.obtenirHtml(url)
      return List(getAdressFromHtml(leHtml))
    }

    // on selectionne des mots a corriger qui ne sont pas dans la bdd :
    val mots_a_corriger = decouper(sans_rech_poli).filter(mot => !liste_mots_bdd_xml.contains(mot.toLowerCase()))
    // print("mots_a_corriger : " + mots_a_corriger + " ; ")
    // on stocke les autres mots sans les corriger :
    val mots_a_garder = decouper(sans_rech_poli).filter(mot => liste_mots_bdd_xml.contains(mot.toLowerCase()))
    // print("mots à garder : " + mots_a_garder + " ; ")
    // on corrige ce qu'il faut corriger :
    val liste_mots_corriges = FautesImpl.correction(mots_a_corriger, BDDImpl.recuplieuxBases ++ liste_mots_bdd_xml)
    // on concatene les mots corrigés et gardés séparés par des espaces :
    val requete_corrigee = assembler(mots_a_garder ++ liste_mots_corriges)
    // println("requete corrigée : " + requete_corrigee)
    // Cas où l'on demande l'adresse directement, sans aucun mot supplémentaire :
    if(BDDImpl.chercherAdresse(requete_corrigee) != "Adresse non trouvée") {
      return List((BDDImpl.chercherLieu(requete_corrigee), BDDImpl.chercherAdresse(requete_corrigee)))
    }
    // on recupere la liste des lieux potentiels :
    val lieux = analyserList(decouper(requete_corrigee))
    // étude de cas en fonction du nombre de lieux trouvés :
    lieux match {
      case Nil => Nil
      case head :: Nil =>
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") {
          List((head, BDDImpl.chercherAdresse(head)))
        } else Nil
      case head :: next =>
        val groupedLieux = lieux.groupBy(identity)
        val maxOccurences = groupedLieux.values.map(_.length).max
        val lieux_les_plus_courants = groupedLieux.filter(_._2.length == maxOccurences).keys.toList.sorted
        lieux_les_plus_courants.map{case lieu => (lieu,BDDImpl.chercherAdresse(lieu))}
    }
  }

  def getAdressFromHtml(html : Html) : (String,String) = {
    val objFiltrageUrls:URLFiltres = new URLFiltres
    val cleanhtml = objFiltrageUrls.filtreAnnonce(html)
    val htmldeladresse = OutilsWebObjet.obtenirHtml("https://www.linternaute.com/" + cleanhtml(0))
    return (objFiltrageUrls.filtreAnnonceNom(htmldeladresse)(0),objFiltrageUrls.filtreAnnonceAdresse(htmldeladresse)(0))
  }

  def filtreLiaison(requete: List[String]): List[String] = {
    val liaisons = List("se", "de", "des", "du", "d", "le", "la", "les", "l", "un", "une", "et", "je", "for")
    requete.filter(mot => !liaisons.contains(mot.toLowerCase())).filter(_.length > 1)
  }

  def filtrePolitesseRecherche(requete: String): String = {
    val dicoUniversel = BDDImpl.getDicoPRN().flatten
    val requete_corrigee = FautesImpl.correction(decouper(requete),dicoUniversel)
    assembler(requete_corrigee.filter(mot => !dicoUniversel.contains(mot.toLowerCase()) && !mot.toLowerCase().equals("rennes")))
  }
  
  def analyserList(requete: List[String]): List[String] = {
    requete match {
      case Nil => Nil
      case head :: next => quiContient(head, BDDImpl.xmlListLieu) ++ analyserList(next)
    }
  }

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

  def decouper(phrase: String): List[String] = phrase.split("[ .!?,;'()]+").toList

  def assembler(list: List[String]): String = {
    if (list.isEmpty) return ""
    else list.reduce(_ + " " + _)
  }

  def analyserChoix(requete: String): Option[Int] = {
    val requete_decoupee = decouper(requete)
    print("requete_decoupee : " + requete_decoupee + " ; ")
    val nombre_only = requete_decoupee.map(mot => mot.replaceAll("\\D","")).filter(_!="")
    println("nombre_only : " + nombre_only)
    nombre_only match {
      case Nil => None
      case head :: Nil => 
        try {
          Some(head.toInt)
        }
        catch {
          case _: NumberFormatException => None
        } 
      case head :: next => None
    }
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
