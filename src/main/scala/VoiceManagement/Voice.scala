/*package VoiceManagement
 
import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import java.io.IOException


object  Voice
{
    private val interface=MaryInterface;
    private val audioPlayer=ap.AudioPlayer ;
            
    def voice(voicename: String):Unit={
        try
        {
            val marytts = new LocalMaryInterface();
            marytts.setVoice(voiceName);
            val ap = new AudioPlayer();
        }
        catch (MaryConfigurationException ex)
        {
            ex.printStackTrace();
        }
    }
    
    def say(input: String):Unit={
        try
        {
            val AudioInputStream = marytts.generateAudio(input);
            
            ap.setAudio(audio);
            ap.start();
        }
        catch (SynthesisException ex)
        {
            System.err.println("Error saying phrase.");
        }
    }
}*/
