package analyse_phrase

trait AnalyseTrait {

/**
  * analyse les mots de la requete du user pour renvoyer la liste des couples (lieu, adresse)
  *
  * @param phrase une String correspondant à la requete du user 
  * @return la liste de couples (lieu, adresses)
  */
  def analyser(phrase: String): List[(String,String)]
  
}
