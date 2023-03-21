package interface

import scala.swing.SimpleSwingApplication
import scala.swing._
import java.awt.Color
import javax.swing.ImageIcon
import java.io.File
import scala.swing.event.ButtonClicked
import java.awt.{Image, Toolkit}
import interface.SendButton
import scala.swing.event.KeyPressed
import scala.swing.event.Key

object InterfaceGraphique extends SimpleSwingApplication{
   
    def top: MainFrame = new UI
}


class UI extends MainFrame {

    title = "Avatar"
    preferredSize = new Dimension (1020,750)

    //Création des composants
    val input = new InField
    val grisFonce = new Color (0x1d1e20)
    val conversation = new BoxPanel(Orientation.Vertical){
                          maximumSize = new Dimension(1020,650)
                          contents += new robotPanel("Bonjour, comment puis-je vous aider ?")
                        }     
    val scrollPanel = new ScrollPane(conversation)
    var scrollBar = scrollPanel.verticalScrollBar
    val reinit = new ReinitButton(conversation)
    val send = new SendButton("Envoyer",conversation,scrollBar,input)
             
    // Ajout des composants à la fenêtre
    contents = new BoxPanel(Orientation.Vertical) {
      background = grisFonce
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += reinit
      }
      contents += new BorderPanel {
        background = grisFonce
        add(scrollPanel,BorderPanel.Position.Center)
      }
      contents += new BoxPanel(Orientation.Horizontal) {
        background = grisFonce
        contents += input
        contents += send
      }
    }
}
    
/*
class testUI extends MainFrame {

  val chat = new BoxPanel(Orientation.Vertical)
  val inputField = new TextField {
    
  }
  val scrollPanel = new ScrollPane(chat)
  var scrollBar = scrollPanel.verticalScrollBar
  val window = new BorderPanel {
    add(scrollPanel, BorderPanel.Position.Center)
    add(inputField, BorderPanel.Position.North)
  }
  contents = window

  def printToChat(s: String) {
    chat.contents += new Label(s) {
      horizontalAlignment = Alignment.Right
      border = Swing.EmptyBorder(20, 20, 0, 0) //(top, left, bottom, right)
    }
    }
}

*/
