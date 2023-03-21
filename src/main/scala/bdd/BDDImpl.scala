package bdd
import scala.io.Source
import scala.io.BufferedSource


object BDDImpl extends BaseDeDonnees{
    val lines = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    val banwords = Array[String]("le","la","les","de","des","du","et")
    def chercherAdresse(str: String): String = {
        for (ligne <- lines){
            if ((ligne.toLowerCase).contains(str.toLowerCase()) && !banwords.contains(str.toLowerCase())){
                return ligne.split(";")(1)
            }
        }
        "Adresse non trouvée"
    }
/**
    * récupère les mots des endroits où aller
    *
    * @param file un fichier (ici pour utiliser le fichier donneesInitiales)
    * @return une array[String] contenant les endroits où aller
    */
  def recup(file:BufferedSource):List[String]={
    val fileEnString=file.mkString
    val listeDeMots=fileEnString.split("[;\r\n]+")
    var listeFinale:Array[String]=Array()
    var i:Int =0
    while (i<listeDeMots.length){
        if (i%2==0){
            listeFinale=listeFinale:+(listeDeMots(i))
        }
        i=i+1
    }
    listeFinale.toList
  }
}
