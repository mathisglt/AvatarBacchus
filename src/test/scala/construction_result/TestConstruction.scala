package construction_result
import org.junit.Test
import org.junit.Assert._
import java.lang.reflect.Constructor
import machine.MachineImpl

class TestConstruction {

    // tests d'intégrations

    @Test
    def test_ConstructionImpl_01: Unit = {
        ConstructionImpl.construire("je cherche une piscine") // plusieurs choix, le user devra faire un choix
        assertEquals(
            true,
            ConstructionImpl.choix_en_cours,
        )
        MachineImpl.reinit() // on reinitialise, pour verifier que choix_en_cours repasse a false
        assertEquals(
            false,
            ConstructionImpl.choix_en_cours,
        )
    }

    @Test
    def test_ConstructionImpl_02: Unit = {
        ConstructionImpl.construire("je cherche la mairie") // un seul choix, le user n'a pas en faire
        assertEquals(
            false,
            ConstructionImpl.choix_en_cours,
        )
    }

    @Test
    def test_ConstructionImpl_03: Unit = {
        ConstructionImpl.construire("je cherche la mairie")
        assertEquals(
            List("Mairie de Rennes"),
            ConstructionImpl.liste_propositions_saved,
        )
    }

    @Test
    def test_ConstructionImpl_04: Unit = {
        ConstructionImpl.construire("je cherche une piscinee") // on rajoute une faute allez
        assertEquals(
            List("Piscine Bréquigny", "Piscine Gayeulles", "Piscine Saint-Georges", "Piscine Villejean"),
            ConstructionImpl.liste_propositions_saved,
        )
    }

    // tests construireLangue

    @Test
    def test_construireLangue_01: Unit = {
        assertEquals(
            List("Do you speak english?"),
            ConstructionImpl.construireLangue("hello")
        )
    }

    @Test
    def test_construireLangue_02: Unit = {
        assertEquals(
            List("Je ne comprends pas votre demande"),
            ConstructionImpl.construireLangue("")
        )
    }

    @Test
    def test_construireLangue_03: Unit = {
        assertEquals(
            List("Hablas español?"),
            ConstructionImpl.construireLangue("busco gare")
        )
    }

    @Test
    def test_construireLangue_04: Unit = {
        assertEquals(
            List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construireLangue("je cherche la mairie de rennes")
        )
    }
    
    // tests construirePolitesse

    @Test
    def test_construirePolitesse_1 {
        //test pour un seul bonjour
        assertEquals(
            List("bonjour"),
            ConstructionImpl.construirePolitesse("Bonjour")   
        )
    }
    @Test
    def test_construirePolitesse_2 {
        //test pour bonjour + requete
        assertEquals(
            List("bonjour","L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construirePolitesse("Bonjour, Mairie")   
        )
    }
    @Test
    def test_construirePolitesse_3 {
        //test normal
        assertEquals(
            List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construirePolitesse("Mairie")   
        )
    }
    
    // tests construireLesReponses

    @Test
    def test_construireLesReponses_1 {
        assertEquals(
            List("Je ne comprends pas votre demande"),
            ConstructionImpl.construireLesReponses("")
        )
    }

    @Test
    def test_construireLesReponses_2 {
        assertEquals(
            List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
            ConstructionImpl.construireLesReponses("la Mairie")   
        )
    }

    @Test
    def test_construireLesReponses_3 {
        assertEquals(
            List("J'ai 4 réponses possibles :", "1) Piscine Bréquigny", "2) Piscine Gayeulles", "3) Piscine Saint-Georges", 
            "4) Piscine Villejean", "Quel est votre choix?"),
            ConstructionImpl.construireLesReponses("piscine")   
        )
    }

    // tests construireReponseUnique

    @Test
    def test_construireReponseUnique_1 {
        assertEquals(
            "Je ne comprends pas votre demande",
            ConstructionImpl.construireReponseUnique("")
        )
    }

    @Test
    def test_construireReponseUnique_2 {
        assertEquals(
            "Je ne comprends pas votre demande",
            ConstructionImpl.construireReponseUnique("dhzbfz    ebz ")
        )
    }

    @Test
    def test_construireReponseUnique_3 { 
        // on renvoit cette valeur par defaut mais ce cas ne doit jamais arriver :
        // construireLesReponses s'occupe de rediriger cette requete vers construireLesPropositions
        assertEquals(
            "",
            ConstructionImpl.construireReponseUnique("je cherche une piscine")
        )
    }

    @Test
    def test_construireReponseUnique_4 {
        assertEquals(
            "Je ne comprends pas votre demande",
            ConstructionImpl.construireReponseUnique("restaurant iEFBIeugfiueg")
        )
    }

    @Test
    def test_construireReponseUnique_5 {
        assertEquals(
            "L'adresse de La Tomate est : 18, rue Saint Georges",
            ConstructionImpl.construireReponseUnique("pizzeria la tomate")
        )
    }

    // tests construireLesPropositions

    @Test
    def test_construireLesPropositions_1 {
        MachineImpl.reinit()
        val list = List("Piscine Bréquigny", "Piscine Gayeulles", "Piscine Saint-Georges", "Piscine Villejean")
        ConstructionImpl.liste_propositions_saved = list
        assertEquals(
            List("J'ai 4 réponses possibles :", "1) Piscine Bréquigny", "2) Piscine Gayeulles", "3) Piscine Saint-Georges", 
            "4) Piscine Villejean", "Quel est votre choix?"),
            ConstructionImpl.construireLesPropositions(list)
        )
        MachineImpl.reinit()
    }

    @Test
    def test_construireLesPropositions_2 {
        // ce cas est impossible mais on le teste pour être sûr qu'il ne renvoit pas d'erreur
        // construireLesReponses renverra ce cas à construireReponseUnique
        MachineImpl.reinit()
        val list = Nil
        ConstructionImpl.liste_propositions_saved = list
        assertEquals(
            List("J'ai 0 réponses possibles :", "Quel est votre choix?"),
            ConstructionImpl.construireLesPropositions(list)
        )
        MachineImpl.reinit()
    }

    @Test
    def test_construireLesPropositions_3 {
        // ce cas est impossible mais on le teste pour être sûr qu'il ne renvoit pas d'erreur
        // construireLesReponses renverra ce cas à construireReponseUnique
        MachineImpl.reinit()
        val list = List("Mairie de Rennes")
        ConstructionImpl.liste_propositions_saved = list
        assertEquals(
            List("J'ai 1 réponses possibles :", "1) Mairie de Rennes", "Quel est votre choix?"),
            ConstructionImpl.construireLesPropositions(list)
        )
        MachineImpl.reinit()
    }
   
}
