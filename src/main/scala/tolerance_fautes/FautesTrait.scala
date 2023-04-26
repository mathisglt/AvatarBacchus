package tolerance_fautes

trait FautesTrait {
  //Sur le principe le but va être d'extraire les mots de la base de données et les mettre sous forme de liste,
  //leur enlever leurs accents, puis les mettre en majuscule
  //ainsi on obtient la liste des mots de la base de données à vérifier filtrée de ses majuscules et de ses accents,
  //On fait de même avec la liste de mots qui est envoyée ici (la liste des mots de la phrase à analyser)
  //puis on les compare avec la distance de levenstein
  //On aura donc :
  // -une fonction qui prend une liste de chaines de caracteres et en enleve les accents puis les mets en majuscules(potentiellement par deux fonctions auxiliaires)
  // -une fonction qui compare deux mots avec levenstein

  //ici la liste de mots 'modele' en quelque sorte
  //on ne regarde pas les petits mots (<3)
  /** applique testChaqueMot sur toute une liste de mot et en renvoie la string corrigée
    *
    * @param mots une liste de mots
    * @return la meme liste corrigée
    */

  def correction(mots: List[String], modeles: List[String]): List[String]
  //meme fonction que la precedente mais on corrige les petits mots
    /** applique testChaqueMotAvecPetitsMots sur toute une liste de mot et en renvoie la string corrigée
    *
    * @param mots une liste de mots
    * @return la meme liste corrigée
    */
  def correctionAvecPetitsMots(mots:List[String],modeles:List[String]):List[String]
}
