package machine
import org.junit.Test
import org.junit.Assert._
import bdd.BDDImpl
import machine.MachineImpl

class TestIntegration {

  // initialisation des objets sous test
  val m = MachineImpl
  m.reinit

  @Test
  def test1_test(): Unit = {
    assertEquals(
      List("Je ne comprends pas votre demande"),
      MachineImpl.test(List("Place de la Maie"))
    )
  }

  @Test
  def test2_test(): Unit = {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
      MachineImpl.test(List("Place de la Mairie"))
    )
  }

  @Test
  def test3_test(): Unit = {
    assertEquals(
      List(
        "L'adresse de Mairie de Rennes est : Place de la Mairie",
        "Je ne comprends pas votre demande"
      ),
      MachineImpl.test(List("ou se trouve la maire?", "scsdgar??"))
    )
  }

  @Test
  def test4_test(): Unit = {
    assertEquals(
      List("Hablas español?", "Está bien, cuál es tu petición?"),
      MachineImpl.test(List("hola", "si"))
    )
  }
}
