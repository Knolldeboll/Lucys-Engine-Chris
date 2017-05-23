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

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by lucy on 23.03.17.
 */
public abstract class Trashman {
    public static ArrayList<Integer> texture_ids = new ArrayList<Integer>();
    public static void clean(){
        System.out.println("cleaning..."+texture_ids.size());

        for(int c=0; c<texture_ids.size();c++){
            GL11.glDeleteTextures(texture_ids.get(c));
            System.out.println("deleting texture "+c+"/"+(texture_ids.size()));
        }
        texture_ids.clear();
    }
}
