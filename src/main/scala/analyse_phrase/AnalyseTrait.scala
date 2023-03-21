package analyse_phrase

trait AnalyseTrait {

/**
  * analyse les mots de la requete du user pour renvoyer le couple (lieu, adresse)
  *
  * @param phrase une String correspondant à la requete du user 
  * @return le couple (lieu, adresses)
  */
  def analyser(phrase: String): (String,String)
  
}
