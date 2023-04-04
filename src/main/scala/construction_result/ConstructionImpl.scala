package construction_result

import analyse_phrase.AnalyseImpl


object ConstructionImpl extends ConstructionTrait{

    /**
      * @param requete la requete de l'utilisateur
      * @param langue la langue confirmé par l'utilisateur
      * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
      */
    def construire(requete: String, langue: langue):String = {
        var phrase:String = ""
        var resultAnalyse = AnalyseImpl.analyser(requete)
        //resultAnalyse match {
        //    case Nil => ""
           // case (lieu, adresse) => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
        //}
        if (resultAnalyse != ("","")) phrase = expression(langue,2).replace("XXX",resultAnalyse._1) + resultAnalyse._2
        else phrase = expression(langue, 3)
        phrase
    }
}
