package analyse_phrase
import org.junit.Test
import org.junit.Assert._

class TestAnalyse {

    // tests unitaires fonction "analyser"

    @Test
    def test_analyser_01 {
        assertEquals(
            ("",""),
            AnalyseImpl.analyser("où et ?")
        )
    }

    @Test
    def test_analyser_02 {
        assertEquals(
            ("Mairie","Place de la Mairie"),
            AnalyseImpl.analyser("où est la mairie ?")
        )
    }

    @Test
    def test_analyser_03 {
        assertEquals(
            ("Mairie de Rennes","Place de la Mairie"),
            AnalyseImpl.analyser("où est la Mairie de Rennes ?")
        )
    }

    @Test
    def test_analyser_04 {
        assertEquals( // XXX "mairie se trouve avant gare dans la bdd"
            ("Mairie","Place de la Mairie"),
            AnalyseImpl.analyser("où sont la gare sncf et la mairie ?")
        )
    }

    @Test
    def test_analyser_05 {
        assertEquals(
            ("",""),
            AnalyseImpl.analyser("où se trouvent Rennes et la Bretagne ?")
        )
    }

    @Test
    def test_analyser_06 {
        assertEquals(
            ("TNB","1, Rue Saint-Hélier"),
            AnalyseImpl.analyser("où se trouvent le tnb et le théâtre de Bretagne ?")
        )
    }

    @Test
    def test_analyser_07 {
        assertEquals(
            ("Hôtel de ville","Place de la Mairie"),
            AnalyseImpl.analyser("où est hôtel de ville")
        )
    }

    @Test // ne marche pas encore
    def test_analyser_08 {
        assertEquals(
            ("Hôtel de ville","Place de la Mairie"),
            AnalyseImpl.analyser("où est l'hôtel de ville")
        )
    }

    @Test
    def test_analyser_09 {
        assertEquals(
            ("Gare SNCF","19, Place de la Gare"),
            AnalyseImpl.analyser("où se trouve la gare sncf ?")
        )
    }

    @Test
    def test_analyser_10 {
        assertEquals(
            ("Mairie","Place de la Mairie"),
            AnalyseImpl.analyser("Place de la mairie")
        )
    }
}
