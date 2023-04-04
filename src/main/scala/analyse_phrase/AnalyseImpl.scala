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
    .concat(List("TNB", "hotel"))
  val listeAvecLiason = liste_lieux.map(decouper(_))

  /** permet de retirer les mots de liaisons de phrase sous formes de liste de string
    *  @param phrase sous forme de liste de string
    *  @return la phrase sous forme de liste de string sans les mots de liaisons
    */
  def filtreLiaison(listeLieu: List[String]): List[String] = {
    val liaisons = List("de", "La", "l")
    listeLieu.filter(listeLieu => !liaisons.contains(listeLieu))
  }

  def analyser(phrase: String): (String, String) = {
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        filtreLiaison(liste_lieux.flatMap(decouper(_)))
      )
    )
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

  /** decoupe un string en plusieurs string élémentts d'une liste de string
    *  @param phrase qui est le string à découper
    *  @return une liste dont chaque élément est un mot de la phrase dans l'ordre dans lequel il apparaît dans le string de base
    */
  def decouper(phrase: String): List[String] = phrase.split("[ .!?,;']+").toList

  /** assemble une liste de mot (string) pour former une phrase sous forme de string avec un espace entre chaque mot
    *  @param list une list de mot
    *  @return un string qui sera la phrase décrit par les éléments de départ
    */
  def assembler(list: List[String]): String = {
    if (list.isEmpty) throw ExceptionListeVide
    else list.reduce(_ + " " + _)
  }

  //Analyse politesse 

  def politeTest_Bonjour(phrase: String): Boolean = {
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        List("bonjour", "bonsoir", "salut")
      )
    )
    phrase_corrigee.toLowerCase().contains("bonjour") || phrase_corrigee
      .toLowerCase()
      .contains("salut") || phrase_corrigee.toLowerCase().contains("bonsoir")
  }

  def politeTest_OnlyBonjour(phrase: String): Boolean = {
    val phrase_corrigee: String = assembler(
      FautesImpl.correction(
        decouper(phrase),
        List("bonjour", "bonsoir", "salut")
      )
    )
    phrase_corrigee.toLowerCase().equals("bonjour") || phrase_corrigee
      .toLowerCase()
      .equals("salut") || phrase_corrigee.toLowerCase().equals("bonsoir")
  }

  // Analyse Langue

  def getDicoLangue(): List[String] = {
    val dicoExpr = BDDImpl.getDicoExpr
    val langue_actuelle = LangueImpl.getLangueActuelle
    var expr = dicoExpr(langue_actuelle)




    dicoExpr match {
      case Nil      => Nil
      case hd :: tl => 
      }
    }
  }

}
