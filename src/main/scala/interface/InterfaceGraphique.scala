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
    val input2 = new InField
  
  // Ajout des composants à la fenêtre
  contents = new BoxPanel(Orientation.Vertical) {
    contents += input
    contents += input2
  }
    
}