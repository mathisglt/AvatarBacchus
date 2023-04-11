package machine
import construction_result.ConstructionImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {

  def ask(s: String): List[String] = {
    println("\nrequete : " + s)
    ConstructionImpl.construirePolitesse(s)
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    LangueImpl.reinitLangue //réinitialise la langue
  }
  def test(l: List[String]): List[String] = {
    println("Requetes de base : " + l)
    l match {
      case Nil          => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}
