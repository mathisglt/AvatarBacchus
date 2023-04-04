package langue

trait LangueTrait {

  def langueSuivante(): Unit
  def getLangueActuelle(): Int
  def setLangueActuelle(langue: String): Unit
  def reinitLangue():Unit
  def langueActuelleToString(langue_actuelle: Int): String
}
