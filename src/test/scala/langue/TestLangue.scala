package langue

import org.junit.Test
import org.junit.Assert._

class TestLangue {
  @Test
  def getLangueActuelle(): Unit = {
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
  @Test
  def langueSuivante_1(): Unit = {
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    assertEquals(1, LangueImpl.getLangueActuelle())
  }
  @Test
  def langueSuivante_2(): Unit = {
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    assertEquals(2, LangueImpl.getLangueActuelle())
  }
  @Test
  def langueSuivante_3(): Unit = {
    LangueImpl.langueSuivante()
    assertEquals(1, LangueImpl.getLangueActuelle())
  }

  @Test
  def setLangueActuelle_1(): Unit = {
    LangueImpl.setLangueActuelle("Espagnol")
    assertEquals(2, LangueImpl.getLangueActuelle())
  }
  @Test
  def setLangueActuelle_2(): Unit = {
    LangueImpl.setLangueActuelle("Chinois")
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
  @Test
  def setLangueActuelle_3(): Unit = {
    LangueImpl.setLangueActuelle("Italien")
    assertEquals(4, LangueImpl.getLangueActuelle())
  }
  @Test
  def setLangueActuelle_4(): Unit = {
    LangueImpl.setLangueActuelle("Allemand")
    assertEquals(3, LangueImpl.getLangueActuelle())
  }
}
