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
    Block(int a, int b, int c, int d, int e, int f, int g) {
        x = a;
        y = b;
        z = c;
        red = d;
        green = e;
        blue = f;
        alpha = g;
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
