package construction_result

trait ConstructionTrait {
  /**
    * construit la phrase de reponse pour l'utilisateur
    * 
    * @param requete la requete de l'utilisateur
    * @return la phrase representant la reponse dans la langue voulu sous la forme d'une String
    */
    def construire(requete: String):String 

}
