package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.Image
import java.io.File

class robotPanel(lab: String) extends BoxPanel(Orientation.Horizontal){
    private val robotMsg = new BubbleText (lab,new Color(0xff2c29))
    private val robotImage = new ImageIcon(new File("doc/Robot.jpg").getAbsolutePath).getImage()
    private val resizedrobotImage = new ImageIcon(robotImage.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    private val labelRobot = new Label("", resizedrobotImage, Alignment.Center)
    contents += labelRobot
    contents += robotMsg
}
