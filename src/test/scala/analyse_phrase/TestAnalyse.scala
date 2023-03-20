package analyse_phrase
import org.junit.Test
import org.junit.Assert._

class TestAnalyse {

    // tests unitaires fonction "decouper"

    @Test
    def test_decouper_1 {
        assertEquals(
            List(""),
            AnalyseImpl.decouper("")
        )
    }

    @Test
    def test_decouper_2 {
        assertEquals(
            List("hello"),
            AnalyseImpl.decouper("hello")
        )
    }

    @Test
    def test_decouper_3 {
        assertEquals(
            List("où","est","la","mairie","?"),
            AnalyseImpl.decouper("où est la mairie ?")
        )
    }

    @Test
    def test_suppr_2 {
        //
    }

    // tests unitaires fonction "analyser"

    @Test
    def test_analyser_1 {
        assertEquals(
            List(),
            AnalyseImpl.analyser("où et ?")
        )
    }

    @Test
    def test_analyser_2 {
        assertEquals(
            List(("Mairie de Rennes","Place de la Mairie")),
            AnalyseImpl.analyser("où est la mairie ?")
        )
    }

    @Test
    def test_analyser_3 {
        assertEquals(
            List(("Mairie de Rennes","Place de la Mairie")),
            AnalyseImpl.analyser("où est la Mairie de Rennes ?")
        )
    }

    @Test
    def test_analyser_4 {
        assertEquals(
            List(("Mairie de Rennes","Place de la Mairie"), ("Gare SNCF", "19, Place de la Gare")),
            AnalyseImpl.analyser("où sont la mairie et la gare ?")
        )
    }

    @Test
    def test_analyser_5 {
        assertEquals(
            List(),
            AnalyseImpl.analyser("où se trouvent Rennes et la Bretagne ?")
        )
    }

    @Test
    def test_analyser_6 {
        assertEquals( // c'est la même adresse et c'est normal
            List(("Théâtre National de Bretagne","1, Rue Saint-Hélier"),("Théâtre National de Bretagne","1, Rue Saint-Hélier")),
            AnalyseImpl.analyser("où se trouvent le tnb et le théâtre de Bretagne ?")
        )
    }

    @Test
    def test_analyser_7 {
        assertEquals(
            List(("Hôtel de Ville","Place de la Mairie")),
            AnalyseImpl.analyser("où est hôtel de ville")
        )
    }

    @Test // ne marche pas encore
    def test_analyser_8 {
        assertEquals(
            List(("Hôtel de Ville","Place de la Mairie")),
            AnalyseImpl.analyser("où est l'hôtel de ville")
        )
    }

}
