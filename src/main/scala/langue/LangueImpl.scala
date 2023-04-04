package langue

object LangueImpl extends LangueTrait {
  private var langueActuelle = 0

  def langueSuivante(): Unit = {
    langueActuelle = (langueActuelle + 1) % 5
  }

  def getLangueActuelle(): Int = {
    langueActuelle
  }

  def reinitLangue(): Unit = {
    setLangueActuelle("Français")
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

  def langueActuelleToString(langue_actuelle: Int): String= {
    langue_actuelle match {
      case 0 => "Français"
      case 1 => "Anglais"
      case 2 => "Espagnol"
      case 3 => "Allemand"
      case 4 => "Italien"
    }

  }
}