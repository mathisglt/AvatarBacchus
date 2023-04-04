package bdd
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import scala.io.BufferedSource
import java.io.PrintWriter
import java.io.File
import bdd.BaseDeDonnees
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList

object BDDImpl extends BaseDeDonnees{
    // TEST2
    
    val lignesBDD = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
    var banwords = Set("")
    var variancesaddr = Map(("tnb","1, Rue Saint-Hélier"),("hotel","Place de la Mairie"))
    var varianceslieux = Map(("tnb","Théâtre National de Bretagne"),("hotel","Mairie de Rennes"))
    var dictionnaireExpressionsInternationale : List[List[String]] = List(List(),List(),List(),List(),List())
    var dictionnairePRNInternationale : List[List[String]] = List(List(),List(),List(),List(),List())
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
    
    def createDicoExpr(): Unit={
        val lignesInter = Source.fromFile("partie2/international.txt").getLines.toList
        var listefr = List[String]()
        var listeen = List[String]()
        var listees = List[String]()
        var listede = List[String]()
        var listeit = List[String]()
        for (lignes <- lignesInter){
            if (lignes.equals("Français:")){
                val index = lignesInter.indexOf(lignes)
                for (i <- index+1 to index+8){
                    listefr = listefr:::lignesInter(i)::Nil
                }
            }   
            if (lignes.equals("Anglais:")){
                val index = lignesInter.indexOf(lignes)
                for (i <- index+1 to index+8){
                    listeen = listeen:::lignesInter(i)::Nil
                }
            }
            if (lignes.equals("Espagnol:")){
                val index = lignesInter.indexOf(lignes)
                for (i <- index+1 to index+8){
                    listees = listees:::lignesInter(i)::Nil
                }
                
            }
            if (lignes.equals("Allemand:")){
               val index = lignesInter.indexOf(lignes)
                for (i <- index+1 to index+8){
                    listede = listede:::lignesInter(i)::Nil
                }
                
            }
            if (lignes.equals("Italien:")){
                val index = lignesInter.indexOf(lignes)
                for (i <- index+1 to index+8){
                    listeit = listeit:::lignesInter(i)::Nil
                }
                
            }         
            dictionnaireExpressionsInternationale = listefr::listeen::listees::listede::listeit::Nil
        }
        
        
    }
  def createDicoPRN(): Unit= {
    val lignesInter = Source.fromFile("partie2/international.txt").getLines.toList
        var listefr = List[String]()
        var listeen = List[String]()
        var listees = List[String]()
        var listede = List[String]()
        var listeit = List[String]()
        for (lignes <- lignesInter){
            if (lignes.contains("Français:") && !lignes.equals("Français:")){
                listefr = listefr:::lignes.split(":")(1).split(",").toList
            }   
            if (lignes.equals("Anglais:")){
                listeen = listeen:::lignes.split(":")(1).split(",").toList
            }
            if (lignes.equals("Espagnol:")){
                listees = listees:::lignes.split(":")(1).split(",").toList
            }
            if (lignes.equals("Allemand:")){
                listede = listede:::lignes.split(":")(1).split(",").toList   
            }
            if (lignes.equals("Italien:")){
                listeit = listeit:::lignes.split(":")(1).split(",").toList
            }         
            dictionnairePRNInternationale = listefr::listeen::listees::listede::listeit::Nil
        }
  }
  def getDicoExpr(): List[List[String]] =  {createDicoExpr;dictionnaireExpressionsInternationale}
  def gettostrDicoExpr(): Unit = {
    println(dictionnaireExpressionsInternationale)
  }
  def gettostrDicoPRN(): Unit = {
    println(dictionnairePRNInternationale)
  }
  def getDicoPRN(): List[List[String]]= {createDicoPRN; dictionnairePRNInternationale}
}