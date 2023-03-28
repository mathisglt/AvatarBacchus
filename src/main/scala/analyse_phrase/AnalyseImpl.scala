package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl
import tolerance_fautes.FautesImpl

object AnalyseImpl extends AnalyseTrait {

  val liste_lieux = BDDImpl.recupLieux(Source.fromFile("doc/DonneesInitiales.txt")).toList
  
  val listeAvecLiason = liste_lieux.map(decouper(_))

  def filtreLiaison(listeLieu : List[String]): List[String] = {
  val liaisons = List("de", "la")
  listeLieu.filter(listeLieu => !liaisons.contains(listeLieu))
  //   listeLieu.map(decouper(_))
     
  //   for(x <- liaisons) {
  //    listeLieu.map(_.filter( _ != x))
  //   } 
  //    listeLieu.map(assembler(_))
  }

  def analyser(phrase : String): (String,String) = {
    val phrase_corrigee: String = assembler(FautesImpl.correction(decouper(phrase)))
    analyserListe(liste_lieux, phrase_corrigee)
  }
 

  def analyserListe(lieux: List[String], phrase : String): (String, String) = {
    lieux match {
      case Nil => ("","")
      case head :: next => 
        if (phrase.toLowerCase().contains(head.toLowerCase())) {
          val adresse = BDDImpl.chercherAdresse(head)
          adresse match {
            case "Place de la Mairie" => ("Mairie de Rennes",adresse)
            case "1, Rue Saint-Hélier" => ("Théâtre National de Bretagne",adresse)
            case "19, Place de la Gare" => ("Gare SNCF",adresse)
            case "2, Rue du Pré de Bris" => ("Théâtre la Paillette",adresse)
            case "feur" => ("quoi",adresse)
          }
        }
        else analyserListe(next, phrase)
    }
  }

  def decouper(phrase : String): List[String] = phrase.split(" ").toList

  def assembler(list : List[String]): String = {
    list match {
      case Nil => ""
      case head :: Nil => head
      case head :: next => head + " " + assembler(next)
    }
  }

  def politeTest_Bonjour(phrase: String): Boolean = phrase.toLowerCase().contains("bonjour") | phrase.toLowerCase().contains("salut") | phrase.toLowerCase().contains("bonsoir")
  
}
