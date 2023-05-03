package interface

import scala.swing._
import java.io.File
import scala.swing.event.{ButtonClicked,KeyPressed,Key}
import java.awt.{Image, Toolkit, Color}
import javax.swing.ImageIcon

object InterfaceGraphique extends SimpleSwingApplication{

    def top: MainFrame = new UI
}

class UI extends MainFrame {

    title = "Avatar"
    val image = new ImageIcon("doc/bzhicon.png").getImage()
    val newImage = image.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH)
    iconImage = newImage
    preferredSize = new Dimension (1020,750)
    minimumSize = new Dimension (1020,750)

    //Création des composants
    val input = new InField                                // zone de texte à taper
    val grisFonce = new Color (0x1d1e20)                   // couleur utilisée
    val conversation = new BoxPanel(Orientation.Vertical){ // initialisation de la conversation
                          maximumSize = new Dimension(1020,650)
                          background = grisFonce
                          border = null
                          contents += new RobotPanel("Bonjour, comment puis-je vous aider ?") // la conversation commence par un message du robot
                        }     
    val scrollPanel = new ScrollPane(conversation)         // ajout de la conversation dans une zone scrollable
    var scrollBar = scrollPanel.verticalScrollBar          // création de la barre de scroll
    val reinit = new ReinitButton(conversation)            // création du bouton de réinitialisation
    val send = new SendButton(conversation,scrollBar,input) // création du bouton d'envoie
    val voice = new BoutonVoix(conversation,send)           //création du bouton activateur de la voix (F8)
    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {        // panel principal
      background = grisFonce
      contents += new BoxPanel(Orientation.Horizontal) {   // panel contenant le bouton de reinitialisation
        contents += reinit
        contents += voice
      }
      contents += scrollPanel                              // ajout du panel avec la conversation
      contents += new BoxPanel(Orientation.Horizontal) {   // panel en bas avec la zone de texte et le bouton envoi
        background = grisFonce
        contents += input
        contents += send
      }
    }
}