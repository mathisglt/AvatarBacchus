package analyse_phrase

import scala.io.Source
import scala.io.BufferedSource
import bdd.BDDImpl

case object ExceptionListeVide extends Exception

object AnalyseImpl extends AnalyseTrait {

  val liste_lieux = BDDImpl.recup(Source.fromFile("doc/DonneesInitiales.txt")).toList

  def analyser(phrase : String): (String,String) = analyserListe(liste_lieux, phrase)

  def analyserListe(lieux: List[String], phrase : String): (String, String) = {
    lieux match {
      case Nil => ("","")
      case head :: next => 
        if (phrase.toLowerCase().contains(head.toLowerCase())) {
          val adresse = BDDImpl.chercherAdresse(head)
          return (head, adresse)
        }
        else analyserListe(next, phrase)
    }
  }
  
}
