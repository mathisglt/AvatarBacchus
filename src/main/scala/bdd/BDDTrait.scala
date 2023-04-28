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

  /**
    * renvoie la liste de toutes les formes de salutations dans la langue demandée
    *
    * @param langueActuelleStr la langue actuelle sous forme de String
    * @param langueActuelle la langue actuelle sous forme de Int
    * @return la liste de tous les "bonjour" dans cette langue
    */
  def createDicoSalutations(langueActuelleStr: String, langueActuelle: Int): List[String] 

  /**
    * renvoie la liste de toutes les formes de recherches dans la langue demandée
    *
    * @param langueActuelleStr la langue actuelle sous forme de String
    * @param langueActuelle la langue actuelle sous forme de Int
    * @return la liste de toutes les "recherche" dans cette langue
    */
  def createDicoRecherche(langueActuelleStr: String, langueActuelle: Int): List[String]
  /**
    *  Renvoie la variable dicoExpr
    */
  def getDicoExpr(): List[List[String]]
  /**
    *  Renvoie la variable dictionnairePRNInternationale
    */
  def getDicoPRN(): List[List[String]]
  /** Récupère le fichier xml et récupère les noms trouvés dans la balise name , et les lieux associés dans la balise name de street
    *@param un lieu(String) à chercher dans une base de donnée (bdd sous forme de List de couple String (lieu,adrese))
    * @return la liste de couple (lieu, adresse) de la base de donnees de Rennes correspondant au lieu pris en paramètre
    */
  def chercherCouplesXML(lieu : String, bdd:List[(String,String)]): List[(String,String)]
  /**
    * Renvoie les mots des 4 lieux de bases pour F1 F2
    * @return List de tous les mots des 4 lieux
    */
  def recuplieuxBases() : List[String]
  /** Récupère le fichier xml et récupère les noms trouvés dans la balise name , et les lieux associés dans la balise name de street
    *
    * @return la liste de couple (lieu, adresse) de la base de donnees de Rennes (1629 couples)
    */
  def createListFromXML(): List[(String, String)]
  /**
    * cree la liste de tous les lieux de la bdd xml uniquement s'ils ont une adresse
    *
    * @return la liste des lieux du doc vAr.xml
    */
  def createListLieuFromXML(): List[String] 
  /**
    * Méthode supprimant tous les accents de la chaine de caractères en paramètre
    *
    * @param str
    * @return
    */
  def removeAccents(str: String): String 
  /**
    * Méthode supprimants tous les accents et mots de liaison de la chaine de caractères donnée en paramètre
    *
    * @param str
    * @return
    */
  def removeLiaisonAccentsWords(str: String): String 
  /**
    * Donne la langue du mot en paramètre parmi les 5 disponibles (fr,en,es,de,it)
    *
    * @param mot
    * @return
    */
  def langueDuMot(mot: String): String 
  /**
    * Récupère tous les premiers éléments d'une Liste de couple (string, string). Ici , les lieux de la liste xml
    *
    * @param List[(String,String)]
    * @return List de tous les lieux sous forme d'une List de String
    */
  def lieuXML(bdd : List[(String,String)]): List[String]
}
