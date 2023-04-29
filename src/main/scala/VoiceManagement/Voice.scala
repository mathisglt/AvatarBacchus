package VoiceManagement

import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import java.io.IOException
import marytts.server.Mary
import java.util.LinkedList
import application.Application
import java.util.concurrent.Semaphore
//les différentes voix : [istc-lucia-hsmm(italien), dfki-pavoque-neutral-hsmm(allemand), upmc-pierre-hsmm(francais), cmu-slt-hsmm(anglais)]
object Voice extends VoiceTrait {
  private val interface: MaryInterface = new LocalMaryInterface();
  private var audioPlayer: AudioPlayer = new AudioPlayer();
  private val fileAttente: LinkedList[(String, Int)] = new LinkedList()
  private val PriseDeParole = new Semaphore(1)

  def ajouteMessage(message: String, langue: Int): Unit = {
    fileAttente.synchronized {
      fileAttente.add((message, langue))
      if (fileAttente.size() == 1) {
        lire()
      }
    }
  }

  /** lis la file d'attente
    */
  def lire(): Unit = {
    fileAttente.synchronized {
      if (!fileAttente.isEmpty()) {
        val message = fileAttente.getFirst()._1
        val langue = fileAttente.getFirst()._2
        fileAttente.removeFirst()
        voice(langue)
        PriseDeParole.acquire()
        say(message)
      }
    }
  }

  /** initialise la voix à français
    */
  def InitVoix(): Unit = {
    interface.setVoice("upmc-pierre-hsmm")
  }

  /** change la langue en la langue demandée
    *
    * @param langue un entier correspondant à la langue voulue (l'espagnol n'est pas considéré)
    * @return opère un changement sur la langue de parole
    */
  def voice(langue: Int): Unit = {
    try {
      langue match {
        case 0 => interface.setVoice("upmc-pierre-hsmm")
        case 1 => interface.setVoice("cmu-slt-hsmm")
        case 3 => interface.setVoice("dfki-pavoque-neutral-hsmm")
        case 4 => interface.setVoice("istc-lucia-hsmm")
        case _ => throw new Exception("pas une langue audible")
      }
      audioPlayer = new AudioPlayer
    } catch {
      case ex: MaryInterface => ex.printStackTrace()
    }
  }

  /** prononce la phrase en paramètre
    *
    * @param phrase une phrase sous forme de String
    * @return le temps que l'interface met à dire la phrase et dit la phrase
    */
  def say(phrase: String): Unit = {
    try {
      val audio: AudioInputStream = interface.generateAudio(phrase)
      audioPlayer.setAudio(audio)
      audioPlayer.start()
      val TempsFichier =
        ((audio.getFrameLength() / 441 * 10)) // une frame = 1/44100 seconde donc 1/44,1 ms j'ai fait /441*10 pour eviter de passer par un double
      Thread.sleep(TempsFichier + 1500)
    } catch {
      case ex: SynthesisException =>
        throw new Exception("impossible de dire la phrase")
    } finally {
      PriseDeParole.release()
      lire()
    }
  }
}
object testVoix extends App {
  Voice.ajouteMessage("A l'ombre d'un grand chêne,", 0)
  Voice.ajouteMessage("vos laitues naissent-elles ?", 0)
  Voice.ajouteMessage("Si vos laitues naissent,", 0)
  Voice.ajouteMessage("mes navets naissent.", 0)
}
