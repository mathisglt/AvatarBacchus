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

  def correction(mots: List[String]):List[String]
  def clearAccentToMaj(mots: List[String]):List[String]
}
