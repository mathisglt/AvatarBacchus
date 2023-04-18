package VoiceManagement

trait VoiceTrait {

  /** initialise la voix à français
    */
  def InitVoix(): Unit

  /** change la langue en la langue demandée
    *
    * @param langue un entier correspondant à la langue voulue (l'espagnol n'est pas considéré)
    * @return opère un changement sur la langue de parole
    */
  def voice(langue: Int): Unit

  /** prononce la phrase en paramètre
    *
    * @param phrase une phrase sous forme de String
    * @return la phrase sous forme de son
    */
  def say(phrase: String): Unit
}
