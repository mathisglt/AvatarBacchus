package analyse_phrase
import org.junit.Test
import org.junit.Assert._
import langue.LangueImpl

class TestAnalyse {

  // tests unitaires fonction "analyser"

  @Test
  def test_analyser_01: Unit = {
    assertEquals(
      ("", ""),
      AnalyseImpl.analyser("où et ?")
    )
  }

  @Test
  def test_analyser_02: Unit = {
    assertEquals(
      ("Mairie de Rennes", "Place de la Mairie"),
      AnalyseImpl.analyser("où est la mairie ?")
    )
  }

  @Test
  def test_analyser_03: Unit = {
    assertEquals(
      ("Mairie de Rennes", "Place de la Mairie"),
      AnalyseImpl.analyser("où est la Mairie de Rennes ?")
    )
  }

  @Test
  def test_analyser_04: Unit = {
    assertEquals( // XXX "mairie se trouve avant gare dans la bdd"
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où sont la gare sncf et la mairie ?")
    )
  }

  @Test
  def test_analyser_05: Unit = {
    assertEquals(
      ("", ""),
      AnalyseImpl.analyser("où se trouve Rennes ?")
    )
  }

  @Test
  def test_analyser_055: Unit = {
    assertEquals(
      ("", ""),
      AnalyseImpl.analyser("où se trouve Bretagne ?")
    )
  }

  @Test
  def test_analyser_06: Unit = {
    assertEquals(
      ("Théâtre National de Bretagne", "1, Rue Saint-Hélier"),
      AnalyseImpl.analyser("où se trouvent le tnb et le théâtre de Bretagne ?")
    )
  }

  @Test
  def test_analyser_07: Unit = {
    assertEquals(
      ("Mairie de Rennes", "Place de la Mairie"),
      AnalyseImpl.analyser("où est hôtel de ville")
    )
  }

  @Test // ne marche pas encore
  def test_analyser_08: Unit = {
    assertEquals(
      ("Mairie de Rennes", "Place de la Mairie"),
      AnalyseImpl.analyser("où est l'hôtel de ville")
    )
  }

  @Test
  def test_analyser_09: Unit = {
    assertEquals(
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où se trouve la gare sncf ?")
    )
  }

  @Test
  def test_analyser_10: Unit = {
    assertEquals(
      ("Mairie de Rennes", "Place de la Mairie"),
      AnalyseImpl.analyser("Place de la mairie")
    )
  }

  @Test
  def test_analyser_11: Unit = {
    assertEquals(
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où est la grre")
    )
  }

  @Test
  def test_analyser_12: Unit = {
    assertEquals(
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où est la gre")
    )
  }

  @Test
  def test_analyser_13: Unit = {
    assertEquals(
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où est la G#Ré")
    )
  }

  // tests unitaires de decouper

  @Test
  def test_decouper_1: Unit = {
    assertEquals(
      List(""),
      AnalyseImpl.decouper("")
    )
  }

  @Test
  def test_decouper_2: Unit = {
    assertEquals(
      List("hello"),
      AnalyseImpl.decouper("hello")
    )
  }

  @Test
  def test_decouper_3: Unit = {
    assertEquals(
      List("où", "est", "la", "mairie"),
      AnalyseImpl.decouper("où est la mairie ?")
    )
  }

  // tests unitaires pour assembler

  @Test
  def test_assembler_1: Unit = {
    try {
      AnalyseImpl.assembler(Nil)
      fail();
    } catch {
      case ExceptionListeVide => ()
    }
  }

  @Test
  def test_assembler_2: Unit = {
    assertEquals(
      "salut les amis",
      AnalyseImpl.assembler(List("salut", "les", "amis"))
    )
  }

  // tests unitaires de politeTest_Bonjour

  @Test
  def test_politeTest_1: Unit = {
    assertEquals(
      false,
      AnalyseImpl.politeTest_Bonjour("yo")
    )
  }

