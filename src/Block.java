import org.lwjgl.opengl.GL11;

/**
 * Created by arjun on 5/15/15.
 */
public class Block {
    private int x;
    private int y;
    private int z;
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private boolean canRender = true;
    Block(int a, int b, int c, int d, int e, int f, int g) {
        x = a;
        y = b;
        z = c;
        red = d;
        green = e;
        blue = f;
        alpha = g;
    }

    public void render(){
        if(canRender) {
            GL11.glPushMatrix();
            GL11.glTranslated(x, y, z);
            cube(100);
            GL11.glPopMatrix();
        }
    }

    public void cube(int f){
        GL11.glBegin(GL11.GL_QUADS);          // Begin drawing the color cube with 6 quads
        // Top face (y = 1.0f)
        // Define vertices in counter-clockwise (CCW) order with normal pointing out
        GL11.glColor3f(0.0f, 1.0f, 0.0f);     // Green
        GL11.glVertex3d( 1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f,  1.0*f);
        GL11.glVertex3d( 1.0*f, 1.0*f,  1.0*f);

        // Bottom face (y = -1.0f)
        GL11.glColor3f(1.0f, 0.5f, 0.0f);     // Orange
        GL11.glVertex3d( 1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);

        // Front face  (z = 1.0f)
        GL11.glColor3f(1.0f, 0.0f, 0.0f);     // Red
        GL11.glVertex3d( 1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, 1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, 1.0*f);

        // Back face (z = -1.0f)
        GL11.glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f,  1.0*f, -1.0*f);

        // Left face (x = -1.0f)
        GL11.glColor3f(0.0f, 0.0f, 1.0f);     // Blue
        GL11.glVertex3d(-1.0*f,  1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);

        // Right face (x = 1.0f)
        GL11.glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
        GL11.glVertex3d(1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d(1.0*f,  1.0*f,  1.0*f);
        GL11.glVertex3d(1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(1.0*f, -1.0*f, -1.0*f);
        GL11.glEnd();  // End of drawing color-cube
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
    public int getAlpha() {
        return alpha;
    }
}
