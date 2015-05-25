/**
 * Created by lucaswebb on 5/15/15.
 */

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class CameraControl {
    public static float moveSpeed = 30.0f;

    private static float maxLook = 85;

    private static float mouseSensitivity = 0.05f;

    private static Vector3f pos;
    private static Vector3f rotation;

    public CameraControl(){
        this.create();
    }

    public static void create() {
        pos = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
    }

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

    public static void acceptInput(float delta) {
        acceptInputRotate(delta);
        acceptInputGrab();
        acceptInputMove(delta);
    }

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

    public static void acceptInputMove(float delta) {
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
        if(keyFlyDown) {
            pos.y -= speed;
        }

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
