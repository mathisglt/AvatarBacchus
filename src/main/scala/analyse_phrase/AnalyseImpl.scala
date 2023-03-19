package analyse_phrase

import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  def analyser(phrase: String): List[(String,String)] = {
    val phrase_decoup = decouper(phrase)
    Nil
  }

  /**
    * decoupe la phrase en liste de mots
    *
    * @param phrase une string a decouper
    * @return la liste de string avec composee de tous les mots
    */
  def decouper(phrase: String): List[String] = phrase.split(" ").toList
  
}
