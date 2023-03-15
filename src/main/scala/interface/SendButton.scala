package interface

import scala.swing._
import event._

class SendButton(lab: String, to: ResultText, from: InField) extends Button{
  text = lab
  reactions += {
    case ButtonClicked(_) => { to.text = from.text; from.text = ""}
  }
}


