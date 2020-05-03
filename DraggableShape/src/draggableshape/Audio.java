/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggableshape;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
 
public class Audio extends Thread
{
    private URL url;
    private AudioClip sound;
  
    public Audio()
    {
        url = this.getClass().getClassLoader().getResource("1680.wav");
        sound = Applet.newAudioClip(url);
    }
     
    public void jouer()
    {
        sound.play();
    }
     
    public void jouerEnBoucle()
    {
        sound.loop();
    }
     
    public void arreter()
    {
        sound.stop();
    }
}