/**
 /* This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 3.0, as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package vodka;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by lucy on 23.03.17.
 */
public class Sound {
    AdvancedPlayer player;
    Thread musicThread;
    boolean looping;
    public Sound(final String file){
        try {
            player = new AdvancedPlayer(new FileInputStream(file));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private AudioInputStream convertToPCM(AudioInputStream audioInputStream)
    {
        AudioFormat m_format = audioInputStream.getFormat();

        if ((m_format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) && (m_format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED))
        {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    m_format.getSampleRate(), 16,
                    m_format.getChannels(), m_format.getChannels() * 2,
                    m_format.getSampleRate(), m_format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }

        return audioInputStream;
    }
    public void play(){
        if(looping)return;
        looping = true;
        musicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException e){
                    e.printStackTrace();
                }
                looping = false;
            }
        });
        musicThread.start();
    }
    public void loop(){
        if(looping)return;
        looping = true;
        musicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (looping){
                    try {
                        player.play();
                    } catch (JavaLayerException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        musicThread.start();
    }
    public void stop(){
        looping = false;
        player.stop();
        musicThread.stop();
    }
}
