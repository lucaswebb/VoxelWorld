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
import javax.swing.JOptionPane;


public class GameLoop {

    CameraControl camera = new CameraControl();
    private World w;
    private float redPlace = 1;
    private float bluePlace = 1;
    private float greenPlace = 1;
    private float alphaPlace = 1;
    private String input;
    boolean singleClick = false;
    boolean isClipping = false;
    boolean highlight = true;



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
        colorSelector();
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            isClipping = !isClipping;
            for(int i = 0; i < 6; i++){
                camera.setClip(i,true);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){
            highlight = !highlight;
        }
        clipTest();
    }

    public void clipTest(){
        if(isClipping) {
            Block temp = new Block(camera.getPos()[0], camera.getPos()[1], camera.getPos()[2],0,0,0,0);

            int xt = temp.getX();
            int yt = temp.getY();
            int zt = temp.getZ();

            temp.setX(temp.getX()+500);
            if(w.blockInWorld(temp)){
                camera.setClip(2, false);
            }
            else{
                camera.setClip(2, true);
            }

            temp.setX(xt);
            temp.setY(yt);
            temp.setZ(zt);

            temp.setX(temp.getX() - 500);
            if(w.blockInWorld(temp)){
                camera.setClip(3, false);
            }
            else{
                camera.setClip(3, true);
            }

            temp.setX(xt);
            temp.setY(yt);
            temp.setZ(zt);

            temp.setY(temp.getY() + 500);
            if(w.blockInWorld(temp)){
                camera.setClip(4, false);
            }
            else{
                camera.setClip(4, true);
            }

            temp.setX(xt);
            temp.setY(yt);
            temp.setZ(zt);

            temp.setY(temp.getY() - 500);
            if(w.blockInWorld(temp)){
                camera.setClip(5, false);
            }
            else{
                camera.setClip(5, true);
            }

            temp.setX(xt);
            temp.setY(yt);
            temp.setZ(zt);

            temp.setZ(temp.getZ() + 500);
            if(w.blockInWorld(temp)){
                camera.setClip(0, false);
            }
            else{
                camera.setClip(0, true);
            }

            temp.setX(xt);
            temp.setY(yt);
            temp.setZ(zt);

            temp.setZ(temp.getZ() - 500);
            if(w.blockInWorld(temp)){
                camera.setClip(1, false);
            }
            else{
                camera.setClip(1, true);
            }
        }
    }


    public void placeRemoveBlocks(){
        int blocksNeeded = 0;
        Block temp;
        for(int i = 1; i < 8; i++){
            temp = new Block(camera.getPos()[0] + (int) (500 * (i) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[2] + (int) (500 * (i) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[1] + (int) (500 * (i) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))),redPlace,greenPlace,bluePlace,alphaPlace);
            if(w.blockInWorld(temp)) {
                blocksNeeded = i;
                break;
            }
        }
        if(blocksNeeded>1) {
            if(highlight){
                Block highlight = new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded-1) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[2] + (int) (500 * (blocksNeeded-1) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[1] + (int) (500 * (blocksNeeded-1) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), 1, 1, 1, .1f);
                w.highlightBlock(highlight);
            }
            if (!singleClick) {
                if (Mouse.isButtonDown(0)) {
                    temp = new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded-1) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[2] + (int) (500 * (blocksNeeded-1) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[1] + (int) (500 * (blocksNeeded-1) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), redPlace, greenPlace, bluePlace, alphaPlace);
                    w.addBlock(temp);
                    singleClick = true;
                } else if (Mouse.isButtonDown(1)) {
                    temp =new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[2] + (int) (500 * (blocksNeeded) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[1] + (int) (500 * (blocksNeeded) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), redPlace, greenPlace, bluePlace, alphaPlace);
                    w.removeBlock(temp);
                    singleClick = true;
                }
            }
        }
        if(!Mouse.isButtonDown(0)&&!Mouse.isButtonDown(1)){
            singleClick = false;
        }
    }

    public void colorSelector(){
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            input = JOptionPane.showInputDialog(null, "Choose Red:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            redPlace = Float.parseFloat(input);
            input = JOptionPane.showInputDialog(null, "Choose Green:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            greenPlace = Float.parseFloat(input);
            input = JOptionPane.showInputDialog(null, "Choose Blue:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            bluePlace = Float.parseFloat(input);
            input = JOptionPane.showInputDialog(null, "Choose Alpha:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            alphaPlace = Float.parseFloat(input);
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
    }

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        manageGame();
    }

    public static void main(String[] args){
        GameLoop loop = new GameLoop();
        loop.start();
    }
}