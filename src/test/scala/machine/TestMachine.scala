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
  def test1_test(): Unit= {
    assertEquals(
      List("Je ne comprends pas votre demande"),
      MachineImpl.test(List("Place de la Maie"))
    )
  }

  @Test
  def test2_test(): Unit= {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
      MachineImpl.test(List("Place de la Mairie"))
    )
  }

  @Test
  def test3_test(): Unit= {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie","Je ne comprends pas votre demande"),
      MachineImpl.test(List("ou se trouve la maire?", "scsdgar??"))
    )
  }

  // @Test
  // def test3_test(): Unit= {
  //   assertEquals(
  //     List("L'adresse de Mairie est : Place de la Mairie", "L'adresse de Gare est : 19, Place de la Gare"),
  //     MachineImpl.test(List("Donne place de la Mairie et Gare"))
  //   )
  // }
}
