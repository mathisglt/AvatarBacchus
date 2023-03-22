package interface

import scala.swing._
import java.awt.{Color, Font}

/**
  * Création de la zone de texte ou l'utilisateur peut taper son message
  */
class InField extends TextField {
  background = new Color(0xC6C7CB)         // couleur du fond
  foreground = Color.BLACK                 // couleur de police
  text = ""                                // on initialise la zone de texte à vide
  border = null                            // pas de bordure
  font = new Font("Arial", Font.BOLD, 16)  // choix de la police et de la taille
  maximumSize = new Dimension(900,40)      // taille max de la zone de texte
}
