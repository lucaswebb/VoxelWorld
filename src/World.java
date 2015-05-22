/**
 * Created by arjun on 5/21/15.
 */
import java.util.ArrayList;
public class World {
    private ArrayList<Chunk> chunks;
    private int camarax = 0;
    private int camaray = 0;
    private int camaraz = 0;
    World(){
        chunks = new ArrayList<Chunk>();
    }
    public void setUp(){
        chunks.add(new Chunk(0,0,0));
        chunks.add(new Chunk(-1,0,0));
        chunks.add(new Chunk(0,-1,0));
        chunks.add(new Chunk(-1,-1,0));
        for(Chunk chunk : chunks){
            chunk.setUp();
        }
    }
    public void setCamaraX(int a){
        camarax = a;
    }
    public void setCamaraY(int a){
        camaray = a;
    }
    public void setCamaraZ(int a){
        camaraz = a;
    }
    public void render(){
        for(int i = 0; i < chunks.size(); i++){
            System.out.println(chunks.get(i).getX() + " " + chunks.get(i).getY());
            chunks.get(i).render();

        }
    }
    public void getLocation(int x, int y, int z){

    }

}
