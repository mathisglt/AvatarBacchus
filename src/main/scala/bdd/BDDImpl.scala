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
    var variancesaddr = Map(("tnb","1, Rue Saint-Hélier"),("hotel de ville","Place de la Mairie"))
    var varianceslieux = Map(("tnb","Théâtre National de Bretagne"),("hôtel de ville","Mairie de Rennes"))
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
  for (line <- file.getLines().grouped(2)) {
    listeFinale += line(0).split(';')(0)
  }
  listeFinale.toList
}
  
}