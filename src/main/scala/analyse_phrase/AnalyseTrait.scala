package analyse_phrase

trait AnalyseTrait {

  /** analyse une phrase sous forme de string en entrée, la corrige et y identifie un lieu que l'on connaît afin de nous renvoyer notre couple (lieu, adresse)
    *  @param phrase qui est le string à analyser
    *  @return un couple de string représentant le lieu ainsi que son adresse (lieu,adresse)
    */
  def analyser(phrase: String): (String,String)

  /**
    * analyse la phrase du user et renvoie true s'il contient "bonjour", "bonsoir" ou "salut"
    *
    * @param phrase la requete du user
    * @return true si contient un des mots, false sinon
    */
  def politeTest_Bonjour(phrase: String): (Boolean,String)

  /**
    * analyse la phrase du user et renvoie true s'il contient uniquement "bonjour", "bonsoir" ou "salut"
    *
    * @param phrase la requete du user
    * @return vrai si la phrase ne contient que "bonjour", "bonsoir" ou "salut" modulo erreurs et majuscules
    */
  def politeTest_OnlyBonjour(phrase: String): (Boolean,String)
  
  /**
    * Recupere le dictionnaire de la langue actuelle
    *
    * @return le dictionnaire correspondant a la langue actuelle
    */
  def getDicoLangue(): List[String]

  /**
    * Detecte s'il y a changement de langue et donne l'int de la langue actuelle
    *
    * @param phrase
    * @return le couple (boolean ; int) : (true si changement, false sinon ; int de la langue actuelle, qu'elle ait change ou non)
    */
  def detecLangue(phrase: String): (Boolean, Int)
  
}