  @Test
  def test_politeTest_2: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("bonjour, bonsoir, saluuuuut")
    )
  }

  @Test
  def test_politeTest_3: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("BonJoUr")
    )
  }

  @Test
  def test_politeTest_4: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("bonsoir")
    )
  }

  @Test
  def test_politeTest_5: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("salut")
    )
  }

  @Test
  def test_politeTest_6: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("bnjour")
    )
  }

  @Test
  def test_politeTest_7: Unit = {
    assertEquals(
      true,
      AnalyseImpl.politeTest_Bonjour("banjour")
    )
  }

  // Tests de filtreLiason

  @Test
  def test_filtreLiason_1 {
    assertEquals(
      List("Hôtel", "Ville"),
      AnalyseImpl.filtreLiaison(List("Hôtel", "de", "Ville"))
    )
  }
  @Test
  def test_filtreLiason_2 {
    assertEquals(
      List("Hôtel", "Ville"),
      AnalyseImpl.filtreLiaison(List("Hôtel", "Ville"))
    )
  }
  @Test
  def test_filtreLiason_3 {
    assertEquals(
      Nil,
      AnalyseImpl.filtreLiaison(Nil)
    )
  }
  @Test
  def test_filtreLiason_4 {
    assertEquals(
      List("gare", "Rennes"),
      AnalyseImpl.filtreLiaison(List("de", "gare", "de", "Rennes"))
    )
  }

  // tests unitaire de politeTest_OnlyBonjour

  @Test
  def test_onlyBonjour_1 {
    assertEquals(
      true,
      AnalyseImpl.politeTest_OnlyBonjour("bonjour")
    )
  }

  @Test
  def test_onlyBonjour_2 {
    assertEquals(
      true,
      AnalyseImpl.politeTest_OnlyBonjour("bnjour")
    )
  }

  @Test
  def test_onlyBonjour_3 {
    assertEquals(
      true,
      AnalyseImpl.politeTest_OnlyBonjour("boujour")
    )
  }

  @Test
  def test_onlyBonjour_4 {
    assertEquals(
      true,
      AnalyseImpl.politeTest_OnlyBonjour("salut")
    )
  }

  @Test
  def test_onlyBonjour_5 {
    assertEquals(
      true,
      AnalyseImpl.politeTest_OnlyBonjour("bonsoir")
    )
  }

  @Test
  def test_onlyBonjour_6 {
    assertEquals(
      false,
      AnalyseImpl.politeTest_OnlyBonjour("bonjour salut")
    )
  }

  @Test
  def test_onlyBonjour_7 {
    assertEquals(
      false,
      AnalyseImpl.politeTest_OnlyBonjour("")
    )
  }

  @Test
  def test_onlyBonjour_8 {
    assertEquals(
      false,
      AnalyseImpl.politeTest_OnlyBonjour("bonjourlesamis")
    )
  }

  // tests unitaire de getDicoLangue

  @Test
  def test_getDicoLangue_01 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      List(
        "oui",
        "non",
        "L'adresse de XXX est",
        "Je ne comprends pas votre demande",
        "Parlez-vous français?",
        "D'accord, quelle est votre demande?",
        "J'ai XXX réponses possibles",
        "Quel est votre choix?",
        "restaurant, creperie, pizzeria"
      ),
      AnalyseImpl.getDicoLangue()
    )
  }

  @Test
  def test_getDicoLangue_02 {
    LangueImpl.setLangueActuelle("Anglais")
    assertEquals(
      List(
        "yes",
        "no",
        "The address of XXX is",
        "I do not understand",
        "Do you speak english?",
        "OK, what is your query?",
        "I found XXX answers",
        "What is your choice?",
        "restaurant, creperie, pizzeria"
      ),
      AnalyseImpl.getDicoLangue()
    )
  }

  @Test
  def test_getDicoLangue_03 {
    LangueImpl.setLangueActuelle("Espagnol")
    assertEquals(
      List(
        "si",
        "no",
        "La dirección de XXX es",
        "No comprendo",
        "Hablas español?",
        "Está bien, cuál es tu petición?",
        "Tengo XXX opciones",
        "Cuál es su elección?",
        "restaurante, creperie, pizzeria"
      ),
      AnalyseImpl.getDicoLangue()
    )
  }

  @Test
  def test_getDicoLangue_04 {
    LangueImpl.setLangueActuelle("Allemand")
    assertEquals(
      List(
        "ja",
        "nein",
        "Die adresse von XXX ist",
        "Ich verstehe nicht",
        "Sprechen Sie Deutsch?",
        "Okay, was ist Ihr Wunsch?",
        "Ich habe XXX Antworten",
        "Was ist Ihre Wahl?",
        "restaurant, creperie, pizzeria"
      ),
      AnalyseImpl.getDicoLangue()
    )
  }

  @Test
  def test_getDicoLangue_05 {
    LangueImpl.setLangueActuelle("Italien")
    assertEquals(
      List(
        "si",
        "no",
        "Indirizzo di XXX è",
        "No capisco",
        "Parli italiano?",
        "Va bene, qual è la tua richiesta?",
        "Ho XXX risposte",
        "Qual è la vostra scelta?",
        "ristorante, creperie, pizzeria"
      ),
      AnalyseImpl.getDicoLangue()
    )
  }

  //Test detecLangue

  @Test
  def test_detecLangue_01 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      (false, 0),
      AnalyseImpl.detecLangue("je cherche la mairie")
    )
  }

  @Test
  def test_detecLangue_02 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      (true, 1),
      AnalyseImpl.detecLangue("I am seeking la mairie de Rennes")
    )
  }
}
