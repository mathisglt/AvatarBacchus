package langue

trait LangueTrait {

  /** opère un passage à la langue suivante : incrémente d'1 la langue actuelle
    */
  def langueSuivante(): Unit

  /** @return l'entier représentant la langue actuelle
    */
  def getLangueActuelle(): Int

  /** change la langue actuelle en celle envoyée en paramêtre
    * @param langue une langue parmi celles proposées
    */
  def setLangueActuelle(langue: String): Unit

  /** met la langue actuelle au français
    */
  def reinitLangue(): Unit

  /** @param langue un int représentant une langue
    * @return la langue en paramêtre sous forme de String ou français si elle est inconnue
    */
  def langueIntToString(langue: Int): String
}
