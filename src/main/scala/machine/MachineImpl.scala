package machine
import construction_result.ConstructionImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {
  var changementEnCours = false //changement de langue en cours

  def ask(requete: String): List[String] = {
    println("requete : " + requete)
    val msgRobot =
      ConstructionImpl.construireLangue(requete) //renvoie le message du robot

    if (!changementEnCours) { //s'il n'y a pas de changement en cours et que le message du robot est une des phrases suivantes, on change la langue
      msgRobot.head match {
        case "Parlez-vous français?" =>
          LangueImpl.setLangueActuelle("Français"); changementEnCours = true;
          msgRobot
        case "Do you speak english?" =>
          LangueImpl.setLangueActuelle("Anglais"); changementEnCours = true;
          msgRobot
        case "Hablas español?" =>
          LangueImpl.setLangueActuelle("Espagnol"); changementEnCours = true;
          msgRobot
        case "Sprechen Sie Deutsch?" =>
          LangueImpl.setLangueActuelle("Allemand"); changementEnCours = true;
          msgRobot
        case "Parli italiano?" =>
          LangueImpl.setLangueActuelle("Italien"); changementEnCours = true;
          msgRobot
        case _ => msgRobot
      }

    } else { //si un changement est en cours on regarde si l'utilisateur à confirmer la langue
      val confirmation = ConstructionImpl.construireConfirmation(
        requete,
        LangueImpl.getLangueActuelle()
      ) //on construit la phrase en fonction de la confirmation ou non
      confirmation match {
        //s'il n'y a pas de confirmation, on passe à la langue suivant et on demande à l'utilisateur si cela lui convient
        case "Pas de confirmation" =>
          LangueImpl.langueSuivante(); LangueImpl.getLangueQuestion() :: Nil
        //s'il y a confirmation, il n'y a plus de changement de langue en cours et on renvoie la confirmation que la langue à été changée
        case _ => changementEnCours = false; confirmation :: Nil
      }
    }
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    LangueImpl.reinitLangue //réinitialise la langue
    changementEnCours = false
  }
  def test(l: List[String]): List[String] = {
    l match {
      case Nil          => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}
