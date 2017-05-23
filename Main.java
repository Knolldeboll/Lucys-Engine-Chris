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

import java.util.Collection;

/**
 * Created by lucy on 23.03.17.
 */
public class Main {
    private static boolean debug = true;
    public static void main(String[] args){
        /*for(String s : args)switch (s){
            case "debug":
                debug = true;
                break;
            case "compile":
                compile();
                break;
            case "run":
                run();
                break;
        }*/
        run();
    }
    private static void compile(){
        Compiler compiler = new Compiler(debug);
    }
    private static void run(){
        GameRuntime.start(debug);
    }
}
