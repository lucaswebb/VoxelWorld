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

    public void manageGame(){
        w.setCamera(camera.getPos());
        w.render();
        placeRemoveBlocks();
    }


    public void placeRemoveBlocks(){
        int blocksNeeded = 0;
        Block temp;
        for(int i = 1; i < 6; i++){
            temp = new Block(camera.getPos()[0]+(int)(500*i*Math.cos(camera.getRot()[1]*(2*Math.PI/360))),
                    camera.getPos()[2]+(int)(500*i*Math.sin(camera.getRot()[1]*(2*Math.PI/360))),
                    camera.getPos()[1]+(int)(500*i*-Math.sin(camera.getRot()[0]*(2*Math.PI/360))),1,0,0,1);
            if(w.blockInWorld(temp)){
                blocksNeeded=i;
                break;
            }
        }
        System.out.println(blocksNeeded);
        if(blocksNeeded>1) {
            if (Mouse.isButtonDown(0)) {
                temp = new Block(camera.getPos()[0] + (int) (500*(blocksNeeded-1) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))),
                        camera.getPos()[2] + (int) (500*(blocksNeeded-1) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))),
                        camera.getPos()[1] + (int) (500*(blocksNeeded-1) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), 1, 0, 0, 1);
                w.addBlock(temp);
            } else if (Mouse.isButtonDown(1)) {
                temp = new Block(camera.getPos()[0] + (int) (500*blocksNeeded * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))),
                        camera.getPos()[2] + (int) (500*blocksNeeded * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))),
                        camera.getPos()[1] + (int) (500*blocksNeeded * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), 1, 0, 0, 1);
                w.removeBlock(temp);
            }
        }
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
        //w.setUp();

    }

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        manageGame();
    }

    public static void main(String[] args){
        GameLoop loop = new GameLoop();
        loop.start();
    }
}