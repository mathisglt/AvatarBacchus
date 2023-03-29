package bdd

import java.util.ArrayList
import scala.io.BufferedSource
trait BaseDeDonnees {
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

}
