package interface

import scala.swing._
import javax.swing.ImageIcon
import java.awt.{Color, Font, Image}
import java.io.File

/**
  * Création du panel correspondant à un message de l'utilisateur
  * @param text le contenu du message
  */
class UserPanel(text: String) extends BoxPanel(Orientation.Horizontal){
    background = new Color (0x1d1e20) // couleur de fond du panel (différente de celle du robot pour une meilleure lisibilité)
    
    private val userMsg = new BubbleText (text,Alignment.Right) // création du label contenant le contenu du message avec Aligment.Right car l'utilisateur est à droite
    private val userImage = new ImageIcon(new File("doc/Logo.png").getAbsolutePath).getImage() // création de l'icône contenant la photo de l'utilisateur
    private val resizeduserImage = new ImageIcon(userImage.getScaledInstance(50,50,Image.SCALE_DEFAULT)) // redimensionne l'image
    private val labelUser = new Label(" | Vous", resizeduserImage, Alignment.Center){ // création d'un label avec l'image de l'utilisateur
        font = new Font("Arial", Font.BOLD, 15) // choix de la police et de la taille pour le "Vous"
        horizontalTextPosition = Alignment.Left // sert à inverser le label pour que le "Vous" soit à gauche de la photo
        foreground = new Color (0xa1a2a9) // couleur de la police pour le "Vous"
    }
    
    // Ajout des composants de manière juxtaposée
    contents += userMsg
    contents += labelUser // photo et "Vous" en deuxième pour qu'ils soientt à droite du message
}
