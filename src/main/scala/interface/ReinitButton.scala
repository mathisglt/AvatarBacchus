package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineImpl
import javax.swing.ImageIcon

/**
  * Création du bouton pour reinitiliser la conversation 
  * @param conv la conversation à effacer
  */
class ReinitButton(conv: BoxPanel) extends Button{
  foreground = Color.white                  // couleur de police
  background = new Color(0xff2c29)          // couleur de fond du bouton
  font = new Font("Arial", Font.BOLD, 16)   // choix de la police et de la taille
  borderPainted = true                     // bordure invisible              
  focusPainted = false                      // tour du texte désactivé
  contentAreaFilled = true                  // activation du changelent de couleur au moment du clic
  opaque = true                             // bouton opaque
  text = "Réinitialiser la conversation"    // texte du bouton
  val image = new ImageIcon("doc/reset.png").getImage()
  val newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
  icon = new ImageIcon(newImage)
  reactions +={                             // actions effectuées par le bouton
    case ButtonClicked(_) => {conv.contents.clear(); // vide le contenu du panel de conversation
                              MachineImpl.reinit()
                              conv.contents += new RobotPanel("Bonjour, comment puis-je vous aider ?") // rajout du premier message du robot
                              conv.peer.updateUI;       // actualisation de l'affichage
                            }
  }
}

