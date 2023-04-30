package interface

import scala.swing._
import java.awt.{Color, Font}

/**
  * création des labels correspondants aux messages du robot et de l'utilisateur
  * @param texte le contenu du message
  * @param align correspond au côté ou mettre le message 
  *             (ex: Right quand il s'agit d'un message de l'utilisateur)
  */
class BubbleText(texte: String, align: Alignment.Value) extends Label {
  foreground = Color.WHITE                       // couleur de la police
  text = texte                                   // contenu du message
  font = new Font("Arial", Font.BOLD, 15)        // choix de la police de sa taille
  maximumSize = new Dimension(Short.MaxValue,45) // dimension du message (Short.MaxValue sert à utiliser la largeur max possible)
  horizontalAlignment = align                    // choix du côté du message
}
