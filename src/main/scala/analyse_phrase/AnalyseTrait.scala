package analyse_phrase
import library.Html

trait AnalyseTrait {

  /** 
    * analyse une phrase sous forme de string en entrée et y identifie des lieux que l'on connaît afin de nous renvoyer 
    * la liste des couples (lieu, adresse)
    * 
    * @param phrase qui est le string à analyser
    * @return une liste de couples de string représentant des lieus ainsi que leur adresse (lieu,adresse)
    */
  def analyser(phrase: String): List[(String,String)]

  /**
    * Retourne un couple (lieu , adresse) à partir d'un type Html
    *
    * @param html
    * @return (Lieu,Adresse)
    */
  def getAdressFromHtml(html : Html) : (String,String)

  /** 
    * permet de retirer les mots de liaisons de phrase sous formes de liste de string
    * - on retire les mots ayant une longueur inf a 2
    * - on retire d'autres mots choisis
    * 
    * @param requete sous forme de liste de string
    * @return la phrase sous forme de liste de string sans les mots de liaisons
    */
    def filtreLiaison(requete: List[String]): List[String]
     /**
    * enleve de la requete du user tous les mots de Recherche ou de Politesse (on enleve "Rennes" aussi)
    *
    * @param requete la requete du user (String)
    * @return la requete sans les mots de Recherche ou de Politesse
    */
  def filtrePolitesseRecherche(requete: String): String 
  /**
    * on cherche tous les lieux que cherche le user dans sa requete en regardant chaque mot un à un
    * attention : les filtres anti parasites sont déjà appliqués
    *
    * @param requete la requete du user en List[String]
    * @return la liste des lieux
    */
  def analyserList(requete: List[String]): List[String]
  /** 
    * cherche tous les lieux de la bdd qui contiennent le mot passé en param
    * 
    * @param mot le mot que l'on cherche dans la base de données
    * @param list, la liste des lieux de vAr.xml (à Rennes et ayant une adresse)
    * @return une liste de string représentant la liste des lieux contenant mot
    */
  def quiContient(mot: String, list: List[String]): List[String]
   /**
    * découpe un string en liste de mots
    *
    * @param phrase qui est le string à découper
    * @return une liste de string représentant la phrase decoupee
    */
  def decouper(phrase: String): List[String]
  /** 
    *  assemble une liste de mot (string) pour former une phrase sous forme de string avec un espace entre chaque mot
    * 
    *  @param list une list de mot
    *  @return un string qui sera la phrase décrit par les éléments de départ
    */
  def assembler(list: List[String]): String
   /**
    * fonction appelée lorsque le user doit faire un choix entre plusieurs lieux proposés
    *
    * @param reponse du user contenant éventuellement un choix (le numéro d'un lieu proposé)
    * @return le int correspondant à son choix
    */
  def analyserChoix(requete: String): Option[Int]
  /** meme chose que getDicoLangue à la difference qu'ici on peut choisir le dictionnaire de la langue que l'on veut
    *
    * @param lang un int compris entre 0 et 4 correspondant à la langue voulue
    * @return le dico de la langue choisie, renvoie une liste vide si le int n'est pas compris entre 0 et 4
    */
  def getDicoLangue(lang: Int): List[String]
  /**
    * analyse la phrase du user et renvoie true s'il contient "bonjour", "bonsoir" ou "salut", puis le bonjour à répondre
    *
    * @param phrase la requete du user
    * @return true si contient un des mots, false sinon ; 
    * le string correspond au bonjour dans la langue correspondante que le robot doit répondre
    */
  def politeTest_Bonjour(phrase: String): (Boolean, String)

  /**
    * analyse la phrase du user et renvoie true s'il contient uniquement "bonjour", "bonsoir" ou "salut", puis le bonjour à répondre
    *
    * @param phrase la requete du user
    * @return vrai si la phrase ne contient que "bonjour", "bonsoir" ou "salut" modulo erreurs et majuscules ; 
    * le string correspond au bonjour dans la langue correspondante que le robot doit répondre
    */
  def politeTest_OnlyBonjour(phrase: String): (Boolean, String)
  
  /**
    * Recupere le dictionnaire de la langue actuelle
    *
    * @return le dictionnaire correspondant a la langue actuelle
    */
  def getDicoLangue(): List[String]

  /**
    * Detecte s'il y a une détection de langue de langue et donne l'int de celle-ci si c'est le cas et l'int de la langue actuelle sinon
    *
    * @param phrase
    * @return le couple (boolean, int, int) : (true si détection, false sinon ; int de la langue détectée si c'est le cas et 
    * langue actuelle sinon ; int de la langue actuelle pour comparer ensuite s'il y a un changement)
    */
  def detecLangue(phrase: String): (Boolean, Int, Int)
  
}
