package bdd
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import scala.io.BufferedSource
import java.io.PrintWriter
import java.io.File
import bdd.BaseDeDonnees

object BDDImpl extends BaseDeDonnees{
    
    val lines = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    val banwords = Array[String]("le","la","les","de","des","du","et","rennes","bretagne","ville")
    def chercherAdresse(str: String): String = {
        for (ligne <- lines){
            if ((ligne.toLowerCase).contains(str.toLowerCase()) && !banwords.contains(str.toLowerCase())){
                return ligne.split(";")(1)
            }
        }
        "Adresse non trouvée"
    }
  def recupadresses(file:BufferedSource):List[String]={
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
  /**
    * ajoute dans le fichier txt DonneesInitiales.txt le lieu et son adresse
    * les deux séparés par un ;
    * @param adresse
    * @param lieu
    */
    def ajouterAdresse(adresse:String,lieu:String): Unit = {
        val writer = new PrintWriter(new File("doc/DonneesInitiales.txt" ))
        writer.write(adresse+";"+lieu)
        writer.close()
    }
}