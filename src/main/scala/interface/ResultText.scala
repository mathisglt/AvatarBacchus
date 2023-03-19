package interface

import scala.swing._
import java.awt.Color


class ResultText extends Label {
  background = Color.WHITE
  foreground = Color.BLACK
  text = ""
  preferredSize = new Dimension (200, 500)
}
