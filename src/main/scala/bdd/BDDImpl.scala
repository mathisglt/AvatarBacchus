package bdd
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import scala.io.BufferedSource
import java.io.PrintWriter
import java.io.File
import bdd.BaseDeDonnees
import scala.collection.mutable.ArrayBuffer

object BDDImpl extends BaseDeDonnees{
    // TEST2
    
    val lignesBDD = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    var banwords = Set("")
    var variancesaddr = Map(("tnb","1, Rue Saint-Hélier"),("hotel","Place de la Mairie"))
    var varianceslieux = Map(("tnb","Théâtre National de Bretagne"),("hotel","Mairie de Rennes"))
    var dictionnaireExpressionsInternationale : Array[Array[String]] = Array[Array[String]]()
    def chercherAdresse(mot: String): String = {
        if (mot.isEmpty()) return "Adresse non trouvée"
        for (ligne <- lignesBDD){
            val fields = ligne.split(";")
            // Si le mot n'apparait qu'une fois dans les lieux possibles
            if(lignesBDD.filter(x => x.contains(mot)).length <= 1){
                //Si c'est un mot variant , on récupère son adresse liée
                if (variancesaddr.contains(mot.toLowerCase())){
                    return variancesaddr.getOrElse(mot.toLowerCase(),"")
                }
                //Sinon on récupère son adresse
                if ( ((fields(0).toLowerCase).contains(" "+ mot.toLowerCase())
                   || (fields(0).toLowerCase).contains(mot.toLowerCase()+ " ")) 
                        && !banwords.contains(mot.toLowerCase())){
                    return fields(1)
                }  
            }
            
            
        }
        "Adresse non trouvée"
    }
    def chercherLieu(mot: String): String = {
        if (mot.isEmpty()) {"Adresse non trouvée"}
        for (ligne <- lignesBDD){
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
    
   
  def recupLieux(file: BufferedSource): List[String] = {
    val listeFinale = ArrayBuffer[String]()
    for (line <- file.getLines()) {
        listeFinale += line.split(';')(0)
    }
    listeFinale.toList
    }
    
    def createDicoExpr()={
        val lignesInter = Source.fromFile("partie2/international.txt").getLines.toArray
        for (lignes <- lignesInter){
            if (lignes.equals("Francais:")){
                val index = lignesInter.indexOf(lignes)
                //for (i <- index to index+8){
                //    dictionnaireExpressionsInternationale.addString(lignesInter.)
                //}
                
            }            
        }
    }
  def createDicoPRN(): Unit= ???
  def getDicoExpr(): Array[Array[String]] = ???
  def getDicoPRN(): Array[Array[String]]= ???
}