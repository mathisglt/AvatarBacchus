package machine
import construction_result.ConstructionImpl
import analyse_phrase.AnalyseImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {

  
  def ask(s: String): List[String] = {
    println("\nrequete : " + s)
    if (AnalyseImpl.politeTest_OnlyBonjour(s)) "Bonjour"::Nil
    else if (AnalyseImpl.politeTest_Bonjour(s)) "Bonjour" :: List(ConstructionImpl.construire(s,LangueImpl.getLangueActuelle()))
    else List(ConstructionImpl.construire(s,LangueImpl.getLangueActuelle()))
  }
  
  // Pour la partie test par le client
  def reinit(): Unit = ()
  def test(l: List[String]): List[String] = {
    println("Requetes de base : " + l)
    l match {
      case Nil => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}