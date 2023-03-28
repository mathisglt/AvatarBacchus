package machine
import construction_result.ConstructionImpl
import analyse_phrase.AnalyseImpl

object MachineImpl extends MachineDialogue {

  
  def ask(s: String): List[String] = {
   List(ConstructionImpl.construire(s))
  }
  
  // Pour la partie test par le client
  def reinit(): Unit = ()
  def test(l: List[String]): List[String] = {
    println(l)
    println(l.map(x => ConstructionImpl.construire(x)))
    l.map(x => ConstructionImpl.construire(x))
  }
}