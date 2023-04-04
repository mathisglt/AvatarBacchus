package construction_result

import analyse_phrase.AnalyseImpl
import bdd.BDDImpl

object ConstructionImpl extends ConstructionTrait{

    /**
      * @param requete la requete de l'utilisateur
      * @param langue la langue confirmé par l'utilisateur
      * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
      */
    def construire(requete: String, langue: Int):String = {
        var phrase:String = ""
        var resultAnalyse = AnalyseImpl.analyser(requete)
        val dicoExpr = BDDImpl.getDicoExpr
        //resultAnalyse match {
        //    case Nil => ""
           // case (lieu, adresse) => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
        //}
        if (resultAnalyse != ("","")) phrase = dicoExpr(langue)(2).replace("XXX",resultAnalyse._1) + resultAnalyse._2
        else phrase = dicoExpr(langue)(3)
        phrase
    }
}
