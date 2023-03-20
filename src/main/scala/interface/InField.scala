package interface

import scala.swing._
import java.awt.{Color, Font}

class InField extends TextField {

  background = Color.WHITE
  foreground = Color.BLACK
  text = ""
  border = Swing.LineBorder(Color.GRAY) 
  font = new Font("Arial", Font.BOLD, 16)
}
