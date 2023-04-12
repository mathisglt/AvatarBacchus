package langue

import org.junit.Test
import org.junit.Assert._

class TestLangue {
  @Test
  def getLangueActuelle_1(): Unit = {
    LangueImpl.reinitLangue()
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
  @Test
  def getLangueActuelle_2(): Unit = {
    LangueImpl.reinitLangue()
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
  @Test
  def getLangueActuelle_3(): Unit = {
    LangueImpl.reinitLangue()
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
  @Test
  def langueSuivante_3(): Unit = {
    LangueImpl.reinitLangue()
    LangueImpl.langueSuivante()
    assertEquals(1, LangueImpl.getLangueActuelle())
  }
  @Test
  def langueSuivante_1(): Unit = {
    LangueImpl.reinitLangue()
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
    LangueImpl.reinitLangue()
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
  def langueSuivante_4(): Unit = {
    LangueImpl.reinitLangue()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.reinitLangue()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    LangueImpl.langueSuivante()
    assertEquals(0, LangueImpl.getLangueActuelle())
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
  @Test
  def setLangueActuelle_5(): Unit = {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(0, LangueImpl.getLangueActuelle())
  }
}
