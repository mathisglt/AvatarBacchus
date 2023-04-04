package machine
import construction_result.ConstructionImpl
import analyse_phrase.AnalyseImpl
import tolerance_fautes.FautesImpl
import langue.LangueImpl

object MachineImpl extends MachineDialogue {

  def ask(s: String): List[String] = {
    println("\nrequete : " + s)
    if (AnalyseImpl.politeTest_OnlyBonjour(s)) "Bonjour" :: Nil
    else if (AnalyseImpl.politeTest_Bonjour(s))
      "Bonjour" :: List(ConstructionImpl.construire(s))
    else List(ConstructionImpl.construire(s))
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    LangueImpl.setLangueActuelle("Français") //réinitialise la langue
  }
  def test(l: List[String]): List[String] = {
    println("Requetes de base : " + l)
    l match {
      case Nil          => Nil
      case head :: next => ask(head) ++ test(next)
    }
  }
}
