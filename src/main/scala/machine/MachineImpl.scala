package machine

object MachineImpl extends MachineDialogue {
  def ask(s: String): List[String] = ???

  // Pour la partie test par le client
  def reinit(): Unit = ???
  def test(l: List[String]): List[String] = ???
}
