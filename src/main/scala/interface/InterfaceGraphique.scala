package interface

import scala.swing.SimpleSwingApplication
import scala.swing._
import java.awt.Color
import javax.swing.ImageIcon
import java.io.File

object InterfaceGraphique extends SimpleSwingApplication{
   
    def top: MainFrame = new UI

}

class UI extends MainFrame {

    title = "Avatar"
    preferredSize = new Dimension (500, 500)

    val input = new InField
    input.background_=(Color.WHITE)
    input.foreground_=(Color.BLACK)
    val output = new ResultText
    val output2 = new ResultText
    val send = new SendButton("Envoyer",output,input)
    val reinit = new Button("Reinitialiser la conversation")
    val image = new ImageIcon(new File("doc/Logo.png").getAbsolutePath)
    val label = new Label("", image, Alignment.Right)
  
    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += reinit
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += output
        contents += label
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += input
        contents += send
      }
    }
}
