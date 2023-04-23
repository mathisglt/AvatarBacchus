package analyse_phrase

trait AnalyseTrait {

  /** analyse une phrase sous forme de string en entrée, la corrige et y identifie un lieu que l'on connaît afin de nous renvoyer notre couple (lieu, adresse)
    *  @param phrase qui est le string à analyser
    *  @return un couple de string représentant le lieu ainsi que son adresse (lieu,adresse)
    */
  def analyser(phrase: String): (String,String)

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
