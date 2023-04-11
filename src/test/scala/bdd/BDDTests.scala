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
    @Test
    def createDicoExpr1{
       assertEquals(List(List("oui", "non", "L'adresse de XXX est", "Je ne comprends pas votre demande", "Parlez-vous français?", "D'accord, quelle est votre demande?", "J'ai XXX réponses possibles", "Quel est votre choix?", "restaurant, creperie, pizzeria"), List("yes", "no", "The address of XXX is", "I do not understand", "Do you speak english?", "OK, what is your query?", "I found XXX answers", "What is your choice?", "restaurant, creperie, pizzeria"), List("si", "no", "La dirección de XXX es", "No comprendo", "Hablas español?", "Está bien, cuál es tu petición?", "Tengo XXX opciones", "Cuál es su elección?", "restaurante, creperie, pizzeria"), List("ja", "nein", "Die adresse von XXX ist", "Ich verstehe nicht", "Sprechen Sie Deutsch?", "Okay, was ist Ihr Wunsch?", "Ich habe XXX Antworten", "Was ist Ihre Wahl?", "restaurant, creperie, pizzeria"), List("si", "no", "Indirizzo di XXX è", "No capisco", "Parli italiano?", "Va bene, qual è la tua richiesta?", "Ho XXX risposte", "Qual è la vostra scelta?", "ristorante, creperie, pizzeria")),BDDImpl.getDicoExpr())
    }
    @Test
    def createDicoExprfr{
       assertEquals(List("oui", "non", "L'adresse de XXX est", "Je ne comprends pas votre demande", "Parlez-vous français?", "D'accord, quelle est votre demande?", "J'ai XXX réponses possibles", "Quel est votre choix?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(0))
    }
    @Test
    def createDicoExpren{
       assertEquals(List("yes", "no", "The address of XXX is", "I do not understand", "Do you speak english?", "OK, what is your query?", "I found XXX answers", "What is your choice?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(1))
    }
    @Test
    def createDicoExpres{
       assertEquals(List("si", "no", "La dirección de XXX es", "No comprendo", "Hablas español?", "Está bien, cuál es tu petición?", "Tengo XXX opciones", "Cuál es su elección?", "restaurante, creperie, pizzeria"),BDDImpl.getDicoExpr()(2))
    }
    @Test
    def createDicoExprde{
       assertEquals(List("ja", "nein", "Die adresse von XXX ist", "Ich verstehe nicht", "Sprechen Sie Deutsch?", "Okay, was ist Ihr Wunsch?", "Ich habe XXX Antworten", "Was ist Ihre Wahl?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(3))
    }
    @Test
    def createDicoExprit{
       assertEquals(List("si", "no", "Indirizzo di XXX è", "No capisco", "Parli italiano?", "Va bene, qual è la tua richiesta?", "Ho XXX risposte", "Qual è la vostra scelta?", "ristorante, creperie, pizzeria"),BDDImpl.getDicoExpr()(4))
    }


    @Test
    def createDicoPRN1{
       assertEquals(List(List("bonjour", "salut", "bonsoir", "recherche", "cherche", "ou", "est", "donc", "trouve", "trouver", "français"), List("hi", "hello", "morning", "evening", "afternoon", "hey", "seek", "seeking", "search", "searching", "look", "looking", "where", "find", "english"), List("hola", "buenos", "dias", "donde", "esta", "busco", "buscando", "español"), List("hallo", "guten", "morgen", "tag", "abend", "wo", "ist", "suche", "suchen", "deutsch"), List("buongiorno", "ciao", "salve", "buon", "pomeriggio", "buonasera", "incantato", "dove", "trova", "cerco", "cercando", "italiano")),BDDImpl.getDicoPRN())
    }



}
