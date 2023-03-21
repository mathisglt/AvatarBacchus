package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.{Color, Font, Image}
import java.io.File

class UserPanel(text: String) extends BoxPanel(Orientation.Horizontal){
    background = new Color (0x1d1e20)
    
    private val userMsg = new BubbleText (text,Alignment.Right)
    private val userImage = new ImageIcon(new File("doc/Logo.png").getAbsolutePath).getImage()
    private val resizeduserImage = new ImageIcon(userImage.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    private val labelUser = new Label(" | Vous", resizeduserImage, Alignment.Center){
        font = new Font("Arial", Font.BOLD, 15)
        horizontalTextPosition = Alignment.Left
        foreground = new Color (0xa1a2a9)
    }
    
    contents += userMsg
    contents += labelUser
}
