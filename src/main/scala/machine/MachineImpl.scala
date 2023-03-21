package machine
import construction_result.ConstructionImpl
import analyse_phrase.AnalyseImpl

object MachineImpl extends MachineDialogue {
  def ask(s: String): List[String] = {
    var liste : List[String] = List()
    liste ++ ConstructionImpl.construire(s)
    liste
  }
  
  // Pour la partie test par le client
  def reinit(): Unit = ()
  def test(l: List[String]): List[String] = {
    l.map(x => ConstructionImpl.construire(x))
  }
}