package interface

import scala.swing._
import java.awt.{Color, Font}
import java.awt.geom.RoundRectangle2D


class BubbleText(color: Color) extends Label {
  
  private val radius = 10
  private val padding = 5

  text = ""

  background = color
  foreground = Color.BLACK
  preferredSize = new Dimension(200, 50)

   override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    val r = new RoundRectangle2D.Float(padding, padding, size.width - padding * 2, size.height - padding * 2, radius, radius)
    g.setColor(color)
    g.fill(r)
  }

}
