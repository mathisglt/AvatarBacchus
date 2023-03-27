package tolerance_fautes
import tolerance_fautes.FautesImpl
import org.junit.Test
import org.junit.Assert._

class TestTolerance {
    val listeTest:List[String]=List("Ou","est","la","mAirie")
    @Test
  def Test1_enleveAccent(): Unit ={
    assertEquals("manger",FautesImpl.enleveAccent("mànger"))
  }
    @Test
  def Test2_enleveAccent(): Unit ={
    assertEquals("manger",FautesImpl.enleveAccent("manger"))
  }
    @Test
  def Test3_enleveAccent(): Unit ={
    assertEquals("ara",FautesImpl.enleveAccent("àrâ"))
  }
    @Test
  def Test1_clearAccentToMaj(): Unit ={
    assertEquals(List("OU","EST","LA","MAIRIE"),FautesImpl.clearAccentToMaj(List("Où","est","LA","mâirie")))
  }
    @Test
  def Test2_clearAccentToMaj(): Unit ={
    assertEquals(List(),FautesImpl.clearAccentToMaj(List()))
  }
     @Test
  def Test1_testDecalage(): Unit ={
    assertTrue(FautesImpl.testDecalage("tetdedecal","testdedecal"))
  }
    @Test
  def Test2_testDecalage(): Unit ={
    assertTrue(FautesImpl.testDecalage("oui","oui"))
  }
    @Test
  def Test3_testDecalage(): Unit ={
    assertFalse(FautesImpl.testDecalage("oui","oune"))
  }
     @Test
  def Test1_test1FauteMax(): Unit ={
    assertTrue(FautesImpl.test1FauteMax("oui","oùi"))
  }
    @Test
  def Test2_test1FauteMax(): Unit ={
    assertTrue(FautesImpl.test1FauteMax("oui","oui"))
  }
    @Test
  def Test3_test1FauteMax(): Unit ={
    assertFalse(FautesImpl.test1FauteMax("oui","non"))
  }
    @Test
  def Test1_distanceDeHammingInf1():Unit={
    assertTrue(FautesImpl.distanceDeHammingInf1("oui","oui"))
  }
    @Test
  def Test2_distanceDeHammingInf1():Unit={
    assertTrue(FautesImpl.distanceDeHammingInf1("oui","ouin"))
  }
   @Test
  def Test3_distanceDeHammingInf1():Unit={
    assertFalse(FautesImpl.distanceDeHammingInf1("ouin","oui"))
  }
   @Test
  def Test4_distanceDeHammingInf1():Unit={
    assertFalse(FautesImpl.distanceDeHammingInf1("oui","non"))
  }
   @Test
   def Test5_distanceDeHammingInf1():Unit={
    assertTrue(FautesImpl.distanceDeHammingInf1("mAirie","mAirie"))
   }
   @Test
  def Test1_testChaqueMot():Unit={
    assertEquals(-1,FautesImpl.testChaqueMot("non",listeTest))
  }
   @Test
  def Test2_testChaqueMot():Unit={
    assertEquals(0,FautesImpl.testChaqueMot("Ou",listeTest))
  }
   @Test
  def Test3_testChaqueMot():Unit={
    assertEquals(3,FautesImpl.testChaqueMot("mAirie",listeTest))//ne marche pas
  }
   @Test
  def Test4_testChaqueMot():Unit={
    assertEquals(-1,FautesImpl.testChaqueMot("non",List()))
  }
   @Test
  def Test1_correction():Unit={//ne marche pas surement à cause de la partie boucle de correction lors de la reconstruction de la liste
    assertEquals(List("Ou","est","la","Mairie"),FautesImpl.correction(listeTest))
  }
  def Test2_correction():Unit={
    assertEquals(List(),FautesImpl.correction(List()))
  }
  def Test3_correction():Unit={
    assertEquals(List("oui"),FautesImpl.correction(List("oui")))
  }
  def Test4_correction():Unit={
    assertEquals(List("Mairie","Gare","Gare"),FautesImpl.correction(List("mairie","gare","gae")))
  }
}