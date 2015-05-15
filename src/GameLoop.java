/**
 * Created by lucaswebb on 5/15/15.
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GameLoop {

    CameraControl camera = new CameraControl(0,0,0);
    float movementSpeed = 10.0f;
    float dt = 0.0f;

    //Mouse.setGrabbed(true);

    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        initGL(); // init OpenGL

        while (!Display.isCloseRequested()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W))//move forward
            {
                camera.walkForward(movementSpeed*dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S))//move backwards
            {
                camera.walkBackwards(movementSpeed*dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A))//strafe left
            {
                camera.strafeLeft(movementSpeed*dt);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D))//strafe right
            {
                camera.strafeRight(movementSpeed*dt);
            }

            camera.lookThrough();
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }

        Display.destroy();
    }

    public void update(){

    }

    public void initGL(){
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 600, -600);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        Block b = new Block(400,300,0,0,1,0,1);
        b.render();
    }

    public static void main(String[] args){
        GameLoop loop = new GameLoop();
        loop.start();
    }
}
