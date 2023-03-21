package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.Image
import java.io.File

class userPanel(lab: String) extends BoxPanel(Orientation.Horizontal){
    private val userMsg = new BubbleText (lab)
    private val userImage = new ImageIcon(new File("doc/Logo.png").getAbsolutePath).getImage()
    private val resizeduserImage = new ImageIcon(userImage.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    private val labelUser = new Label("Vous : ", resizeduserImage, Alignment.Left)
    
    contents += labelUser
    contents += userMsg
}
