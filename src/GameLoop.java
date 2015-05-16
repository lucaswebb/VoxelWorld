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

    CameraControl camera = new CameraControl(0,0,0);
    float movementSpeed = 10.0f;
    float mouseSensitivity = 0.05f;
    float dt = 0.0f;
    float dx;
    float dy;

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
        dx = Mouse.getDX();
        dy = Mouse.getDY();
        dt = 1.0f;

        camera.incrementYaw(dx * mouseSensitivity);
        camera.incrementPitch(dy * mouseSensitivity);

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
        GL11.glLoadIdentity();
        camera.lookThrough();
    }

    public void initGL(){
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //GL11.glOrtho(0, 800, 0, 600, 600, -600);
        GLU.gluPerspective(90,(float)800/600,100,-600);
        GLU.gluLookAt(0,300,1300,0,0,0,0,1,0);
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
