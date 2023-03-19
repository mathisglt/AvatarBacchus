package analyse_phrase

import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  def analyser(phrase : String): List[(String,String)] = {
    analyse_List(decouper(phrase))
  }

  /**
    * analyse les mots de la requete du user (sous forme de list[String]) pour renvoyer la liste des couples (lieu, adresse)
    *
    * @param phrase_decoup une liste de String
    * @return une liste de couples (String,String) "(lieu,adresse)"
    */
  def analyse_List(phrase_decoup: List[String]): List[(String,String)] = {
    phrase_decoup match {
      case Nil => Nil
      case head :: next => 
        if (BDDImpl.chercherAdresse(head) != "Adresse non trouvée") (head,BDDImpl.chercherAdresse(head)) :: analyse_List(next)
        else analyse_List(next)
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
