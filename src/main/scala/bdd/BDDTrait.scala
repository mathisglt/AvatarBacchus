package bdd

import java.util.ArrayList
import scala.io.BufferedSource
trait BaseDeDonnees {
  /**
    *Langues: 
    * 0: Français
    * 1: Anglais
    * 2: Espagnol
    * 3: Allemand
    * 4: Italien
    * @return dictionnaireInternationale qui est le international.txt rangé en dico
    */
  var dictionnaireExpressionsInternationale :List[List[String]]
  var dictionnairePRNInternationale : List[List[String]]
  val lignesBDD: Array[String]
  /**
    * Recherche dans le fichier texte DonneesInitiales si l'adresse existe , élimine les cas ou un mot
    * de liaison est donnée puisqu'il correspond à plusieurs adresses
    * @param str, un mot 
    * @return l'adresse du mot si elle existe
    */
  def chercherAdresse(str:String): String
   /**
    * récupère les mots des endroits où aller
    *
    * @param file un fichier (ici pour utiliser le fichier donneesInitiales)
    * @return une array[String] contenant les endroits où aller
    */
  def recupLieux(file:BufferedSource):List[String]
  /**
    * Renvoie le lieu à partir d'un mot si il lui correspond dans la base de données
    *
    * @param mot
    * @return String le lieu si il existe , Adresse non trouvée sinon 
    */
  def chercherLieu(mot: String): String
  /**
    * Creer la variable dictionnaireExpressionsInternationale accessible par getDicoExpr
    */
  def createDicoExpr(): Unit
  /**
    * Creer la variable dictionnairePRNInternationale accessible par getDicoPRN
    */
  def createDicoPRN(): Unit
  /**
    * Recupère la variable dictionnaireExpressionsInternationale
    */
  def getDicoExpr(): List[List[String]]
  /**
    *  Recupère la variable dictionnairePRNInternationale
    */
  def getDicoPRN(): List[List[String]]
  
  /** Récupère le fichier xml et récupère les noms trouvés dans la balise name , et les lieux associés dans la balise name de street
    *@param un lieu(String) à chercher dans une base de donnée (bdd sous forme de List de couple String (lieu,adrese))
    * @return la liste de couple (lieu, adresse) de la base de donnees de Rennes correspondant au lieu pris en paramètre
    */
    def chercherCouplesXML(lieu : String, bdd:List[(String,String)]): List[(String,String)]
}
