package construction_result

trait ConstructionTrait {

  /**
    * fonction de lancement de la construction de la réponse du robot
    * - elle s'adressera dans la bonne langue avec construireLangue
    * - elle ajoutera eventuellement 'bonjour' avec construirePolitesse
    * - elle construira ensuite la liste de reponses avec construireLesReponses, construireReponseUnique et construireLesPropositions
    *
    * @param requete la requete du user
    * @return la liste complete de reponses du robot bien construite
    */
  def construire(requete: String): List[String]

  /** Renvoie la confirmation à l'utilisateur que la langue à bien été changée 
    * si sa requête était "oui" dans la même langue, et renvoie "Pas de confirmation" sinon 
    *
    * @param requete la requête de l'utilisateur (soit un "oui" dans la bonne langue soit autre chose)
    * @param langueActuelle la langue actuelle
    * @return la confirmation dans la bonne langue
    */
  def construireConfirmation(requete: String, langueActuelle: Int): String

}
