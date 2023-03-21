package interface

import scala.swing._
import java.awt.{Color, Font}
import java.awt.geom.RoundRectangle2D


class BubbleText(lab: String) extends Label {
  foreground = Color.BLACK
  text = lab
  background = new Color(0xff2c29)
}
