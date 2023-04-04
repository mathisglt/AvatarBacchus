package bdd
import org.junit.Test
import org.junit.Assert._

class TestBDD {
   BDDImpl.createDicoPRN()

   @Test
   def testchercheradresseInconnuv1{
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("gdfcghdf"))
   }
    @Test
    def testchercheradresseConnuv1{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("Mairie"))
    } 
    @Test
    def testchercheradresseConnuv2{
       assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("Gare"))
    }
    @Test
    def testchercheradresseConnuVariante1{
       assertEquals("1, Rue Saint-Hélier",BDDImpl.chercherAdresse("tnb"))
    }
    @Test
    def testchercheradresseConnuVariante2{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("hotel"))
    }
    @Test
    def testchercheradresseConnuv1Minuscules{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("mairie"))
    }
    @Test
    def testchercheradresseConnuv1Majuscules{
       assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("MAIRIE"))
    }
    
   @Test
    def testchercheradresseConnuv2Minuscules{
       assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("gare"))
    }
    @Test
    def testchercheradresseConnuv2Majuscules{
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
      @Test
    def testchercheradresseSpace{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse(" "))
    }
     @Test
    def testchercheradresseVoid{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse(""))
    }
    //askdhlkajh
    @Test
    def testchercheradressebug1{
       assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("askdhlkajh"))
    }
   @Test
    def testchercheradressebug2{
       assertEquals("Adresse non trouvée",BDDImpl.chercherLieu("askdhlkajh"))
    }
    @Test
    def testchercheradressebug3{
       assertEquals("1, Rue Saint-Hélier",BDDImpl.chercherAdresse("tnb"))
    }

    // F4 - F8

    @Test
    def testlanguedumotPolitesse1{
       assertEquals("français",BDDImpl.langueDuMot("bonjour"))
    }
    @Test
    def testlanguedumotPolitesse2{
       assertEquals("english",BDDImpl.langueDuMot("hello"))
    }
    @Test
    def testlanguedumotPolitesse3{
       assertEquals("español",BDDImpl.langueDuMot("hola"))
    }
    @Test
    def testlanguedumotPolitesse4{
       assertEquals("deutsch",BDDImpl.langueDuMot("guten"))
    }
    @Test
    def testlanguedumotPolitesse5{
       assertEquals("italiano",BDDImpl.langueDuMot("incantato"))
    }

    @Test
    def testlanguedumotRecherche1{
       assertEquals("français",BDDImpl.langueDuMot("recherche"))
    }
    @Test
    def testlanguedumotRecherche2{
       assertEquals("english",BDDImpl.langueDuMot("seeking"))
    }
    @Test
    def testlanguedumotRecherche3{
       assertEquals("español",BDDImpl.langueDuMot("busco"))
    }
    @Test
    def testlanguedumotRecherche4{
       assertEquals("deutsch",BDDImpl.langueDuMot("suchen"))
    }
    @Test
    def testlanguedumotRecherche5{
       assertEquals("italiano",BDDImpl.langueDuMot("cercando"))
    }

}
