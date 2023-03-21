package analyse_phrase

trait AnalyseTrait {

  /**
    * analyse les mots de la requete du user pour renvoyer le couple (lieu, adresse)
    *
    * @param phrase une String correspondant à la requete du user 
    * @return le couple (lieu, adresses)
    */
  def analyser(phrase: String): (String,String)

  /**
    * analyse la phrase du user et renvoie true s'il contient "bonjour", "bonsoir" ou "salut"
    *
    * @param phrase la requete du user
    * @return true si contient un des mots, false sinon
    */
  def politeTest_Bonjour(phrase: String): Boolean
  
}
