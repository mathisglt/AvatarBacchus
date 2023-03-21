package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  val liste_lieux = BDDImpl.recupLieux(Source.fromFile("doc/DonneesInitiales.txt")).toList

  def analyser(phrase : String): (String,String) = analyserListe(liste_lieux, phrase)

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
          }
        }
        else analyserListe(next, phrase)
    }
  }

  def politeTest_Bonjour(phrase: String): Boolean = phrase.toLowerCase().contains("bonjour") | phrase.toLowerCase().contains("salut") | phrase.toLowerCase().contains("bonsoir")
  
}
