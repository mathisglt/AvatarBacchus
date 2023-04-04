package langue

object LangueImpl extends LangueTrait {
  private var langueActuelle = 0

  def langueSuivante(): Unit = {
    langueActuelle = (langueActuelle + 1) % 5
  }

  def getLangueActuelle(): Int = {
    langueActuelle
  }

  def setLangueActuelle(langue: String): Unit = {
    langue match {
      case "Anglais"  => langueActuelle = 1
      case "Espagnol" => langueActuelle = 2
      case "Allemand" => langueActuelle = 3
      case "Italien"  => langueActuelle = 4
      case _          => langueActuelle = 0
    }
  }

}
