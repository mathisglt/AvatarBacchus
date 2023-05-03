package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineImpl

/**
  * Création du bouton pour activer ou desactive la voix
  * @param conv la conversation à effacer
  */
class BoutonVoix(conv: BoxPanel,send : SendButton) extends Button{
  foreground = Color.white                  // couleur de police
  background = new Color(0xff2c29)          // couleur de fond du bouton
  font = new Font("Arial", Font.BOLD, 16)   // choix de la police et de la taille
  borderPainted = true                     // bordure invisible              
  focusPainted = false                      // tour du texte désactivé
  contentAreaFilled = true                  // activation du changelent de couleur au moment du clic
  opaque = true                             // bouton opaque
  text = "Voix : Désactivé"    // texte du bouton
  reactions +={                             // actions effectuées par le bouton
  case ButtonClicked(_) => {
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

