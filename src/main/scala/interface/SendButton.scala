package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineDialogue
import machine.MachineImpl

/**
  * Création du bouton pour envoyer un message
  * @param conv la conversation à laquelle ajouter le message
  * @param scrollBar la scrollBar (on va s'en servir pour actualiser sa position après l'envoi)
  * @param from la zone de texte dans laquelle récupérer le message
  */
class SendButton(conv: BoxPanel,scrollBar: ScrollBar, from: InField) extends Button{
  text = "Envoyer"                         // texte du bouton
  maximumSize = new Dimension(120,40)      // taille max du bouton
  foreground = Color.white                 // couleur de la police
  background = new Color(0xff2c29)         // couleur de fond du bouton
  font = new Font("Arial", Font.BOLD, 16)  // choix de la police et de la taille
  borderPainted = false                    // bordure invisible  
  focusPainted = false                     // tour du texte désactivé
  contentAreaFilled = true                 // activation du changelent de couleur au moment du clic
  opaque = true                            // bouton opaque
  listenTo(from.keys)                      // detecte l'activation des touches dans la zone de texte
  reactions += {                           // actions réalisée quand le bouton ou la touche "Entrée" sont cliqués
  case ButtonClicked(_) | KeyPressed(_, Key.Enter, _, _) if(from.text != "")=> {conv.contents += new UserPanel(from.text); // ajout à la conversation d'un message de l'utilisateur avec le texte qu'il a tapé
                                                                                conv.contents += new RobotPanel(MachineImpl.ask(from.text).head); // ajout à la conversation de la/les reponse(s) du robot
                                                                                from.text = ""; // efface la zone de texte
                                                                                scrollToBottom(); // actualise la scrollBar
                                                                                conv.revalidate; // actualise la fenêtre
                                                                              }
  }
  
  /**
    * Sert à envoyer la scrollBar en bas de la conversation à chaque fois qu'un message est envoyé
    */
  def scrollToBottom() {
      scrollBar.peer.setValue(scrollBar.peer.getMaximum+1)
  }
}

