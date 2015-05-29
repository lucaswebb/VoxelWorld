public class TerrainGen {

    Perlin p = new Perlin();

    public TerrainGen(){

    }

    //returns a height value for a given x and y
    public float getHeight(int x, int y){
        float height = Math.abs(20*p.perlin2D((float)x,(float)y));
        return height;
    }
}