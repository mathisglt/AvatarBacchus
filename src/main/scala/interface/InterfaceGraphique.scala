package interface

import scala.swing.SimpleSwingApplication
import scala.swing._
import java.awt.Color

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
    output.background_=(Color.WHITE)
    output.foreground_=(Color.BLACK)
    val send = new SendButton("Envoyer",output,input)
  
    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += output
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += input
        contents += send
      }
    }
}
