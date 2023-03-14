package interface

import scala.swing._
import java.awt.Color

class InField extends TextField {

  background = Color.BLACK
  foreground = Color.WHITE
  text = ""
  border = Swing.LineBorder(Color.GRAY) 
  
}
