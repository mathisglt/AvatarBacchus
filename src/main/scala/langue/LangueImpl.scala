package langue

object LangueImpl extends LangueTrait {
  private var langueActuelle = 0 //entier correspondant au numéro de la langue en cours d'utilisation

  def langueSuivante(): Unit = {
    langueActuelle = (langueActuelle + 1) % 5 //passe la langue actuelle à la langue suivante 
  }

  def getLangueActuelle(): Int = {
    langueActuelle //obtenir le numéro de la langue utilisée actuellement, on retourn directement le numéro car la langue est utilisée sous forme d'entier dans d'autres fichier
  }

  def reinitLangue(): Unit = {
    setLangueActuelle("Français") //réinitialisation de la langue sur Français 
  }

  /**
    * configuration de la langue actuelle
    *
    * @param langue la langue souhaitée
    */
  def setLangueActuelle(langue: String): Unit = {
    langue match {
      case "Anglais"  => langueActuelle = 1
      case "Espagnol" => langueActuelle = 2
      case "Allemand" => langueActuelle = 3
      case "Italien"  => langueActuelle = 4
      case _          => langueActuelle = 0
    }
  }

  /**
    * renvoie la langue actuelle sous forme de string
    *
    * @param langue_actuelle le numéro de la langue
    * @return la langue correspondante
    */
  def langueActuelleToString(langue_actuelle: Int): String= {
    langue_actuelle match {
      case 0 => "Français"
      case 1 => "Anglais"
      case 2 => "Espagnol"
      case 3 => "Allemand"
      case 4 => "Italien"
    }
  }

  def langueStringToInt(langue : String): Int = {
      langue match {
        case "français" => 0
        case "english" => 1
        case "español" => 2
        case "deutsch" => 3
        case "italiano" => 4
      }
    }
}