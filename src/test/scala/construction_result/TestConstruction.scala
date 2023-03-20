package construction_result
import org.junit.Test
import org.junit.Assert._

class TestConstruction {
    
    //tests constructionv1

    @Test
    def test_constructionv1_1 {
        //resultat a modifier pour que construction renvoie quelque chose ?
        assertEquals(
            "",
            ConstructionImpl.construirev1(List())
        )
    }

    @Test
    def test_constructionv1_2 {
        //test pour un seul couple
        assertEquals(
            "L'adresse de Mairie est Place de la Mairie.",
            ConstructionImpl.construirev1(List(("Mairie","Place de la Mairie")))   
        ) 
    }

    @Test
    def test_constructionv1_3 {
        //test pour deux couples
        assertEquals(
            "L'adresse de Mairie est place de la Mairie. L'adresse de Gare est 19, Place de la Gare.",
            ConstructionImpl.construirev1(List(("Mairie","Place de la Mairie"), ("Gare", "19, Place de la Gare")))
        )
    }
}
