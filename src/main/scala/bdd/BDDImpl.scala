package bdd
import scala.io.Source



class BDDImpl extends BaseDeDonnees{
    var wordtofind = ""
    val filename = "fileopen.scala"
    var listwords = Array[String]()
    for (line <- Source.fromFile(filename).getLines) {
        listwords.apply(line)
    }
    def chercherAdresse(str: String): String = {
        for (ligne <- listwords){
            if (ligne.contains(str)){
                ligne.split(";")(1)
            }
        }
        "Adresse non trouvée"
    }
}

object testBDD extends App{//batterie de test de BDD 
}


