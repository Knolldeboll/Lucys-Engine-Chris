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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by lucy on 23.03.17.
 */
public class Scene {
    private boolean debug;
    public ArrayList<Sprite> sprites = new ArrayList<>();
    public ArrayList<Animation> animations = new ArrayList<>();
    public Scene(boolean d, int w, int h){
        debug = d;
        try {
            Display.setDisplayMode(new DisplayMode(w, h));
            Display.setTitle("ConsoleSDK_v0.0.0.1");
            Display.create();

            GL11.glViewport(0, 0, w, h);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Display.setVSyncEnabled(true);
        // Setup an XNA like background color
        GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);

        // Map the internal OpenGL coordinate system to the entire screen
        GL11.glViewport(0, 0, w, h);
        if(d){
            System.out.println("OS name " + System.getProperty("os.name"));
            System.out.println("OS version " + System.getProperty("os.version"));
            System.out.println("LWJGL version " + org.lwjgl.Sys.getVersion());
            System.out.println("OpenGL version " + glGetString(GL_VERSION));
            //Display.destroy();
        }
    }
    private boolean render;
    public void start(){
        if(render && debug)System.out.println("already started :)");
        render=true;
        renderLoop();
    }
    int count = 0;
    private void renderLoop(){
        while(render){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            TimeKeeper.mark("render");
            for(Animation a : animations){
                if(a.running)a.step();
            }
            for(Sprite s : sprites){
                if(s.enabled){
                    glBindTexture(GL_TEXTURE_2D, s.getActiveTexture());
                    glEnable(GL_TEXTURE_2D);
                    glBegin(GL_QUADS);
                    glTexCoord2d(0, 1);
                    glVertex3d(s.p00.getX()+s.position.getX(), s.p00.getY()+s.position.getY(), s.depth);
                    glTexCoord2d(1, 1);
                    glVertex3d(s.p10.getX()+s.position.getX(), s.p10.getY()+s.position.getY(), s.depth);
                    glTexCoord2d(1, 0);
                    glVertex3d(s.p11.getX()+s.position.getX(), s.p11.getY()+s.position.getY(), s.depth);
                    glTexCoord2d(0, 0);
                    glVertex3d(s.p01.getX()+s.position.getX(), s.p01.getY()+s.position.getY(), s.depth);
                    glEnd();
                    count++;
                }
            }
            //TimeKeeper.print("render");
            //System.out.println("Sprites rendered: "+count);
            count = 0;
            //System.out.println("render");

            Display.sync(60);
            Display.update();
        }
        if(close) {
            System.out.println("closing window");
            Trashman.clean();
            Display.destroy();
        }
    }
    public void stop(){
        render=false;
    }
    private boolean close = false;
    public void close(){
        close=true;
        stop();
    }
}
