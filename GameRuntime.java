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

import beer.Game;

/**
 * Created by lucy on 23.03.17.
 */
public class GameRuntime {
    public static boolean debug;
    public static void start(boolean d){
        debug = d;
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                //Trashman.clean();
                System.out.println("exit...");
            }
        }));
        if(d)debugRun();
        else{

        }
    }
    public static boolean isRunning(){
        return game.isRunning;
    }
    private static Game game;
    public static void error(){
        game.exit();
        scene.close();
        System.exit(-1);
    }
    private static boolean initialized;
    public static boolean isInitialized(){
        return initialized;
    }
    private static Scene scene;
    public static Scene getScene(){
        return scene;
    }
    private static void debugRun(){
        scene = new Scene(debug, 800, 600);
        game = new Game();
        game.init();

        initialized=true;

        Thread stepThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(game.isRunning){
                    game.step();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //if(debug && org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_ESCAPE))System.exit(0);
                    //System.out.println("reached break");
                    //break;
                }
            }
        });
        stepThread.start();
        scene.start();

        game.exit();
        scene.close();
    }
    public static void shutdown(){

    }
}
