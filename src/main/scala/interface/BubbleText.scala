package interface

import scala.swing._
import java.awt.{Color, Font}
import java.awt.geom.RoundRectangle2D


class BubbleText(texte: String, align: Alignment.Value) extends Label {
  foreground = Color.WHITE
  text = texte
  font = new Font("Arial", Font.BOLD, 15)
  maximumSize = new Dimension(Short.MaxValue,45)
  horizontalAlignment = align
}
