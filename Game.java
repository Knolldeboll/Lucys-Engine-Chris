package beer;

import java.util.*;
import org.lwjgl.input.Keyboard;
import vodka.*;

import java.io.Serializable;
import java.util.Timer;

/**
 * Created by lucy on 23.03.17.
 */
public class Game implements Serializable{
    double manY = 0;
    double backgroundX = 5;
    public boolean isRunning = true;
    Timer verz;
    ArrayList<Sprite> background = new ArrayList<>();
    Sprite man;
    Sprite caffeine;
    Animation backslide;
    Animation animation;
    Animation manimationUp;
    Animation manimationDown;
    Animation manimationJump;
    public void init(){
        //Sprites
        background.add(new Sprite());
        background.get(0).setScale(new Point(5,2));
        background.get(0).orientation = new Point(0,0);
        int backtex[] = new int[1];
        backtex[0] = Texture.loadTexture("Unbenannt.png");
        background.get(0).setTexture_id(backtex);
        background.get(0).position = new Point(3,0   );
        background.get(0).enabled = true;




        man = new Sprite();
        man.setScale(new Point(0.5, 0.5));
        man.orientation = new Point (0,0);
        int[] mantex = new int[2];
        mantex[0] = Texture.loadTexture("man1.png");
        mantex[1] = Texture.loadTexture("manJump.png");
        man.setTexture_id(mantex);
        man.enabled = true;

        /*caffeine = new Sprite();
        caffeine.setScale(new Point(0.5, 0.5));
        caffeine.orientation = new Point(0, 0);
        int[] tex = new int[2];
        tex[0] = Texture.loadTexture("download.png");
        tex[1] = Texture.loadTexture("stGrid2.png");
        caffeine.setTexture_id(tex);
        caffeine.enabled=false;*/

        //Animationen
        backslide = new Animation(background.get(0)){
            public void step(){
           backgroundX = backgroundX - 0.01;
            background.get(0).position.setX(backgroundX);

            }

        };

       /* animation = new Animation(caffeine){
            boolean dir;
            double x=0;
            public void step(){
                if(dir){
                    x+=0.01;
                    if(x>=0.5)dir=!dir;
                }
                else{
                    x-=0.01;
                    if(x<=-0.5)dir=!dir;
                }
                sprite.position.setX(x);
            }
        };*/


        manimationUp = new Animation(man){

            public void step(){
                if (manY < 0.7){
                    manY = manY + 0.01;
                    man.position.setY(manY);
                }
            }


        };

        manimationDown = new Animation(man){


            public void step(){
                if (manY > -0.7){
                    manY = manY - 0.01;
                    man.position.setY(manY);
                }
            }
        };

        manimationJump = new Animation(man){
        public void step(){

            man.activeTexture = 1;

            //hoch
            for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    manY = manY + 0.007;
                    man.position.setY(manY);

                }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            //runter
            for (int i = 0; i <= 100; i++){
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                  manY = manY - 0.007;
                    man.position.setY(manY);
                }

               man.activeTexture = 0;



            }
        };

            // ende animationen


    }


    public void step(){
       //Keydowns






        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) GameRuntime.getScene().stop();

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
         manimationUp.step();
        };
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
          manimationDown.step();
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
         manimationJump.step();
        }
       //Steps
        backslide.enable();

        System.out.println("step");
    }
    public void pause(){

    }
    public void resume(){

    }
    public void exit(){

    }
}
