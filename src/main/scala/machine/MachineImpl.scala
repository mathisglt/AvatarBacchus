package machine
import construction_result.ConstructionImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {
  var changementEnCours = false //changement de langue en cours

  def ask(requete: String): List[String] = {
    println("requete : " + requete)
    val msgRobot = ConstructionImpl.construireLangue(requete) //renvoie le message du robot

    if (!changementEnCours) {
      //s'il n'y a pas de changement de langue en cours et que le message du robot est une question pour savoir la langue, 
      //on change la langue et on active le changement
      if (analyseMsgRobot(msgRobot).head != "Cas par défaut") {
        changementEnCours = true
      }
      println("message robot : " + msgRobot)
      msgRobot //on renvoie ensuite le message du robot dans tous les cas
    } else {
      //si un changement de langue est en cours on regarde si l'utilisateur à confirmé la langue

      val confirmation = ConstructionImpl.construireConfirmation(requete,LangueImpl.getLangueActuelle()) 
      //on construit la phrase en fonction de la confirmation ou non

      confirmation match {
        //s'il n'y a pas de confirmation, on passe à la langue suivante et on demande à l'utilisateur si cela lui convient
        case "Pas de confirmation" =>
          if (analyseMsgRobot(msgRobot).head == "Cas par défaut") {
            LangueImpl.langueSuivante(); 
            LangueImpl.getLangueQuestion() :: Nil
          } else {
            analyseMsgRobot(msgRobot)
          }
        //s'il y a confirmation, il n'y a plus de changement de langue en cours et on renvoie la confirmation que la langue à été changée
        case _ => changementEnCours = false; 
                  println("message robot : " + confirmation); 
                  confirmation :: Nil
      }
    }
  }

  /**
    * Analyse si le message du robot est une question pour connaître la langue ou non
    *
    * @param msgRobot la liste de messages réponses du robot
    * @return soit la réponse du robot après avoir changé la langue, 
    *         soit "Cas par défaut" lorsque le message n'est pas une des questions
    */
  def analyseMsgRobot(msgRobot: List[String]): (List[String]) = {
    var reponse = msgRobot
    msgRobot.last match {
      case "Parlez-vous français?" =>
        LangueImpl.setLangueActuelle("Français"); 
        reponse
      case "Do you speak english?" =>
        LangueImpl.setLangueActuelle("Anglais"); 
        reponse
      case "Hablas español?" =>
        LangueImpl.setLangueActuelle("Espagnol"); 
        reponse
      case "Sprechen Sie Deutsch?" =>
        LangueImpl.setLangueActuelle("Allemand"); 
        reponse
      case "Parli italiano?" => 
        LangueImpl.setLangueActuelle("Italien"); 
        reponse
      case _                 => "Cas par défaut" :: Nil
    }
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    LangueImpl.reinitLangue //réinitialise la langue
    changementEnCours = false //réinitialise la changement de langue au cas où il y en avait un en cours
  }
  def test(l: List[String]): List[String] = {
    l match {
      case Nil          => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}
