package machine

import construction_result.ConstructionImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {
  var changementEnCours = false //changement de langue en cours

  def ask(requete: String): List[String] = {
    val msgRobot =
      ConstructionImpl.construireLangue(requete) //renvoie le message du robot

    if (!changementEnCours) {
      //s'il n'y a pas de changement de langue en cours et que le message du robot est une question pour savoir la langue,
      //on change la langue et on active le changement
      if (analyseMsgRobot(msgRobot).head != "Cas par défaut") {
        changementEnCours = true
      }
      msgRobot //on renvoie ensuite le message du robot dans tous les cas
    } else {
      //si un changement de langue est en cours on regarde si l'utilisateur à confirmé la langue

      val confirmation = ConstructionImpl.construireConfirmation(
        requete.replaceAll(" ", ""),
        LangueImpl.getLangueActuelle()
      )
      //on construit la phrase en fonction de la confirmation ou non

      confirmation match {
        //s'il n'y a pas de confirmation, on passe à la langue suivante et on demande à l'utilisateur si cela lui convient
        case "Pas de confirmation" =>
          if (analyseMsgRobot(msgRobot) == "Cas par défaut" :: Nil) {
            LangueImpl.langueSuivante();
            LangueImpl.getLangueQuestion() :: Nil
          } else {
            analyseMsgRobot(msgRobot)
          }
        //s'il y a confirmation, il n'y a plus de changement de langue en cours et on renvoie la confirmation que la langue à été changée
        case _ =>
          changementEnCours = false;
          confirmation :: Nil
      }
    }
  }

  /** Analyse si le message du robot est une question pour connaître la langue ou non
    *
    * @param msgRobot la liste de messages réponses du robot
    * @return soit la réponse du robot après avoir changé la langue,
    *         soit "Cas par défaut" lorsque le message n'est pas une des questions
    */
  private def analyseMsgRobot(msgRobot: List[String]): (List[String]) = {
    msgRobot.last match {
      case "Parlez-vous français?" =>
        LangueImpl.setLangueActuelle("Français");
        msgRobot
      case "Do you speak english?" =>
        LangueImpl.setLangueActuelle("Anglais");
        msgRobot
      case "Hablas español?" =>
        LangueImpl.setLangueActuelle("Espagnol");
        msgRobot
      case "Sprechen Sie Deutsch?" =>
        LangueImpl.setLangueActuelle("Allemand");
        msgRobot
      case "Parli italiano?" =>
        LangueImpl.setLangueActuelle("Italien");
        msgRobot
      case _ => "Cas par défaut" :: Nil
    }
  }

  def getLangueActuelle(): Int = {
    LangueImpl.getLangueActuelle()
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    LangueImpl.reinitLangue //réinitialise la langue
    changementEnCours =
      false //réinitialise la changement de langue au cas où il y en avait un en cours
    ConstructionImpl.choix_en_cours = false //réinitialise le choix du user
    ConstructionImpl.liste_propositions_saved =
      Nil //réinitialise la liste des lieux proposés sauvegardée
  }

  /** test de l'avatar
    *  @param l une liste de requête
    *  @return la liste des réponses produites par l'avatar
    */
  def test(l: List[String]): List[String] = {
    l.flatMap(ask(_))
  }
}
