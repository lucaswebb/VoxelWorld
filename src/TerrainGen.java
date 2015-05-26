/**
 * Created by lucaswebb on 5/25/15.
 */
public class TerrainGen {

    NoiseGen generator = new NoiseGen(3);

    public TerrainGen(){

    }

    public int getHeight(int x, int y){
        float noise = generator.Noise(x, y,0);
        System.out.println(noise);
        return  (int)noise;
    }
}