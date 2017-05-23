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

import java.util.HashMap;

/**
 * Created by lucy on 12.03.17.
 */
public abstract class TimeKeeper {
    static HashMap<String, Long> buffer = new HashMap<String, Long>();
    public static void mark(String s){
        buffer.put(s, System.currentTimeMillis());
    }
    public static void print(String s){
        long t = System.currentTimeMillis()-buffer.get(s);
        if(t!=0)System.out.println(s+" = "+t+" ms");
    }
}