package bdd
import org.junit.Test
import org.junit.Assert._

class BDDTest {

   @Test
   def testchercheradresseInconnuv1: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("sjdchbsdbb"))
   }
   @Test
   def testchercheradresseConnuv1: Unit = {
      assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("Mairie"))
   }
   @Test
   def testchercheradresseConnuv2: Unit = {
      assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("Gare"))
   }
   @Test
   def testchercheradresseConnuVariante1: Unit = {
      assertEquals("1, Rue Saint-Hélier",BDDImpl.chercherAdresse("tnb"))
   }
   @Test
   def testchercheradresseConnuVariante2: Unit = {
      assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("hotel"))
   }
   @Test
   def testchercheradresseConnuv1Minuscules: Unit = {
      assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("mairie"))
   }
   @Test
   def testchercheradresseConnuv1Majuscules: Unit = {
      assertEquals("Place de la Mairie",BDDImpl.chercherAdresse("MAIRIE"))
   }
   @Test
   def testchercheradresseConnuv2Minuscules: Unit = {
      assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("gare"))
   }
   @Test
   def testchercheradresseConnuv2Majuscules: Unit = {
      assertEquals("19, Place de la Gare",BDDImpl.chercherAdresse("GARE"))
   }
   @Test
   def testchercheradresseBanword1: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("de"))
   }
   @Test
   def testchercheradresseBanword2: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("des"))
   }
   @Test
   def testchercheradresseBanword3: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("LE"))
   }
   @Test
   def testchercheradresseSpace: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse(" "))
   }
   @Test
   def testchercheradresseVoid: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse(""))
   }
   
   @Test
   def testchercheradressebug1: Unit = {
      assertEquals("Adresse non trouvée",BDDImpl.chercherAdresse("askdhlkajh"))
   }
   @Test
   def testchercheradressebug2: Unit = {
      assertEquals("Lieu non trouvé",BDDImpl.chercherLieu("askdhlkajh"))
   }
   @Test
   def testchercheradressebug3: Unit = {
      assertEquals("1, Rue Saint-Hélier",BDDImpl.chercherAdresse("tnb"))
   }

    // F4 - F8

   @Test
   def testlanguedumotPolitesse1: Unit = {
      assertEquals("français",BDDImpl.langueDuMot("bonjour"))
   }
   @Test
   def testlanguedumotPolitesse2: Unit = {
      assertEquals("english",BDDImpl.langueDuMot("hello"))
   }
   @Test
   def testlanguedumotPolitesse3: Unit = {
      assertEquals("español",BDDImpl.langueDuMot("hola"))
   }
   @Test
   def testlanguedumotPolitesse4: Unit = {
      assertEquals("deutsch",BDDImpl.langueDuMot("guten"))
   }
   @Test
   def testlanguedumotPolitesse5: Unit = {
      assertEquals("italiano",BDDImpl.langueDuMot("incantato"))
   }

   @Test
   def testlanguedumotRecherche1: Unit = {
      assertEquals("français",BDDImpl.langueDuMot("recherche"))
   }
   @Test
   def testlanguedumotRecherche2: Unit = {
      assertEquals("english",BDDImpl.langueDuMot("seeking"))
   }
   @Test
   def testlanguedumotRecherche3: Unit = {
      assertEquals("español",BDDImpl.langueDuMot("busco"))
   }
   @Test
   def testlanguedumotRecherche4: Unit = {
      assertEquals("deutsch",BDDImpl.langueDuMot("suchen"))
   }
   @Test
   def testlanguedumotRecherche5: Unit = {
      assertEquals("italiano",BDDImpl.langueDuMot("cercando"))
   }

   @Test 
   def createDicoSalutations1: Unit = {
      assertEquals(BDDImpl.createDicoSalutations("Français",0),List("bonjour","salut","bonsoir"))
   }

   @Test 
   def createDicoSalutations2: Unit = {
      assertEquals(BDDImpl.createDicoSalutations("Anglais",1),List("hi","hello","morning","evening","afternoon","hey"))
   }

   @Test 
   def createDicoSalutations3: Unit = {
      assertEquals(BDDImpl.createDicoSalutations("Espagnol",2),List("hola","buenos","dias"))
   }

   @Test
   def createDicoExpr1: Unit = {
      assertEquals(List(List("oui", "non", "L'adresse de XXX est", "Je ne comprends pas votre demande", "Parlez-vous français?", "D'accord, quelle est votre demande?", "J'ai XXX réponses possibles", "Quel est votre choix?", "restaurant, creperie, pizzeria"), List("yes", "no", "The address of XXX is", "I do not understand", "Do you speak english?", "OK, what is your query?", "I found XXX answers", "What is your choice?", "restaurant, creperie, pizzeria"), List("si", "no", "La dirección de XXX es", "No comprendo", "Hablas español?", "Está bien, cuál es tu petición?", "Tengo XXX opciones", "Cuál es su elección?", "restaurante, creperie, pizzeria"), List("ja", "nein", "Die adresse von XXX ist", "Ich verstehe nicht", "Sprechen Sie Deutsch?", "Okay, was ist Ihr Wunsch?", "Ich habe XXX Antworten", "Was ist Ihre Wahl?", "restaurant, creperie, pizzeria"), List("si", "no", "Indirizzo di XXX è", "No capisco", "Parli italiano?", "Va bene, qual è la tua richiesta?", "Ho XXX risposte", "Qual è la vostra scelta?", "ristorante, creperie, pizzeria")),BDDImpl.getDicoExpr())
   }
   @Test
   def createDicoExprfr: Unit = {
      assertEquals(List("oui", "non", "L'adresse de XXX est", "Je ne comprends pas votre demande", "Parlez-vous français?", "D'accord, quelle est votre demande?", "J'ai XXX réponses possibles", "Quel est votre choix?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(0))
   }
   @Test
   def createDicoExpren: Unit = {
      assertEquals(List("yes", "no", "The address of XXX is", "I do not understand", "Do you speak english?", "OK, what is your query?", "I found XXX answers", "What is your choice?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(1))
   }
   @Test
   def createDicoExpres: Unit = {
      assertEquals(List("si", "no", "La dirección de XXX es", "No comprendo", "Hablas español?", "Está bien, cuál es tu petición?", "Tengo XXX opciones", "Cuál es su elección?", "restaurante, creperie, pizzeria"),BDDImpl.getDicoExpr()(2))
   }
   @Test
   def createDicoExprde: Unit = {
      assertEquals(List("ja", "nein", "Die adresse von XXX ist", "Ich verstehe nicht", "Sprechen Sie Deutsch?", "Okay, was ist Ihr Wunsch?", "Ich habe XXX Antworten", "Was ist Ihre Wahl?", "restaurant, creperie, pizzeria"),BDDImpl.getDicoExpr()(3))
   }
   @Test
   def createDicoExprit: Unit = {
      assertEquals(List("si", "no", "Indirizzo di XXX è", "No capisco", "Parli italiano?", "Va bene, qual è la tua richiesta?", "Ho XXX risposte", "Qual è la vostra scelta?", "ristorante, creperie, pizzeria"),BDDImpl.getDicoExpr()(4))
   }


   @Test
   def createDicoPRN1: Unit = {
      assertEquals(List(List("bonjour", "salut", "bonsoir", "recherche", "cherche", "ou", "est", "donc", "trouve", "trouver", "français"), List("hi", "hello", "morning", "evening", "afternoon", "hey", "seek", "seeking", "search", "searching", "look", "looking", "where", "find", "english"), List("hola", "buenos", "dias", "donde", "esta", "busco", "buscando", "español"), List("hallo", "guten", "morgen", "tag", "abend", "wo", "ist", "suche", "suchen", "deutsch"), List("buongiorno", "ciao", "salve", "buon", "pomeriggio", "buonasera", "incantato", "dove", "trova", "cerco", "cercando", "italiano")),BDDImpl.getDicoPRN())
   }
   @Test
   def xml1erelement: Unit = {
      assertEquals(("Direction habitat social","1, Place de la Communauté"),BDDImpl.createListFromXML().head)
   }
   @Test
   def xmldernierelement: Unit = {
      assertEquals(("Xylocus","JARDIN DU SECHOIR"),BDDImpl.createListFromXML().last)
   }
   

   @Test
   def newchercherlieu1: Unit = {
      assertEquals("Mairie de Rennes",BDDImpl.chercherLieu("Mairie"))
   }

   @Test
   def newchercherlieu2: Unit = {
      assertEquals("40mcube",BDDImpl.chercherLieu("40mcube"))
   }

   @Test
   def newchercherlieu3: Unit = {
      assertEquals("Clair Obscur",BDDImpl.chercherLieu("Clair Obscur"))
   }

   @Test
   def newchercheradresse1xml: Unit = {
      assertEquals("48, AVENUE SERGENT MAGINOT",BDDImpl.chercherAdresse("40mcube"))
   }

   @Test
   def newchercheradresse2xml: Unit = {
      assertEquals("5, RUE DE LORRAINE,RUE DE LORRAINE",BDDImpl.chercherAdresse("clair obscur"))
   }
   @Test
   def newchercheradresse3xml: Unit = {
      assertEquals("103, RUE DE LORIENT,RUE DE LORIENT",BDDImpl.chercherAdresse("Ecole maternelle Moulin du Comte"))
   }
   @Test
   def newchercheradresse4xml: Unit = {
      assertEquals("7, QUAI CHATEAUBRIAND,QUAI CHATEAUBRIAND",BDDImpl.chercherAdresse("consulat honoraire espagne"))
   }
   @Test
   def newchercheradresse5xmlaccents: Unit = {
      assertEquals("10, Boulevard Albert 1er",BDDImpl.chercherAdresse("Piscine de Bréquigny"))
   }
  @Test
  def testRemoveLiaisonAccentsWords1(): Unit = {
    val phrasedetest = "C'est un test"
    val réponse = "c'est test"
    assertEquals(réponse, BDDImpl.removeLiaisonAccentsWords(phrasedetest))
  }

  @Test
  def testRemoveLiaisonAccentsWords2(): Unit = {
    val phrasedetest = "De la nourriture et des boissons"
    val réponse = "nourriture boissons"
    assertEquals(réponse, BDDImpl.removeLiaisonAccentsWords(phrasedetest))
  }

  @Test
  def testRemoveLiaisonAccentsWords3(): Unit = {
    val phrasedetest = "Une voiture rouge et un camion bleu"
    val réponse = "voiture rouge camion bleu"
    assertEquals(réponse, BDDImpl.removeLiaisonAccentsWords(phrasedetest))
  }

  @Test
  def testRemoveLiaisonAccentsWords4(): Unit = {
    val phrasedetest = "L'artiste a créé une œuvre magnifique"
    val réponse = "l'artiste a cree œuvre magnifique"
    assertEquals(réponse, BDDImpl.removeLiaisonAccentsWords(phrasedetest))
  }

  @Test
  def testRemoveLiaisonAccentsWords5(): Unit = {
    val phrasedetest = "Le chat noir et la souris blanche"
    val réponse = "chat noir souris blanche"
    assertEquals(réponse, BDDImpl.removeLiaisonAccentsWords(phrasedetest))
  }
}
