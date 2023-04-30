package machine

trait MachineDialogue {

  /** envoi d'une requête à la machine et réccupération de sa réponse
    *  @param s la requête
    *  @return la liste de réponses
    */
  def ask(s: String): List[String]

  /**
    * Retourne la langue actuelle aux fichiers qui n'y ont pas accès
    *
    * @return la langue actuelle
    */
  def getLangueActuelle (): Int 

  // Pour la partie test par le client
  /**
    * réinitialisation de l'avatar
    */
  def reinit(): Unit

  /** test de l'avatar
    *  @param l une liste de requête
    *  @return la liste des réponses produites par l'avatar
    */
  def test(l: List[String]): List[String]
}
