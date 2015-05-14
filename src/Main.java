/**
 * Created by lucaswebb on 5/14/15.
 */
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
    public Main() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();

            while(!Display.isCloseRequested()) {
                Display.update();
            }
//test
            Display.destroy();
        } catch(LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}