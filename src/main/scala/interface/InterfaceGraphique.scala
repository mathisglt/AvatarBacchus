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

    private val conv:List[BoxPanel] = List(new robotPanel("Bonjour, comment puis-je vous aider ?"))

    title = "Avatar"
    preferredSize = new Dimension (1020, 900)
    

    //Création des composants
    val input = new InField
    val send = new SendButton("Envoyer",conv,input)
    val reinit = new ReinitButton(conv)

    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      background = new Color (0x1d1e20)
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += reinit
      }
      contents += affichageDiscussion(conv)
      contents += new BoxPanel(Orientation.Horizontal) {
        background = new Color (0x1d1e20)
        contents += input
        contents += send
      }
    }

    def affichageDiscussion(conv: List[BoxPanel]): BoxPanel= {
      new BoxPanel(Orientation.Vertical){
        conv match {
          case Nil => Nil
          case head :: next => contents += head; contents += affichageDiscussion(next)
        }
      }
    }
}
