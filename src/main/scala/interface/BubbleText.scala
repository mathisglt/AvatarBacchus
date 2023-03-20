package interface

import scala.swing._
import java.awt.{Color, Font}
import java.awt.geom.RoundRectangle2D


class BubbleText(lab: String, color: Color) extends Label {

  background = color
  foreground = Color.BLACK

  text = lab

}
