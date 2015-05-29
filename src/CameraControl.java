import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class CameraControl {
    public static float moveSpeed = 500.0f;
    private static float maxLook = 85;
    private static float mouseSensitivity = 0.05f;

    private static Vector3f pos;
    private static Vector3f rotation;

    public CameraControl(){
        this.create();
    }

    public int[] getPos(){
        int[] temp = new int[3];
        temp[0] = (int)pos.x;
        temp[1] = (int)pos.y;
        temp[2] = (int)pos.z;
        return temp;
    }

    //returns position vector
    public int[] getRot(){
        int[] temp = new int[3];
        temp[0] = (int)rotation.x;
        temp[1] = (int)rotation.y;
        temp[2] = (int)rotation.z;
        return temp;
    }

    public static void create() {
        pos = new Vector3f((float)(1000000*Math.random()+1000000), 20000, (float)(1000000*Math.random()+1000000));
        rotation = new Vector3f(0, 0, 0);
    }

    //applies transformation to world
    public static void apply() {
        if(rotation.y / 360 > 1) {
            rotation.y -= 360;
        } else if(rotation.y / 360 < -1) {
            rotation.y += 360;
        }
        GL11.glLoadIdentity();
        GL11.glRotatef(rotation.x, 0, 0, 1);
        GL11.glRotatef(rotation.y, 0, 1, 0);
        GL11.glRotatef(rotation.z, 1, 0, 0);
        GL11.glTranslatef(-pos.x, -pos.y, -pos.z);
    }

    //gets input
    public void acceptInput(float delta) {
        acceptInputRotate(delta);
        acceptInputGrab();
        acceptInputMove(delta);
    }

    //gets mouse input
    public static void acceptInputRotate(float delta) {
        if(Mouse.isGrabbed()) {
            float mouseDX = Mouse.getDX();
            float mouseDY = Mouse.getDY();
            rotation.y += mouseDX * mouseSensitivity * delta;
            rotation.x += -mouseDY * mouseSensitivity * delta;
            rotation.x = Math.max(-maxLook, Math.min(maxLook, rotation.x));
        }
    }


    public static void acceptInputGrab() {
        if(Mouse.isInsideWindow() && Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Mouse.setGrabbed(false);
        }
    }

    //accepts keyboard input
    public void acceptInputMove(float delta) {
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean keyFast = Keyboard.isKeyDown(Keyboard.KEY_Q);
        boolean keySlow = Keyboard.isKeyDown(Keyboard.KEY_E);
        boolean keyFlyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean keyFlyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);


        float speed;

        if(keyFast) {
            speed = moveSpeed * 5;
        }
        else if(keySlow) {
            speed = moveSpeed / 2;
        }
        else {
            speed = moveSpeed;
        }

        speed *= delta;

        if(keyFlyUp) {
            pos.y += speed;
        }
        if(keyFlyDown ) {
            pos.y -= speed;
        }

        //trig for moving based on rotation
        if(keyRight) {
            pos.x -= Math.sin(Math.toRadians(rotation.y)) * speed;
            pos.z += Math.cos(Math.toRadians(rotation.y)) * speed;
        }
        if(keyLeft) {
            pos.x += Math.sin(Math.toRadians(rotation.y)) * speed;
            pos.z -= Math.cos(Math.toRadians(rotation.y)) * speed;
        }
        if(keyDown) {
            pos.x += Math.sin(Math.toRadians(rotation.y - 90)) * speed;
            pos.z -= Math.cos(Math.toRadians(rotation.y - 90)) * speed;
        }
        if(keyUp) {
            pos.x += Math.sin(Math.toRadians(rotation.y + 90)) * speed;
            pos.z -= Math.cos(Math.toRadians(rotation.y + 90)) * speed;
        }
    }
}
