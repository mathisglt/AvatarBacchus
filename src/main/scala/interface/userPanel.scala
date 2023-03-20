package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.Image
import java.io.File

class userPanel(lab: String) extends BoxPanel(Orientation.Horizontal){
    private val userMsg = new BubbleText (lab,new Color(0xff2c29))
    private val userImage = new ImageIcon(new File("doc/Logo.png").getAbsolutePath).getImage()
    private val resizeduserImage = new ImageIcon(userImage.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    private val labelUser = new Label("", resizeduserImage, Alignment.Center)
     contents += userMsg
    contents += labelUser
}
