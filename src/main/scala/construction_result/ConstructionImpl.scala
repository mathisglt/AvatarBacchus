package construction_result

import analyse_phrase.AnalyseImpl

object ConstructionImpl extends ConstructionTrait{
  
    val resultat = AnalyseImpl

    /**
      * Amélioration de la v0 qui prend plusieurs couples (lieu, adresse) et rend les phrases résultats
      * @param resultAnalyse le resultat de l'analyse qui peut contenir plusieurs couples (lieu, adresse)
      * @return la ou les phrases representant la reponse sous la forme d'une String
      */
    def construirev1(resultAnalyse: List[(String, String)]):String = {
        var phrase:String = ""
        resultAnalyse match {
            case Nil => ""
            case (lieu, adresse)::reste => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
        }
        phrase
    }
}
