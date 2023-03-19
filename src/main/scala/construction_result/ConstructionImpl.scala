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

    /**
      * Amélioration de la v0 qui prend plusieurs couples (lieu, adresse) et rend les phrases résultats
      * @param resultAnalyse le resultat de l'analyse qui peut contenir plusieurs couples (lieu, adresse)
      * @return la ou les phrases representant la reponse sous la forme d'une String
      */
    def construirev1(resultAnalyse: List[(String, String)]):String = {
        var phrase:String = ""
        resultAnalyse match {
            case Nil => ""
            case (lieu, adresse)::reste => phrase ++ "L'adresse de " + lieu + " est : " + adresse +". " ++ construirev1(reste)
        }
        phrase
    }
}
