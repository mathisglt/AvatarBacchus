package interface

import scala.swing._
import event._
import java.awt.{Color, Font}


class SendButton(lab: String, to: ResultText, from: InField) extends Button{
  text = lab
  foreground = Color.white
  background = new Color(0xff2c29)
  font = new Font("Arial", Font.BOLD, 16)
  borderPainted = false
  border
  focusPainted = false
  contentAreaFilled = true
  opaque = true
  preferredSize = new Dimension(120, 40)
  listenTo(from.keys)
  reactions += {
    case ButtonClicked(_) | KeyPressed(_, Key.Enter, _, _) => { to.text = from.text; from.text = ""}
  }
}

