package construction_result

trait ConstructionTrait {
  /**
      * @param requete la requete de l'utilisateur
      * @param langue la langue confirmé par l'utilisateur
      * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
      */
    def construire(requete: String, langue: langue):String 

}
