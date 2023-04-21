package construction_result

import analyse_phrase.AnalyseImpl

object ConstructionImpl extends ConstructionTrait {

  def construireLangue(requete: String): List[String] = {
    val change = AnalyseImpl.detecLangue(requete)
    val dicoExpr = AnalyseImpl.getDicoLangue(change._2)
    if (change._1) { //true = changer langue
      dicoExpr(4) :: Nil
    } else {
      construirePolitesse(requete)
    }
  }

  def construireConfirmation(requete: String, langueActuelle: Int): String = {
    val dicoExpr = AnalyseImpl.getDicoLangue(langueActuelle)
    requete match {
      case "oui" if (langueActuelle == 0) => dicoExpr(5) //"D'accord, quelle est votre demande?"
      case "yes" if (langueActuelle == 1) => dicoExpr(5) //"OK, what is your query?"
      case "si" if (langueActuelle == 2)  => dicoExpr(5) //"Está bien, cuál es tu petición?"
      case "ja" if (langueActuelle == 3)  => dicoExpr(5) //"Okay, was ist Ihr Wunsch?"
      case "si" if (langueActuelle == 4)  => dicoExpr(5) //"Va bene, qual è la tua richiesta?"
      case _                              => "Pas de confirmation"
    }
  }

  /** @param requete la requete de l'utilisateur
    * @return un bonjour si bonjour + construire
    */
  def construirePolitesse(requete: String): List[String] = {
    val couple = AnalyseImpl.politeTest_OnlyBonjour(requete)
    val couple2 = AnalyseImpl.politeTest_Bonjour(requete)
    if (couple._1) couple._2 :: Nil // couple._1 = seulement bonjour, couple._2 = bonjour dans la langue correspondante
    else if (couple2._1) couple2._2 :: List(construire(requete)) // couple2._1 = bonjour + une requete, couple._2 = bonjour dans la langue correspondante
    else List(construire(requete))
  }

  /** @param requete la requete de l'utilisateur
    * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
    */
  def construire(requete: String): String = {
    var phrase: String = ""
    var resultAnalyse = AnalyseImpl.analyser(requete)
    val dicoExpr = AnalyseImpl.getDicoLangue
    //resultAnalyse match {
    //    case Nil => ""
    // case (lieu, adresse) => phrase += "L'adresse de " ++ lieu ++ " est : " ++ adresse ++ ". " ++ construirev1(reste)
    //}
    if (resultAnalyse != ("", ""))
      phrase = dicoExpr(2).replace("XXX", resultAnalyse._1) + " : " + resultAnalyse._2
    else phrase = dicoExpr(3)
    phrase
  }
}
