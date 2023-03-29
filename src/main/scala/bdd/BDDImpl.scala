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
    var banwords = List("de","du","des","la","le","les"," ","théâtre","où")
    var variancesaddr = Map(("tnb","1, Rue Saint-Hélier"),("hotel de ville","Place de la Mairie"))
    var varianceslieux = Map(("tnb","Théâtre National de Bretagne"),("hôtel de ville","Mairie de Rennes"))
    def chercherAdresse(mot: String): String = {
        if (mot.length == 0) return "Adresse non trouvée"
        for (ligne <- lines){
            val fields = ligne.split(";")
            if (variancesaddr.contains(mot.toLowerCase())){
                print(variancesaddr.getOrElse(mot.toLowerCase(),""))
                return variancesaddr.getOrElse(mot.toLowerCase(),"")
            }
            if ( ((fields(0).toLowerCase).contains(" "+ mot.toLowerCase())
              || (fields(0).toLowerCase).contains(mot.toLowerCase()+ " ")) && !banwords.contains(mot.toLowerCase())){
                return fields(1)
            }
            
        }
        "Adresse non trouvée"
    }
    //TODO scaladoc
    def chercherLieu(mot: String): String = {
        if (mot == "") {"Adresse non trouvée"}
        for (ligne <- lines){
            val fields = ligne.split(";")
            if (varianceslieux.contains(mot.toLowerCase())){
                return varianceslieux.getOrElse(mot.toLowerCase(),"")
            }
            if ((fields(0).toLowerCase).contains(mot.toLowerCase())&& !banwords.contains(mot.toLowerCase())){
                return fields(0)
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
  
}