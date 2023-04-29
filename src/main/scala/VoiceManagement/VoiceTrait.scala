package VoiceManagement

trait VoiceTrait {

  /** ajoute un message dans la file d'attente et demarre la lecture s'il est le seul
    *
    * @param message un message a lire
    * @param langue une langue
    */
  def ajouteMessage(message: String, langue: Int): Unit
}
