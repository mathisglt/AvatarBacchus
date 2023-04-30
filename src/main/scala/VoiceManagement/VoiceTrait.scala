package VoiceManagement

trait VoiceTrait {

  /** Ajoute un message dans la file d'attente et démarre la lecture s'il est le seul
    *
    * @param message le message a lire
    * @param langue la langue dans laquelle lire le message
    */
  def ajouteMessage(message: String, langue: Int): Unit
}
