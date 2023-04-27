package analyse_phrase
import org.junit.Test
import org.junit.Assert._
import langue.LangueImpl
import bdd.BDDImpl

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
    // note : gare sncf privilégié car formé de 2 mots contre 1 mot pour mairie
    // le programme décide donc qu'il y a plus de chance que le user veuille trouver la gare
    assertEquals( 
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
      AnalyseImpl.analyser("où est la gar")
    )
  }

  @Test
  def test_analyser_13: Unit = {
    assertEquals(
      ("Gare SNCF", "19, Place de la Gare"),
      AnalyseImpl.analyser("où est la G#Ré")
    )
  }

  @Test
  def test_analyser_14: Unit = {
    assertEquals(
      ("GIP bretagne environnement","6, RUE DU BIGNON,RUE DU BIGNON"),
      AnalyseImpl.analyser("je cherche le gip bretagne environnement")
    )
  }

  @Test
  def test_analyser_15: Unit = {
    assertEquals(
      ("GIP bretagne environnement","6, RUE DU BIGNON,RUE DU BIGNON"),
      AnalyseImpl.analyser("je cherche le ip retagne nvironnement")
    )
  }

  @Test
  def test_analyser_16: Unit = {
    assertEquals(
      ("GIP bretagne environnement","6, RUE DU BIGNON,RUE DU BIGNON"),
      AnalyseImpl.analyser("je cherche le tip tretagne tnvironnement")
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
    assertEquals(
      "",
      AnalyseImpl.assembler(List())
    )
  }

  @Test
  def test_assembler_2: Unit = {
    assertEquals(
      "salut les amis",
      AnalyseImpl.assembler(List("salut", "les", "amis"))
    )
  }

  // tests unitaires de politeTest_Bonjour
  LangueImpl.setLangueActuelle("Français")

  @Test
  def test_politeTest_01: Unit = {
    assertEquals(
      (false,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("yo")
    )
  }

  @Test
  def test_politeTest_02: Unit = {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("BonJoUr")
    )
  }

  @Test
  def test_politeTest_03: Unit = {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("bonsoir")
    )
  }

  @Test
  def test_politeTest_04: Unit = {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("salut")
    )
  }

  @Test
  def test_politeTest_05: Unit = {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("bnjour")
    )
  }

  @Test
  def test_politeTest_06: Unit = {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_Bonjour("banjour")
    )
  }

  LangueImpl.setLangueActuelle("Anglais") // pour les tests suivants on essaye dans une autre langue

  @Test
  def test_politeTest_08: Unit = {
    LangueImpl.setLangueActuelle("Anglais");
    assertEquals(
      (true,"hi"),
      AnalyseImpl.politeTest_Bonjour("hello")
    )
  }

  @Test
  def test_politeTest_09: Unit = {
    LangueImpl.setLangueActuelle("Espagnol");
    assertEquals(
      (true,"hola"),
      AnalyseImpl.politeTest_Bonjour("hola")
    )
  }

  @Test
  def test_politeTest_10: Unit = {
    LangueImpl.setLangueActuelle("Anglais");
    assertEquals(
      (true,"hi"),
      AnalyseImpl.politeTest_Bonjour("hella")
    )
  }

  LangueImpl.setLangueActuelle("Français") // on remet la langue par defaut pour les prochains tests

  // Tests de filtreLiason

  @Test
  def test_filtreLiason_01 {
    assertEquals(
      List("Hôtel", "Ville"),
      AnalyseImpl.filtreLiaison(List("Hôtel", "de", "Ville"))
    )
  }
  @Test
  def test_filtreLiason_02 {
    assertEquals(
      List("Hôtel", "Ville"),
      AnalyseImpl.filtreLiaison(List("Hôtel", "Ville"))
    )
  }
  @Test
  def test_filtreLiason_03 {
    assertEquals(
      Nil,
      AnalyseImpl.filtreLiaison(Nil)
    )
  }
  @Test
  def test_filtreLiason_04 {
    assertEquals(
      List("gare", "Rennes"),
      AnalyseImpl.filtreLiaison(List("de", "gare", "de", "Rennes"))
    )
  }
  @Test
  def test_filtreLiason_05: Unit = {
    /* note :
      on utilise AnalyseImpl.decouper avant d'utiliser AnalyseImpl.filtreLiaison
      donc les caracteres "'" seront deja suppr et les lettres "d" et "l" isolees
    */
    assertEquals(
      List("Mur", "eau"),
      AnalyseImpl.filtreLiaison(List("Mur","d","eau"))
    )
  }

  // tests unitaire de politeTest_OnlyBonjour

  @Test
  def test_onlyBonjour_1 {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("bonjour")
    )
  }

  @Test
  def test_onlyBonjour_2 {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("bnjour")
    )
  }

  @Test
  def test_onlyBonjour_3 {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("salut")
    )
  }

  @Test
  def test_onlyBonjour_4 {
    assertEquals(
      (true,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("bonsoir")
    )
  }

  @Test
  def test_onlyBonjour_5 {
    assertEquals(
      (false,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("bonjour salut")
    )
  }

  @Test
  def test_onlyBonjour_6 {
    assertEquals(
      (false,"bonjour"),
      AnalyseImpl.politeTest_OnlyBonjour("")
    )
  }

  @Test
  def test_onlyBonjour_7 {
    assertEquals(
      (false,"bonjour"),
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

  @Test
  def test_getDicoLangue_06 {
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
      AnalyseImpl.getDicoLangue(4)
    )
  }

  @Test
  def test_getDicoLangue_07 {
    assertEquals(
      List(),
      AnalyseImpl.getDicoLangue(5)
    )
  }

  //Test detecLangue

  @Test
  def test_detecLangue_01 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      (true, 0, 0),
      AnalyseImpl.detecLangue("je cherche la mairie")
    )
  }

  @Test
  def test_detecLangue_02 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      (true, 1, 0),
      AnalyseImpl.detecLangue("I am seeking la mairie de Rennes")
    )
  }

  @Test
  def test_detecLangue_03 {
    LangueImpl.setLangueActuelle("Français")
    assertEquals(
      (false, 0, 0),
      AnalyseImpl.detecLangue("aucun mot ne doit être détecté")
    )
  }

  // tests analyserList

  @Test
  def test_analyserList_01 {
    assertEquals(
      List("Service Fret - SNCF", "Gare SNCF", "Service Fret - SNCF"),
      AnalyseImpl.analyserList(List("fret","sncf"))
    )
  }

  @Test
  def test_analyserList_02 {
    assertEquals(
      List("Gare SNCF", "Gare SNCF", "Service Fret - SNCF"),
      AnalyseImpl.analyserList(List("gare","sncf"))
    )
  }

  // tests quiContient

  @Test
  def test_quiContient_01 {
    assertEquals(
      List("Piscine Bréquigny", "Piscine Gayeulles", "Piscine Saint-Georges", "Piscine Villejean"),
      AnalyseImpl.quiContient("piscine", BDDImpl.xmlListLieu)
    )
  }

  @Test
  def test_quiContient_02 {
    assertEquals(
      List("Gare SNCF"),
      AnalyseImpl.quiContient("gare", BDDImpl.xmlListLieu)
    )
  }

  // tests filtrePolitesseRecherche

  @Test
  def test_filtrePoliRech_01 {
    assertEquals(
      "je la mairie de",
      AnalyseImpl.filtrePolitesseRecherche("je cherche la mairie de rennes")
    )
  }

  @Test
  def test_filtrePoliRech_02 {
    assertEquals(
      "je la mairie de",
      AnalyseImpl.filtrePolitesseRecherche("je CHERCHE la mairie de ReNnEs")
    )
  }

}
