/**
 * Created by arjun on 5/21/15.
 */
import java.util.ArrayList;
public class World {
    private ArrayList<Chunk> chunks;
    private int camerax = 0;
    private int cameray = 0;
    private int cameraz = 0;

    TerrainGen generator = new TerrainGen();

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
    public void setCamera(int[] a){
        camerax = a[0];
        cameray = a[1];
        cameraz = a[2];
    }



    public void render(){
        for(int i = -1; i < 2; i++){
            for(int k = -1; k < 2; k++){
                if(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)>=0){
                    chunks.get(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)).render();
                } else {
                    chunks.add(new Chunk(getChunkReal(camerax, 0, cameraz)[0]+i
                            ,getChunkReal(camerax, 0, cameraz)[1]+k
                            ,getChunkReal(camerax,0,cameraz)[2]));
                    chunks.get(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)).setUp();
                }
            }
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

    public int isInWorld(int[] a, int shiftX, int shiftY, int shiftZ){
        for(int i = 0; i < chunks.size(); i++){
           if(chunks.get(i).getX()==a[0] + shiftX&&
                   chunks.get(i).getY()==a[1] + shiftY&&
                   chunks.get(i).getZ()==a[2] + shiftZ
            ){
               return i;
           }
        }
        return -1;
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
