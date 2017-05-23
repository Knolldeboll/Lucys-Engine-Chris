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

/**
 * Created by lucy on 23.03.17.
 */
public class Animation {
    public Animation(Sprite sprite){
        this.sprite = sprite;
        if(GameRuntime.isInitialized()){
            System.err.println("could not initialize sprites, do it in init();");
            GameRuntime.error();
        }
        GameRuntime.getScene().animations.add(this);
    }
    public Sprite sprite;
    public boolean running=false;
    public void step(){
    }
    public void doIt(long time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                enable();
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                disable();
            }
        }).start();
    }
    public void enable(){
        running = true;
    }
    public void disable(){
        running = false;
    }
    public void move(Point p){
        if(sprite==null)return;
    }
    public void moveTo(Point p){
        if(sprite==null)return;
    }
    public boolean loop;
    //move x, y, z = Vector(x, y, z)
    //rotate pitch, yaw, roll = Vector(pitch, yaw, roll)
    //loop = true|false
}
