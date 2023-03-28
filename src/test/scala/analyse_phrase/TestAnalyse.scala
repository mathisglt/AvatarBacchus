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
            ("Mairie de Rennes","Place de la Mairie"),
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
            ("Mairie de Rennes","Place de la Mairie"),
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
            ("Théâtre National de Bretagne","1, Rue Saint-Hélier"),
            AnalyseImpl.analyser("où se trouvent le tnb et le théâtre de Bretagne ?")
        )
    }

    @Test
    def test_analyser_07 {
        assertEquals(
            ("Mairie de Rennes","Place de la Mairie"),
            AnalyseImpl.analyser("où est hôtel de ville")
        )
    }

    @Test // ne marche pas encore
    def test_analyser_08 {
        assertEquals(
            ("Mairie de Rennes","Place de la Mairie"),
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
            ("Mairie de Rennes","Place de la Mairie"),
            AnalyseImpl.analyser("Place de la mairie")
        )
    }

    @Test
    def test_analyser_11 {
        assertEquals(
            ("Gare SNCF","19, Place de la Gare"),
            AnalyseImpl.analyser("où est la grre")
        )
    }

    @Test
    def test_analyser_12 {
        assertEquals(
            ("Gare SNCF","19, Place de la Gare"),
            AnalyseImpl.analyser("où est la gre")
        )
    }

    @Test
    def test_analyser_13 {
        assertEquals(
            ("Gare SNCF","19, Place de la Gare"),
            AnalyseImpl.analyser("où est la G#Ré")
        )
    }

    // tests unitaires de decouper

    
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

    // tests unitaires pour assembler

    @Test
    def test_assembler_1 {
        assertEquals(
            "",
            AnalyseImpl.assembler(Nil)
        )
    }

    @Test
    def test_assembler_2 {
        assertEquals(
            "salut les amis",
            AnalyseImpl.assembler(List("salut","les","amis"))
        )
    }

        //Tests de filtreLiason
    @Test
    def test_filtreLiason_1 {
        assertEquals(
            List("Hôtel","Ville") ,
            AnalyseImpl.filtreLiaison(List("Hôtel" ,"de", "Ville"))
        )
    }
     @Test
    def test_filtreLiason_2 {
        assertEquals(
            List("Hôtel","Ville"),
            AnalyseImpl.filtreLiaison(List("Hôtel","Ville"))
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
            List("gare","Rennes"),
            AnalyseImpl.filtreLiaison(List("de","gare","de","Rennes"))
        )
    }
    

}
