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
public class Sprite {
    private int[] texture_id;
    public boolean enabled;
    public int activeTexture;
    public double depth = 0;
    public Point position=new Point(0, 0), orientation=new Point(0, 0), scale;
    public Point p00=new Point(0, 0), p10=new Point(1, 0), p01=new Point(0, 1), p11=new Point(1, 1);
    public Sprite(){
        if(GameRuntime.isInitialized()){
            System.err.println("could not initialize sprites, do it in init();");
            GameRuntime.error();
        }
        GameRuntime.getScene().sprites.add(this);
    }
    public void setTexture_id(int[] ids){
        texture_id = ids;
        activeTexture = 0;
    }
    public int getActiveTexture(){
        return texture_id[activeTexture];
    }
    public void setScale(Point scale){
        scale = scale.clone();
        p00.setX(-(scale.getX()/2));
        p00.setY(-(scale.getY()/2));
        p10.setX((scale.getX()/2));
        p10.setY(-(scale.getY()/2));
        p01.setX(-(scale.getX()/2));
        p01.setY((scale.getY()/2));
        p11.setX((scale.getX()/2));
        p11.setY((scale.getY()/2));
    }
}