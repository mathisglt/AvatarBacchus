package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.{Color, Font,Image}
import java.io.File

class RobotPanel(text: String) extends BoxPanel(Orientation.Horizontal){
    background = new Color (0x444654)

    private val robotMsg = new BubbleText (text, Alignment.Left)
    private val robotImage = new ImageIcon(new File("doc/Robot.jpg").getAbsolutePath).getImage()
    private val resizedrobotImage = new ImageIcon(robotImage.getScaledInstance(50,50,Image.SCALE_DEFAULT))
    private val labelRobot = new Label("Avatar | ", resizedrobotImage, Alignment.Center){
        font = new Font("Arial", Font.BOLD, 15)
        foreground = new Color (0xa1a2a9)
    }

    contents += labelRobot
    contents += robotMsg
}
