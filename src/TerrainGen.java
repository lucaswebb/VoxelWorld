/**
 * Created by lucaswebb on 5/25/15.
 */
public class TerrainGen {

    Perlin p = new Perlin();

    public TerrainGen(){

    }

    public float getHeight(int x, int y){
        float height = Math.abs(20*p.perlin2D((float)x,(float)y));
        return height;
    }
}