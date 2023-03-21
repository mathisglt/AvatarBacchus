package construction_result

import analyse_phrase.AnalyseImpl


object ConstructionImpl extends ConstructionTrait{
  
    val resultat = AnalyseImpl

    /**
      * Amélioration de la v0 qui prend plusieurs couples (lieu, adresse) et rend les phrases résultats
      * @param requete requete (lieu, adresse)
      * @return la ou les phrases representant la reponse sous la forme d'une String
      */
    def construire(requete: String):String = {
        var phrase:String = ""
        var resultAnalyse = AnalyseImpl.analyser(requete)
        //resultAnalyse match {
        //    case Nil => ""
           // case (lieu, adresse) => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
        //}
        if (resultAnalyse != ("","")) phrase += "L'adresse de " ++ resultAnalyse._1 ++ " est : " ++ resultAnalyse._2 
        else phrase = "Je ne comprends pas votre demande"
        phrase
        
    }
}
