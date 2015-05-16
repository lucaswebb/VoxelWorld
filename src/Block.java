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

    public void render(int n){
        if(canRender) {
            GL11.glPushMatrix();
            GL11.glTranslated(x+64*n, y+64*n, z+64*n);
            cube(100);
            GL11.glPopMatrix();
        }
    }

    public void cube(int f){
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(red, green, blue, alpha);

        // Top face
        GL11.glVertex3d( 1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, 1.0*f,  1.0*f);
        GL11.glVertex3d( 1.0*f, 1.0*f,  1.0*f);

        // Bottom face
        GL11.glVertex3d( 1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);

        // Front face
        GL11.glVertex3d( 1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, 1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, 1.0*f);
        GL11.glVertex3d( 1.0*f, -1.0*f, 1.0*f);

        // Back face
        GL11.glVertex3d( 1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d( 1.0*f,  1.0*f, -1.0*f);

        // Left face
        GL11.glVertex3d(-1.0*f,  1.0*f,  1.0*f);
        GL11.glVertex3d(-1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f, -1.0*f);
        GL11.glVertex3d(-1.0*f, -1.0*f,  1.0*f);

        // Right face
        GL11.glVertex3d(1.0*f,  1.0*f, -1.0*f);
        GL11.glVertex3d(1.0*f,  1.0*f,  1.0*f);
        GL11.glVertex3d(1.0*f, -1.0*f,  1.0*f);
        GL11.glVertex3d(1.0*f, -1.0*f, -1.0*f);
        GL11.glEnd();
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
