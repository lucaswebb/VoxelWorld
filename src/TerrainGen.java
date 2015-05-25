/**
 * Created by lucaswebb on 5/25/15.
 */
public class TerrainGen {

    SimplexNoiseGenerator generator = new SimplexNoiseGenerator();

    public TerrainGen(){

    }

    public int getHeight(int x, int y){
        double noise = generator.noise(x, y)*10;
        System.out.println(noise);
        return  (int)noise;
    }
}
