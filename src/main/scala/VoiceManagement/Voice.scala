package VoiceManagement

import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import java.io.IOException
import marytts.server.Mary
//les différentes voix : [istc-lucia-hsmm(italien), dfki-pavoque-neutral-hsmm(allemand), upmc-pierre-hsmm(francais), cmu-slt-hsmm(anglais)]
object Voice extends VoiceTrait {
  private var interface: MaryInterface = new LocalMaryInterface();
  private var audioPlayer: AudioPlayer = new AudioPlayer();

  def InitVoix(): Unit = {
    interface = new LocalMaryInterface()
    interface.setVoice("upmc-pierre-hsmm")
  }

  def voice(langue: Int): Unit = {
    try {
      interface = new LocalMaryInterface();
      langue match {
        case 0 => interface.setVoice("upmc-pierre-hsmm");
        case 1 => interface.setVoice("cmu-slt-hsmm");
        case 3 => interface.setVoice("dfki-pavoque-neutral-hsmm");
        case 4 => interface.setVoice("istc-lucia-hsmm");
        case _ => throw new Exception("pas une langue audible")
      }

      audioPlayer = new AudioPlayer();
    } catch {
      case ex: MaryInterface => ex.printStackTrace();
    }
  }

  def say(phrase: String): Unit = {
    try {
      val audio: AudioInputStream = interface.generateAudio(phrase);

      audioPlayer.setAudio(audio);
      audioPlayer.start();
    } catch {
      case ex: SynthesisException => System.err.println("Error saying phrase.");
    }
  }
}
