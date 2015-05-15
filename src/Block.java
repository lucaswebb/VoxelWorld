import org.lwjgl.opengl.GL11;

/**
 * Created by arjun on 5/15/15.
 */
public class Block {
    private int x;
    private int y;
    private int z;
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private boolean canRender = true;

    Block(int x, int y, int z, float r, float g, float b, float a) {
        this.x = x;
        this.y = y;
        this.z = z;
        red = r;
        green = g;
        blue = b;
        alpha = a;
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

        GL11.glColor4f(red, green, blue, alpha);

        // Top face (y = 1.0f)
        // Define vertices in counter-clockwise (CCW) order with normal pointing out
        GL11.glVertex3d( 1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f,  1.0*f);
        GL11.glVertex3d( 1.0*f, 1.0*f,  1.0*f);

        // Bottom face (y = -1.0f)
        GL11.glVertex3d( 1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);

        // Front face  (z = 1.0f)
        GL11.glVertex3d( 1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, 1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, 1.0*f);

        // Back face (z = -1.0f)
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f,  1.0*f, -1.0*f);

        // Left face (x = -1.0f)
        GL11.glVertex3d(-1.0*f,  1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);

        // Right face (x = 1.0f)
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
    public float getRed() {
        return red;
    }
    public float getGreen() {
        return green;
    }
    public float getBlue() {
        return blue;
    }
    public float getAlpha() {
        return alpha;
    }
}
