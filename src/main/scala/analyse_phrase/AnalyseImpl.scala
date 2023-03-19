package analyse_phrase

import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  def analyser(phrase_decoup: List[String]): List[(String,String)] = {
    phrase_decoup match {
      case Nil => Nil
      case head :: next => 
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") (head,BDDImpl.chercherAdresse(head)) :: analyser(next)
        else analyser(next)
    }
  }

  /**
    * decoupe la phrase en liste de mots
    *
    * @param phrase une string a decouper
    * @return la liste de string avec composee de tous les mots
    */
  def decouper(phrase: String): List[String] = phrase.split(" ").toList
  
}
