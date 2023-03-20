package analyse_phrase

import bdd.BDDImpl

object AnalyseImpl extends AnalyseTrait {

  def analyser(phrase : String): List[(String,String)] = {
    analyse_List(decouper(phrase))
  }

  /**
    * analyse les mots de la requete du user (sous forme de list[String]) pour renvoyer la liste des couples (lieu, adresse)
    *
    * TODO : gérer pour hotel de ville
    * 
    * @param phrase_decoup une liste de String
    * @return une liste de couples (String,String) "(lieu,adresse)"
    */
  def analyse_List(phrase_decoup: List[String]): List[(String,String)] = {
    phrase_decoup match {
      case Nil => Nil
      case head :: next => 
        val adresse = BDDImpl.chercherAdresse(head)
        if (adresse != "Adresse non trouvée") {
          adresse match {
            case _ if (head.toLowerCase().contains("hôtel")) => ("Hôtel de Ville",adresse) :: analyse_List(next)
            case _ if (adresse.contains("Place de la Mairie")) => ("Mairie de Rennes",adresse) :: analyse_List(next)
            case _ if (adresse.contains("1, Rue Saint-Hélier")) => ("Théâtre National de Bretagne",adresse) :: analyse_List(next)
            case _ if (adresse.contains("19, Place de la Gare")) => ("Gare SNCF",adresse) :: analyse_List(next)
            case _ if (adresse.contains("Rue du Pré de Bris")) => ("Théâtre la Paillette",adresse) :: analyse_List(next)
            case _ => (head,adresse) :: analyse_List(next)
          }
        }
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
