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

    // tests unitaires fonction "analyser"

    @Test
    def test_analyser_1 {
        assertEquals(
            List(),
            AnalyseImpl.analyser("où est ?")
        )
    }

    @Test
    def test_analyser_2 {
        assertEquals(
            List(("mairie","Place de la Mairie")),
            AnalyseImpl.analyser("où est la mairie ?")
        )
    }

    @Test
    def test_analyser_3 {
        assertEquals(
            List(("Mairie","Place de la Mairie")),
            AnalyseImpl.analyser("où est la Mairie de Rennes ?")
        )
    }

    @Test
    def test_analyser_4 {
        assertEquals(
            List(("mairie","Place de la Mairie"), ("gare", "19, Place de la Gare")),
            AnalyseImpl.analyser("où est la mairie et la gare ?")
        )
    }

}
