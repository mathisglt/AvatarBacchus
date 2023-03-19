package bdd
import scala.io.Source



object BDDImpl extends BaseDeDonnees{
    val lines = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    def chercherAdresse(str: String): String = {
    
    for (ligne <- lines){
        if (ligne.contains(str)){
            ligne.split(";")(1)
        }
    }
        "Adresse non trouvée"
    }

}
