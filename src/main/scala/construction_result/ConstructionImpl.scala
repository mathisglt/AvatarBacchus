package construction_result

import analyse_phrase.AnalyseImpl
import langue.LangueImpl

object ConstructionImpl extends ConstructionTrait {

  // variables qui seront réinitialisées avec MachineImpl.reinit() :

  var choix_en_cours = false // devient true lorsque le user doit effectuer un choix entre plusieurs lieux proposés
  var liste_propositions_saved: List[String] = List() // contient la liste des lieux proposés à l'utlisateur

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

  /** Check les bonjours et repond le cas echéant
    *
    * @param requete la requete de l'utilisateur
    * @return un bonjour si bonjour + construire
    */
  def construirePolitesse(requete: String): List[String] = {
    val couple = AnalyseImpl.politeTest_OnlyBonjour(requete)
    val couple2 = AnalyseImpl.politeTest_Bonjour(requete)
    if (couple._1) couple._2 :: Nil // couple._1 = seulement bonjour, couple._2 = bonjour dans la langue correspondante
    else if (couple2._1) couple2._2 :: construireLesReponses(requete) // couple2._1 = bonjour + une requete, couple._2 = bonjour dans la langue correspondante
    else construireLesReponses(requete) // reponse du bot sans politesse
  }

  /** Fonction qui va construire les reponses du bot, une liste de propositions ou une liste avec une reponse seule
    * (la gestion de la langue et de la politesse est deja effectuee en amont de cette fonction)
    *
    * @param requete la requete du user
    * @return la liste de la (des) proposition(s)
    */
  def construireLesReponses(requete: String): List[String] = {
    val liste_couples = AnalyseImpl.analyser(requete)
    val liste_lieux = liste_couples.map{case (lieu,adresse) => lieu}
    // on commence par sauvegarder les propositions de lieux si aucun choix n'est en cours :
    if (!choix_en_cours) {liste_propositions_saved = liste_lieux.sorted}
    // lorsque l'on doit identifier le choix du user parmi nos propositions :
    if (choix_en_cours) {
      val choix = AnalyseImpl.analyserChoix(requete)
      val dicoExpr = AnalyseImpl.getDicoLangue
      choix_en_cours = false
      choix match {
        case None => 
          List(dicoExpr(3)) // "Je ne comprends pas votre demande"
        case Some(value) =>
          if (value > 0 && value <= liste_propositions_saved.length) { List(construireReponseUnique(liste_propositions_saved(value-1))) }
          else { List(dicoExpr(3)) } // "Je ne comprends pas votre demande"
      }
    }
    // lorsque l'on a plusieurs lieux a proposer, on va construire la liste de message les presentant :
    else if (liste_couples.length > 1) {
      choix_en_cours = true
      construireLesPropositions(liste_lieux)
    }
    // lorsque l'on a qu'une reponse a proposer, on va onstruire la phrase reponse :
    else List(construireReponseUnique(requete))
  }

  /** Construit la phrase de reponse pour l'utilisateur
    * 
    * @param requete la requete de l'utilisateur
    * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
    */
  def construireReponseUnique(requete: String): String = {
    var resultAnalyse = AnalyseImpl.analyser(requete)
    val dicoExpr = AnalyseImpl.getDicoLangue
    resultAnalyse match {
      case Nil => dicoExpr(3) // "Je ne comprends pas votre demande"
      case head :: Nil => dicoExpr(2).replace("XXX", head._1) + " : " + head._2 // "L'adresse de XXX est"
      case head :: next => 
        // il est normalement impossible de rentrer dans ce case car ce cas est gere dans la fonction precedente
        dicoExpr(3) // on renvoie cette reponse au cas ou
    }
  }

  /** On construit la liste de reponses lorsque le bot devra proposer plusieurs lieux
    *
    * @param liste_lieux la liste des lieux
    * @return une liste de string correspondant a la suite de reponses du bot proposant une liste de choix
    */
  def construireLesPropositions(liste_lieux: List[String]): List[String] = {
    val dicoExpr = AnalyseImpl.getDicoLangue
    val nb_rep = dicoExpr(6).replace("XXX", liste_lieux.length.toString()) + " :" // "J'ai XXX réponses possibles"
    val lieu_avec_numero = liste_propositions_saved.map(lieu => (liste_propositions_saved.indexOf(lieu)+1) + ") " + lieu)
    val question_choix = dicoExpr(7) // "Quel est votre choix?"
    nb_rep :: (lieu_avec_numero :+ question_choix)
  }
}
