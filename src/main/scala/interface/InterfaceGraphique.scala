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

    private val conv:Array[BoxPanel] = Array(new robotPanel("Bonjour, comment puis-je vous aider ?"),
                                             new userPanel("Où ?"))

    title = "Avatar"
    preferredSize = new Dimension (1020,750)
    

    //Création des composants
    val input = new InField
    val send = new SendButton("Envoyer",conv,input)
    val reinit = new ReinitButton(conv)
    val grisFonce = new Color (0x1d1e20)

    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      background = grisFonce
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += reinit
      }
      contents += affichageDiscussion(conv.toList)
      contents += new BoxPanel(Orientation.Horizontal) {
        background = grisFonce
        contents += input
        contents += send
      }
    }

    def affichageDiscussion(conv: List[BoxPanel]): BoxPanel= {
      new BoxPanel(Orientation.Vertical){
        maximumSize = new Dimension(1020,650)
        background = grisFonce
        conv match {
          case Nil => Nil
          case head :: Nil => contents += head;
          case head :: next => contents += head; contents += affichageDiscussion(next)
        }
      }
    }
}
