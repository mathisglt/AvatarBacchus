package interface

import scala.swing._
import event._
import java.awt.{Color, Font}
import machine.MachineDialogue
import machine.MachineImpl

class SendButton(lab: String, conv: BoxPanel,scrollBar: ScrollBar, from: InField) extends Button{
  text = lab
  maximumSize = new Dimension(120,40)
  foreground = Color.white
  background = new Color(0xff2c29)
  font = new Font("Arial", Font.BOLD, 16)
  borderPainted = false
  focusPainted = false
  contentAreaFilled = true
  opaque = true
  listenTo(from.keys)
  reactions += {
  case ButtonClicked(_) | KeyPressed(_, Key.Enter, _, _) if(from.text != "")=> {conv.contents += new UserPanel(from.text); 
                                                                                conv.contents += new RobotPanel(MachineImpl.ask(from.text).head);
                                                                                from.text = "";
                                                                                scrollToBottom();
                                                                                conv.revalidate;}
  }
  
  def scrollToBottom() {
      scrollBar.peer.setValue(scrollBar.peer.getMaximum+1)
  }
}

