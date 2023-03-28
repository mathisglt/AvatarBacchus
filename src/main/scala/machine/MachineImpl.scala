package machine
import construction_result.ConstructionImpl
import analyse_phrase.AnalyseImpl

object MachineImpl extends MachineDialogue {

  
  def ask(s: String): List[String] = {
    println(s)
    if (AnalyseImpl.politeTest_Bonjour(s)) "Bonjour" :: List(ConstructionImpl.construire(s))
    else List(ConstructionImpl.construire(s))
  }
  
  // Pour la partie test par le client
  def reinit(): Unit = ()
  def test(l: List[String]): List[String] = {
    l match {
      case Nil => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}