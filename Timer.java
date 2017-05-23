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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luciano.willems on 11.05.2017.
 */
public class Timer {
    static HashMap<String, Long> marks = new HashMap<String, Long>();
    static HashMap<String, Long> time = new HashMap<>();
    public static void mark(String name, Long millis){
        marks.put(name, System.currentTimeMillis());
        time.put(name, millis);
    }
    public static boolean isDone(String name){
        long t = System.currentTimeMillis()-marks.get(name);
        //System.out.println("t = "+t);
        if(t>=time.get(name))return true;
        else return false;
    }
}