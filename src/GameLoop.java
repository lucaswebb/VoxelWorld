import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import javax.swing.JOptionPane;

public class GameLoop {

    CameraControl camera = new CameraControl(); //Create camera
    private World w; //Create world
    //Set colors for placed blocks
    private float redPlace = 1;
    private float bluePlace = 1;
    private float greenPlace = 1;
    private float alphaPlace = 1;
    private String input; //Input string used to edit blocks

    boolean singleClick = false;  //Makes sure that button when held down does not constantly execute function
    boolean highlight = true; //Boolean that determines whether blocks are being highlighted

    //Dimensions of screen
    int screenWidth = 800;
    int screenHeight = 600;

    //Set up display
    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);

        }
        initGL(); // init OpenGL

        //Move cursor inside of the window
        Mouse.setCursorPosition(screenWidth/2,screenHeight/2);
        Mouse.setGrabbed(true);

        //Loop that runs game
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            update();
            renderGL();
            Display.update();
            Display.sync(60); // cap fps to 60fps
        }
        Display.destroy();
    }

    //Runs functions that work the game
    public void manageGame(){
        w.setCamera(camera.getPos());
        w.render();
        editBlocks();
        colorSelector();
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){
            highlight = !highlight;
        }
    }

    //Handles the editing of all blocks
    public void editBlocks(){
        int blocksNeeded = 0;
        Block temp;
        //Shoots out a ray from the camera and sets blocksNeeded equal to the number of blocks the closest block to the player is along that array
        for(int i = 1; i < 8; i++){
            temp = new Block(camera.getPos()[0] + (int) (500 * (i) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[2] + (int) (500 * (i) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[1] + (int) (500 * (i) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))),redPlace,greenPlace,bluePlace,alphaPlace); //Block position used to check and see if any block overlaps with this block
            if(w.blockInWorld(temp)) {
                blocksNeeded = i;
                break;
            }
        }
        //If the ray found a block
        if(blocksNeeded>1) {
            if(highlight){ //If highlight is turned on
                Block highlight = new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded-1) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[2] + (int) (500 * (blocksNeeded-1) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                    camera.getPos()[1] + (int) (500 * (blocksNeeded-1) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), 1, 1, 1, 1f); //Creates highlight block based on the ray.  Is one block before on the ray
                w.editBlock(highlight, 1); //Creates highlight block
            }
            if (!singleClick) {
                if (Mouse.isButtonDown(1)) { //Right Click
                    temp = new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded-1) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[2] + (int) (500 * (blocksNeeded-1) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[1] + (int) (500 * (blocksNeeded-1) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), redPlace, greenPlace, bluePlace, alphaPlace); //Creates block to be added. Is one block before on the ray
                    w.editBlock(temp, 0); //adds block
                    singleClick = true;
                } else if (Mouse.isButtonDown(0)) { //Left Click
                    temp =new Block(camera.getPos()[0] + (int) (500 * (blocksNeeded) * Math.cos(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[2] + (int) (500 * (blocksNeeded) * Math.sin(camera.getRot()[1] * (2 * Math.PI / 360))*Math.cos(camera.getRot()[0] * (2 * Math.PI / 360))),
                            camera.getPos()[1] + (int) (500 * (blocksNeeded) * -Math.sin(camera.getRot()[0] * (2 * Math.PI / 360))), redPlace, greenPlace, bluePlace, alphaPlace);
                    w.editBlock(temp, 2); // removes block.  Is the block on the ray
                    singleClick = true;
                }
            }
        }
        if(!Mouse.isButtonDown(0)&&!Mouse.isButtonDown(1)){ //Resets single click
            singleClick = false;
        }
    }

    public void colorSelector(){ //Used to determine what color block is being placed
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){ //When Q is pressed
            //Creates JOptionPanes that take in an input from 0 to 1 and sets those for th RGB values of a block
            input = JOptionPane.showInputDialog(null, "Choose Red:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            redPlace = Float.parseFloat(input);
            input = JOptionPane.showInputDialog(null, "Choose Green:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            greenPlace = Float.parseFloat(input);
            input = JOptionPane.showInputDialog(null, "Choose Blue:", "Color Selector",
                    JOptionPane.PLAIN_MESSAGE);
            bluePlace = Float.parseFloat(input);
        }
    }

    public void update(){ //Updates camera
        camera.acceptInput(1);
        camera.apply();
    }

    public void initGL(){ //Initializes properties
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //GL11.glOrtho(-50, 50, -50, 50, 600, -600);
        GLU.gluPerspective(60,(float)800/600,600,-600);
        GLU.gluLookAt(0,0,0,10,0,0,0,1,0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        w = new World();
    }

    public void renderGL() { //Set render properties and runs manageGame()
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        manageGame();
    }

    public static void main(String[] args){ //Starts the Game
        GameLoop loop = new GameLoop();
        loop.start();
    }
}