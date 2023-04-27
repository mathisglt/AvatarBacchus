package construction_result

import analyse_phrase.AnalyseImpl
import langue.LangueImpl

object ConstructionImpl extends ConstructionTrait {

  def construireLangue(requete: String): List[String] = {
    val detection = AnalyseImpl.detecLangue(requete) // recherche de mots de détection dans la phrase
    val dicoExpr = AnalyseImpl.getDicoLangue(detection._2) // on récupère le dico correspondant à la langue détéctée
    if (detection._1 && detection._2 != detection._3) { 
      // detection._1 = une langue est détéctée, detection._2 = la langue détéctée, detection._3 la langue actuelle car construction n'y a pas accès
      dicoExpr(4) :: Nil 
      // on renvoie la question correspondante si jamais la langue détectée est différente de la langue actuelle
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
    var resultAnalyse = AnalyseImpl.analyser(requete)
    val dicoExpr = AnalyseImpl.getDicoLangue
    resultAnalyse match {
      case Nil => dicoExpr(3)
      case head :: Nil => dicoExpr(2).replace("XXX", head._1) + " : " + head._2
      case head :: next => ??? // TODO gérer les différents résultats
    }
  }
}
