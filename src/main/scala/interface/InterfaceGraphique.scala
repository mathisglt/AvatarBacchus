package interface

import scala.swing.SimpleSwingApplication
import scala.swing._
import java.awt.Color
import javax.swing.ImageIcon
import java.io.File
import scala.swing.event.ButtonClicked
import java.awt.{Image, Toolkit}
import interface.SendButton

object InterfaceGraphique extends SimpleSwingApplication{
   
    def top: MainFrame = new UI

}

class UI extends MainFrame {

    title = "Avatar"
    preferredSize = new Dimension (1020, 900)

    //Création des composants
    var input = new InField
    var userText = new BubbleText (new Color(0xff2c29))
    var robotText = new BubbleText (new Color(0xff2c29))
    val send = new SendButton("Envoyer",userText,input)
    val reinit = new ReinitButton(userText)

    // Charger l'image
    val image = new ImageIcon(new File("doc/Robot.jpg").getAbsolutePath).getImage()
    
    // Redimensionner l'image
    val resizedImage = new ImageIcon(image.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    val label = new Label("", resizedImage, Alignment.Right)
  
    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += reinit
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += label
        contents += userText
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += input
        contents += send
      }
    }
}
