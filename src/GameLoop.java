/**
 * Created by lucaswebb on 5/15/15.
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameLoop {
    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(){

    }

    public void initGL(){

    }

    public void renderGL(){

    }

    public static void main(String[] args){

    }
}
