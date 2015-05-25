/**
 * Created by lucaswebb on 5/15/15.
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class GameLoop {

    CameraControl camera = new CameraControl();
    private World w;

    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        initGL(); // init OpenGL

        Mouse.setGrabbed(true);

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

            update();
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }

        Display.destroy();
    }

    public void update(){
        camera.acceptInput(1);
        camera.apply();
    }

    public void initGL(){
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //GL11.glOrtho(-50, 50, -50, 50, 600, -600);
        GLU.gluPerspective(60,(float)800/600,600,-600);
        GLU.gluLookAt(0,0,0,10,0,0,0,1,0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        w = new World();
        w.setUp();

    }

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        w.render();
        //System.out.println(camera.getPos()[0]+ " " + camera.getPos()[1] + " " +camera.getPos()[2]);
        System.out.println(w.getChunkReal(camera.getPos()[0],camera.getPos()[1],camera.getPos()[2])[0]+" "
                +w.getChunkReal(camera.getPos()[0],camera.getPos()[1],camera.getPos()[2])[1]+ " "+ w.getChunkReal(
                camera.getPos()[0],camera.getPos()[1],camera.getPos()[2])[2]);
    }

    public static void main(String[] args){
        GameLoop loop = new GameLoop();
        loop.start();
    }
}