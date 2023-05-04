package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineImpl
import javax.swing.ImageIcon

/**
  * Création du bouton pour activer ou desactiver la voix
  * @param conv la conversation à actualiser, from la zone de texte
  */
class BoutonVoix(conv: BoxPanel, send : SendButton, from : InField) extends Button{
  foreground = Color.white                  // couleur de police
  background = new Color(0xff2c29)          // couleur de fond du bouton
  font = new Font("Arial", Font.BOLD, 16)   // choix de la police et de la taille
  borderPainted = true                      // bordure invisible              
  focusPainted = false                      // tour du texte désactivé
  contentAreaFilled = true                  // activation du changelent de couleur au moment du clic
  opaque = true                             // bouton opaque
  val image = new ImageIcon("doc/mic.png").getImage()
  val newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
  icon = new ImageIcon(newImage)
  text = "Voix : Désactivé"                 // texte du bouton
  listenTo(from.keys)
  reactions +={                             // actions effectuées par le bouton
  case ButtonClicked(_) | KeyPressed(_, Key.F2, _, _) => {
                             if(send.active){
                                text="Voix : Désactivé"
                             }
                             else {
                                text = "Voix : Activé"
                             }
                             send.active = !send.active
                             conv.peer.updateUI;       // actualisation de l'affichage
                            }
  }
}

