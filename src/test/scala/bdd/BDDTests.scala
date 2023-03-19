package bdd

import org.junit.Test
import org.junit.Assert._

class TestUnitaire{
    
    @Test
    def testchercheradresseInconnuv1():Unit ={
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("gdfcghdf"))
    def testchercheradresseConnuv1():Unit ={
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("Mairie de Rennes"))
    }
}

}