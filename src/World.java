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
            chunks.get(i).render();
        }
    }

    public void addBlock(Block b){
        int[] temp = getChunkFake(b.getX(), b.getY(), b.getZ());
        for(int i = 0; i < chunks.size(); i++){
            if(chunks.get(i).getX()==temp[0]&&chunks.get(i).getY()==temp[1]&&chunks.get(i).getZ()==temp[2])
            {
                if(b.getX()>=0) {
                    b.setX(Math.abs(b.getX()));
                }
                else{
                    b.setX(16-Math.abs(b.getX()));
                }
                if(b.getY()>=0) {
                    b.setY(Math.abs(b.getY()));
                }
                else{
                    b.setY(16 - Math.abs(b.getY()));
                }
                if(b.getZ()>=0) {
                     b.setZ(Math.abs(b.getZ()));
                }
                else{
                    b.setZ(16-Math.abs(b.getZ()));
                }
                chunks.get(i).addBlock(b);
            }
        }

    }

    public void removeBlock(Block b){
        int[] temp = getChunkFake(b.getX(), b.getY(), b.getZ());
        for(int i = 0; i < chunks.size(); i++){
            if(chunks.get(i).getX()==temp[0]&&chunks.get(i).getY()==temp[1]&&chunks.get(i).getZ()==temp[2])
            {
                if(b.getX()>=0) {
                    b.setX(Math.abs(b.getX()));
                }
                else{
                    b.setX(16-Math.abs(b.getX()));
                }
                if(b.getY()>=0) {
                    b.setY(Math.abs(b.getY()));
                }
                else{
                    b.setY(16-Math.abs(b.getY()));
                }
                if(b.getZ()>=0) {
                    b.setZ(Math.abs(b.getZ()));
                }
                else{
                    b.setZ(16-Math.abs(b.getZ()));
                }
                chunks.get(i).removeBlock(b.getX(),b.getY(),b.getZ());
            }
        }

    }


    public int[] getChunkReal(int x, int y, int z){
        int[] ans = new int[3];
        if(x>=0){
            ans[0] = x/8000;
        }
        else{
            ans[0] = (x/8000) -1;
        }
        if(z>=0) {
            ans[1] = z / 8000;
        }
        else{
            ans[1] = (z/8000) -1;
        }
        if(y>=0) {
            ans[2] = y / 8000;
        }
        else{
            ans[2] = y/8000 - 1;
        }
        return ans;
    }
    public int[] getChunkFake(int x, int y, int z){
        int[] ans = new int[3];
        if(x>=0){
            ans[0] = x/16;
        }
        else{
            ans[0] = (x/16) -1;
        }
        if(y>=0) {
            ans[1] = y / 16;
        }
        else{
            ans[1] = (y/16) -1;
        }
        if(z>=0) {
            ans[2] = z / 16;
        }
        else{
            ans[2] = z/16 - 1;
        }
        return ans;
    }
}
