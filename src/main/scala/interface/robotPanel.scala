package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.{Color, Font,Image}
import java.io.File

/**
  * Création du panel correspondant à un message du robot
  * @param text le contenu du message
  */
class RobotPanel(text: String) extends BoxPanel(Orientation.Horizontal){
    background = new Color (0x444654) // couleur de fond du panel

    // Création des composants
    private val robotMsg = new BubbleText (text, Alignment.Left) // création du label contenant le contenu du message avec Aligment.Left car le robot est à gauche
    private val robotImage = new ImageIcon(new File("doc/mateo2.png").getAbsolutePath).getImage() // création de l'icône contenant la photo du robot
    private val resizedrobotImage = new ImageIcon(robotImage.getScaledInstance(50,50,Image.SCALE_DEFAULT)) // redimensionne l'image
    private val labelRobot = new Label("Mateo | ", resizedrobotImage, Alignment.Center){ // création d'un label avec l'image et le nom du robot
        font = new Font("Arial", Font.BOLD, 15) // choix de la police et de la taille pour le nom du robot
        foreground = new Color (0xa1a2a9) // couleur de la police pour le nom du robot
    }

    // Ajout des composants de manière juxtaposée
    contents += labelRobot // photo et nom du robot en premier pour qu'ils soientt à gauche du message
    contents += robotMsg
}
