package bdd
import org.junit.Test
import org.junit.Assert._

class TestBDD{
    
    @Test
    def testchercheradresseInconnuv1{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("gdfcghdf"))
    }

    @Test   
    def testchercheradresseConnuv1{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("Mairie de Rennes"))
    }
    @Test
    def testchercheradresseConnuv2{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("Mairie"))
    } 
    @Test
    def testchercheradresseConnuv2Minuscules{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("mairie"))
    }
    @Test
    def testchercheradresseConnuv2Majuscules{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("MAIRIE"))
    }
    @Test
    def testchercheradresseConnuv3{
       assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("Gare"))
    }
   @Test
    def testchercheradresseConnuv3Minuscules{
       assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("gare"))
    }
    @Test
    def testchercheradresseConnuv3Majuscules{
       assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("GARE"))
    }
        @Test
    def testchercheradresseBanword1{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("de"))
    }
         @Test
    def testchercheradresseBanword2{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("des"))
    }
        @Test
    def testchercheradresseBanword3{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("LE"))
    }
    
   
}
