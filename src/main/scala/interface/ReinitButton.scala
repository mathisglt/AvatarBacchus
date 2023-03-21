package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineDialogue
import machine.MachineImpl


class ReinitButton(conv: Array[BoxPanel]) extends Button{
  foreground = Color.white
  background = new Color(0xff2c29)
  font = new Font("Arial", Font.BOLD, 16)
  borderPainted = false
  focusPainted = false
  contentAreaFilled = true
  opaque = true
  text = "Réinitialiser la conversation"
  reactions +={
    case ButtonClicked(_) => conv.dropRight(conv.length-1)
  }
}

