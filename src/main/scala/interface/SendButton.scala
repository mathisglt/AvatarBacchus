package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineImpl
import VoiceManagement.Voice
import langue.LangueImpl
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/** Création du bouton pour envoyer un message
  * @param conv la conversation à laquelle ajouter le message
  * @param scrollBar la scrollBar (on va s'en servir pour actualiser sa position après l'envoi)
  * @param from la zone de texte dans laquelle récupérer le message
  */
class SendButton(conv: BoxPanel, scrollBar: ScrollBar, from: InField)
    extends Button {
  var active: Boolean = false;
  text = "Envoyer" // texte du bouton
  maximumSize = new Dimension(120, 40) // taille max du bouton
  foreground = Color.white // couleur de la police
  background = new Color(0xff2c29) // couleur de fond du bouton
  font = new Font("Arial", Font.BOLD, 16) // choix de la police et de la taille
  borderPainted = false // bordure invisible
  focusPainted = false // tour du texte désactivé
  contentAreaFilled =
    true // activation du changelent de couleur au moment du clic
  opaque = true // bouton opaque
  listenTo(from.keys) // detecte l'activation des touches dans la zone de texte
  reactions += { // actions réalisée quand le bouton ou la touche "Entrée" sont cliqués
    case ButtonClicked(_) | KeyPressed(_, Key.Enter, _, _)
        if (from.text != "") => {
      conv.contents += new UserPanel(
        from.text
      ) // ajout à la conversation d'un message de l'utilisateur avec le texte qu'il a tapé
      écritPuisDis(MachineImpl.ask(from.text))
      from.text = "" // efface la zone de texte
      scrollToBottom() // actualise la scrollBar
    }
  }

  def écritPuisDis(messages: List[String]): Unit = {
    messages match {
      case head :: next =>
        conv.contents += new RobotPanel(
          head
        ) // ajout à la conversation de la/les reponse(s) du robot
        conv.peer.updateUI() // actualise la fenêtre
        Future {
          if (active) {
            if (MachineImpl.getLangueActuelle() != 2) { // on vérifie que la langue n'est pas espagnol car la voix n'existe pas
              Voice.ajouteMessage(
                head,
                MachineImpl.getLangueActuelle()
              ) // on ajoute tous les messages à la file d'attente pour la lecture avec la bonne langue
            }
          }
          écritPuisDis(next)
        }
      case Nil => ()
    }
  }

  /** Sert à envoyer la scrollBar en bas de la conversation à chaque fois qu'un message est envoyé
    */
  def scrollToBottom(): Unit = {
    scrollBar.peer.setValue(scrollBar.peer.getMaximum + 1)
  }
}
