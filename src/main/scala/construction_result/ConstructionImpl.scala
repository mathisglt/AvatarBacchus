package construction_result

class ConstructionImpl extends ConstructionTrait{
  
    val resultat = AnalyseImpl.analyser

    /**
      * Construit une phrase propre à partir du resultat recupere par l'analyse
      * @param str la chaine de caracteres recuperee par l'analyse
      * @return la phrase qui contient le resultat de l'analyse
      */
    def construire(str: String):String = {
        "L'adresse de " + resultat._1 + " est : " + resultat._2
    }
}
