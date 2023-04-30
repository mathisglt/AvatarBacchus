package langue

object LangueImpl extends LangueTrait {
  private var langueActuelle = 0 //entier correspondant au numéro de la langue en cours d'utilisation

  def langueSuivante(): Unit = {
    langueActuelle = (langueActuelle + 1) % 5 //passe la langue actuelle à la langue suivante
  }

  def reinitLangue(): Unit = {
    setLangueActuelle("Français") //réinitialisation de la langue sur Français
  }

  def getLangueActuelle(): Int = {
    langueActuelle //obtenir le numéro de la langue utilisée actuellement, on retourne directement le numéro car la langue est utilisée sous forme d'entier dans d'autres fichier
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

  def getLangueQuestion(): String = {
    langueActuelle match {
      case 0 => "Parlez-vous français?"
      case 1 => "Do you speak english?"
      case 2 => "Hablas español?"
      case 3 => "Sprechen Sie Deutsch?"
      case 4 => "Parli italiano?"
      case _ => throw new Exception("Quelque chose d'inédit s'est produit (voir getLangueQuestion)")
    }
  }

  def langueIntToString(langue: Int): String = {
    langue match {
      case 0 => "français"
      case 1 => "english"
      case 2 => "español"
      case 3 => "deutsch"
      case 4 => "italiano"
      case _ => throw new Exception("langue inconnue")
    }
  }

  def langueStringToInt(langue: String): Int = {
    langue match {
      case "français" => 0
      case "english"  => 1
      case "español"  => 2
      case "deutsch"  => 3
      case "italiano" => 4
      case _          => throw new Exception("langue inconnue")
    }
  }
}
