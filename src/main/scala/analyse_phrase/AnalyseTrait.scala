package analyse_phrase

trait AnalyseTrait {

/**
  * prend chaque element de la liste et renvoie la liste de couples associée à la phrase
  *
  * @param phrase_decoup une liste de String correspondant à la phrase du user transformee en liste de mots
  * @return la liste de couples (lieu, adresses) 
  */
  def analyser(phrase_decoup: List[String]): List[(String,String)]
  
}
