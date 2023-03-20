package analyse_phrase

import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  def analyser(phrase : String): List[(String,String)] = {
    analyseList(decouper(phrase))
  }

  /**
    * analyse les mots de la requete du user (sous forme de list[String]) pour renvoyer la liste des couples (lieu, adresse)
    *
    * TODO : gérer pour hotel de ville
    * 
    * @param phrase_decoup une liste de String
    * @return une liste de couples (String,String) "(lieu,adresse)"
    */
  def analyseList(phrase_decoup: List[String]): List[(String,String)] = {
    phrase_decoup match {
      case Nil => Nil
      case head :: next => 
        val adresse = BDDImpl.chercherAdresse(head)
        if (adresse != "Adresse non trouvée") {
          adresse match {
            case "Place de la Mairie" => ("Mairie de Rennes",adresse) :: analyseList(next)
            case "1, Rue Saint-Hélier" => ("Théâtre National de Bretagne",adresse) :: analyseList(next)
            case "19, Place de la Gare" => ("Gare SNCF",adresse) :: analyseList(next)
            case "Rue du Pré de Bris" => ("Théâtre la Paillette",adresse) :: analyseList(next)
            case _ => (head,adresse) :: analyseList(next) // il n'existe pas d'autres valeurs possibles mais au cas où
          }
        }
        else analyseList(next)
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
