package bdd

trait BaseDeDonnees {

  /** Méthode supprimant tous les accents de la chaine de caractères en paramètre
    *
    * @param str, la chaine de caractères avec accents
    * @return la même mais sans accents
    */
  def removeAccents(str: String): String 

  /** Méthode supprimant tous les accents et mots de liaison de la chaine de caractères donnée en paramètre
    *
    * @param str, la chaine de caractères initiale
    * @return la même mais sans accents ni mots de liaison
    */
  def removeLiaisonAccentsWords(str: String): String 

  /** Recherche dans le fichier texte DonneesInitiales si l'adresse existe, élimine les cas ou un mot
    * de liaison est donnée puisqu'il correspond à plusieurs adresses
    * 
    * @param str, un mot 
    * @return l'adresse du mot si elle existe
    */
  def chercherAdresse(str:String): String
   
  /** Renvoie le lieu à partir d'un mot si il lui correspond dans la base de données
    *
    * @param mot le mot à chercher
    * @return String le lieu si il existe , Adresse non trouvée sinon 
    */
  def chercherLieu(mot: String): String
  
  /** Renvoie la liste de toutes les formes de salutations dans la langue demandée
    *
    * @param langueActuelleStr la langue actuelle sous forme de String
    * @param langueActuelle la langue actuelle sous forme de Int
    * @return la liste de tous les "bonjour" dans cette langue
    */
  def createDicoSalutations(langueActuelleStr: String, langueActuelle: Int): List[String] 

  /** Donne la langue du mot en paramètre parmi les 5 disponibles (fr,en,es,de,it)
    *
    * @param mot le mot à détecter
    * @return la langue du mot
    */
  def langueDuMot(mot: String): String 
  
  /** Renvoie la variable dicoExpr
    * 
    */
  def getDicoExpr(): List[List[String]]

  /** Renvoie la variable dictionnairePRNInternationale
    * 
    */
  def getDicoPRN(): List[List[String]]

  /** Renvoie les mots des 4 lieux de bases pour F1 F2
    * 
    * @return List de tous les mots des 4 lieux
    */
  def recuplieuxBases() : List[String]
}
