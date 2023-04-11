package construction_result

import analyse_phrase.AnalyseImpl

object ConstructionImpl extends ConstructionTrait{



    def construireLangue(requete: String): List[String] = {
      val change = AnalyseImpl.detecLangue(requete)
      val dicoExpr = AnalyseImpl.getDicoLangue(change._2)
      if(change._1){//true = changer langue 
        dicoExpr(4) :: Nil
      }
      else{
        construirePolitesse(requete)
      }
    }


    /**
      * @param requete la requete de l'utilisateur
      * @return un bonjour si bonjour + construire
      */
    def construirePolitesse(requete: String):List[String] = {
        if (AnalyseImpl.politeTest_OnlyBonjour(requete)) "Bonjour" :: Nil
        else if (AnalyseImpl.politeTest_Bonjour(requete)) "Bonjour" :: List(construire(requete))
        else List(construire(requete))
    }


    /**
      * @param requete la requete de l'utilisateur
      * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
      */
    def construire(requete: String):String = {
        var phrase:String = ""
        var resultAnalyse = AnalyseImpl.analyser(requete)
        val dicoExpr = AnalyseImpl.getDicoLangue
        //resultAnalyse match {
        //    case Nil => ""
        // case (lieu, adresse) => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
        //}
        if (resultAnalyse != ("","")) phrase = dicoExpr(2).replace("XXX", resultAnalyse._1) + " : " +resultAnalyse._2
        else phrase = dicoExpr(3)
        println("Requete corrigé  : " + phrase)
        phrase
    }
}
