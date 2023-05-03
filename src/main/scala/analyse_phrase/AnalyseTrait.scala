package analyse_phrase

trait AnalyseTrait {

  /** Analyse une phrase sous forme de string en entrée et y identifie des lieux que l'on connaît afin de nous renvoyer 
    * la liste des couples (lieu, adresse)
    * 
    * @param phrase qui est le string à analyser
    * @return une liste de couples de string représentant des lieus ainsi que leur adresse (lieu,adresse)
    */
  def analyser(phrase: String): List[(String,String)]

  /** Fonction appelée lorsque le user doit faire un choix entre plusieurs lieux proposés
    *
    * @param reponse du user contenant éventuellement un choix (le numéro d'un lieu proposé)
    * @return le int correspondant à son choix
    */
  def analyserChoix(requete: String): Option[Int]

  /** Analyse la phrase du user et renvoie true s'il contient "bonjour", "bonsoir" ou "salut", puis le bonjour à répondre
    *
    * @param phrase la requete du user
    * @return true si contient un des mots, false sinon ; 
    * le string correspond au bonjour dans la langue correspondante que le robot doit répondre
    */
  def politeTest_Bonjour(phrase: String): (Boolean, String)

  /** Analyse la phrase du user et renvoie true s'il contient uniquement "bonjour", "bonsoir" ou "salut", puis le bonjour à répondre
    *
    * @param phrase la requete du user
    * @return vrai si la phrase ne contient que "bonjour", "bonsoir" ou "salut" modulo erreurs et majuscules ; 
    * le string correspond au bonjour dans la langue correspondante que le robot doit répondre
    */
  def politeTest_OnlyBonjour(phrase: String): (Boolean, String)

  /** Recupere le dictionnaire de la langue actuelle
    *
    * @return le dictionnaire correspondant a la langue actuelle
    */
  def getDicoLangue(): List[String]

  /** Meme chose que getDicoLangue à la difference qu'ici on peut choisir le dictionnaire de la langue que l'on veut
    *
    * @param lang un int compris entre 0 et 4 correspondant à la langue voulue
    * @return le dico de la langue choisie, renvoie une liste vide si le int n'est pas compris entre 0 et 4
    */
  def getDicoLangue(lang: Int): List[String]

  /** Detecte s'il y a une détection de langue de langue et donne l'int de celle-ci si c'est le cas et l'int de la langue actuelle sinon
    *
    * @param phrase, la phrase du user
    * @return le couple (boolean, int, int) : (true si détection, false sinon ; int de la langue détectée si c'est le cas et 
    * langue actuelle sinon ; int de la langue actuelle pour comparer ensuite s'il y a un changement)
    */
  def detecLangue(phrase: String): (Boolean, Int, Int)
  
}
