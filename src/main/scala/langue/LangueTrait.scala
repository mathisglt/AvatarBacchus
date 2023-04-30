package langue

trait LangueTrait {

  /** Opère un passage à la langue suivante : incrémente de 1 la langue actuelle
    * 
    */
  def langueSuivante(): Unit

  /** Réinitialise la langue (Français par défaut, soit 0)
    * 
    */
  def reinitLangue(): Unit

  /** Retourne le numéro de la langue actuelle
    *
    * @return le numéro de la langue actuelle
    */
  def getLangueActuelle(): Int

  /** configuration de la langue actuelle
    *
    * @param langue la langue souhaitée parmi "Anglais", "Espagnol", "Allemand", "Italien" ou any (="Français")
    */
  def setLangueActuelle(langue: String): Unit
  
  /** Retourne la question pour demander à l'utilisateur s'il parle la langue actuelle
    * 
    *
    * @return la question correspondante
    */
  def getLangueQuestion(): String

  /** Renvoie le numéro de la langue actuelle 
    * 
    * @param langue sous forme de string
    * @return le numéro de la langue correspondante
    */
  def langueStringToInt(langue: String): Int 

  /** Renvoie la langue actuelle sous forme de string
    *
    * @param langue le numéro de la langue
    * @return la langue correspondante sous forme de string
    */
  def langueIntToString(langue: Int): String
}
