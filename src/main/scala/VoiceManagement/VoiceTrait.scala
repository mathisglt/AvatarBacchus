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
    * @return le temps que l'interface met à dire la phrase et dit la phrase
    */
  def say(phrase: String): Long
}
