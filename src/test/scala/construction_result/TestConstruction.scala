package construction_result
import org.junit.Test
import org.junit.Assert._

class TestConstruction {
    
    //tests constructionv1

    @Test
    def test_construction_1 {
        //resultat a modifier pour que construction renvoie quelque chose ?
        assertEquals(
            "Je ne comprends pas votre demande",
            ConstructionImpl.construire("")
        )
    }

    @Test
    def test_construction_2 {
        //test pour un seul couple
        assertEquals(
            "L'adresse de Mairie de Rennes est : Place de la Mairie",
            ConstructionImpl.construire("Place de la Mairie")   
        )
    }

    @Test
    def test_constructionPolitesse_1 {
        //test pour un seul bonjour
        assertEquals(
            List("Bonjour"),
            ConstructionImpl.construirePolitesse("Bonjour")   
        )
    }
    @Test
    def test_constructionPolitesse_2 {
        //test pour bonjour + requete
        assertEquals(
            List("Bonjour","L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construirePolitesse("Bonjour, Place de la Mairie")   
        )
    }
    @Test
    def test_constructionPolitesse_3 {
        //test normal
        assertEquals(
            List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construirePolitesse("Place de la Mairie")   
        )
    }

  //  @Test
  //  def test_constructionv1_3 {
        //test pour deux couples
   //     assertEquals(
   //         "L'adresse de Mairie est : Place de la Mairie. L'adresse de Gare est : 19, Place de la Gare. ",
   //         ConstructionImpl.construire(List(("Mairie","Place de la Mairie"), ("Gare", "19, Place de la Gare")))
   //     )
   // }
   
}
