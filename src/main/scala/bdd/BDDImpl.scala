package bdd
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import scala.io.BufferedSource
import java.io.PrintWriter
import java.io.File
import bdd.BaseDeDonnees

object BDDImpl extends BaseDeDonnees{
    // TEST2
    
    val lines = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    def chercherAdresse(str: String): String = {
        for (ligne <- lines){
            val fields = ligne.split(";")
            if ((fields(0).toLowerCase).contains(str.toLowerCase())){
                return fields(1)
            }
        }
        "Adresse non trouvée"
    }
  def recupLieux(file:BufferedSource):List[String]={
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