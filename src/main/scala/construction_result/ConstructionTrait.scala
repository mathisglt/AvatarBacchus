package construction_result

trait ConstructionTrait {

  /** Si une langue est détectée, on demande à l'utilisateur s'il parle cette langue,
    * sinon on renvoie les réponses du robot correspondantes à la requête 
    *
    * @param requete, la requête de l'utilisateur
    * @return soit, la question pour demander à l'utilisateur s'il parle la langue détectée
    * soit, les réponses du robot à la requête
    */
  def construireLangue(requete: String): List[String]

  /** Renvoie la confirmation à l'utilisateur que la langue à bien été changée 
    * si sa requête était "oui" dans la même langue, et renvoie "Pas de confirmation" sinon 
    *
    * @param requete la requête de l'utilisateur (soit un "oui" dans la bonne langue soit autre chose)
    * @param langueActuelle la langue actuelle
    * @return la confirmation dans la bonne langue
    */
  def construireConfirmation(requete: String, langueActuelle: Int): String

}
