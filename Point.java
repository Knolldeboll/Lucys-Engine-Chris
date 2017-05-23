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
public class Point {
    private double x, y, s=1.0;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    private Point(double x, double y, double s){
        this.x = x;
        this.y = y;
        this.s = s;
    }
    public void setX(double d){
        x = d*s;
    }
    public void setY(double d){
        y = d*s;
    }
    public void setScale(double d){
        s = d*s;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getScale(){
        return s;
    }
    public Point clone(){
        return new Point(x, y, s);
    }
}
