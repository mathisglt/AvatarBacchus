package machine
import org.junit.Test
import org.junit.Assert._
import bdd.BDDImpl

class TestIntegration {

  // initialisation des objets sous test
  val m = MachineImpl
  m.reinit

  @Test
  def test1_bdd(): Unit= {
    assertEquals(
      "Mairie",
      BDDImpl.chercherAdresse("Place de la Mairie")
    )
  }

}
