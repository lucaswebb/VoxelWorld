/**
 * Created by lucaswebb on 5/25/15.
 */
public class TerrainGen {

    Perlin p = new Perlin();

    public TerrainGen(){

    }

    public int getHeight(int x, int y){
        float height = Math.abs(10*p.perlin2D((float)x,(float)y));
        System.out.println(height);
        return  (int)height;
    }
}