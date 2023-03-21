package construction_result

trait ConstructionTrait {
  /**
      * Amélioration de la v0 qui prend plusieurs couples (lieu, adresse) et rend les phrases résultats
      * @param resultAnalyse le resultat de l'analyse d'un couple (lieu, adresse)
      * @return la phrases representant la reponse sous la forme d'une String
      */
    def construire(resultat: String):String 

}
