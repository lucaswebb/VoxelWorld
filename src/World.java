import java.util.ArrayList;

public class World {
    private ArrayList<Chunk> chunks;  //Dynamically changing ArrayList of all Chunks.  Stores values

    //Gives the Camera location for use
    private int camerax = 0;
    private int cameray = 0;
    private int cameraz = 0;

    //Constructer.  Initialize chunks
    World(){
        chunks = new ArrayList<Chunk>();
    }

    //Feeds camera position
    public void setCamera(int[] a){
        camerax = a[0];
        cameray = a[1];
        cameraz = a[2];
    }

    //Checks to see if a block b exists in the world and returns true if it does
    public boolean blockInWorld(Block b){
        int[] temp = getChunkReal(b.getX(), b.getZ(), b.getY());
        for(int i = 0; i < chunks.size(); i++){
            if(chunks.get(i).getX()==temp[0]&&chunks.get(i).getY()==temp[1]&&chunks.get(i).getZ()==temp[2])
            {
            if(b.getX()>=0) {
                b.setX(Math.abs((b.getX()-8000*chunks.get(i).getX())/500));
            }
            else{
                b.setX(Math.abs((b.getX()-8000*chunks.get(i).getX())/500));
            }
            if(b.getY()>=0) {
                b.setY(Math.abs((b.getY()-8000*chunks.get(i).getY())/500));
            }
            else {
                b.setY(Math.abs((b.getY()-8000*chunks.get(i).getY())/500));
            }
            if(b.getZ()>=0) {
                b.setZ(Math.abs(b.getZ())/500);
            } else {
                b.setZ(16-Math.abs(b.getZ())/500);
            }
            return chunks.get(i).checkBlock(b);
            }
        }
        return false;
    }

    //Renders the world.
    public void render(){
        //Check 3x3 around camera
        for(int i = -2; i < 3; i++){
            for(int k = -2; k < 3; k++){
                if(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)>=0){ //if the chunk exists run its render method
                    chunks.get(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)).render();
                } else {
                    chunks.add(new Chunk(getChunkReal(camerax, 0, cameraz)[0]+i //Create a new chunk and runs its set up method
                            ,getChunkReal(camerax, 0, cameraz)[1]+k
                            ,getChunkReal(camerax,0,cameraz)[2]));
                    chunks.get(isInWorld(getChunkReal(camerax,0,cameraz),i,k,0)).setUp();
                }
            }
        }
    }

    //Edits a block b to the world
        //IF a == 0 it adds the block
        //Else if a == 1 it highlights the block
        //Else it removes the block
    public void editBlock(Block b, int a){
        int[] temp = getChunkReal(b.getX(), b.getZ(), b.getY()); //Gets the chunk the block is in
        for(int i = 0; i < chunks.size(); i++){
            if(chunks.get(i).getX()==temp[0]&&chunks.get(i).getY()==temp[1]&&chunks.get(i).getZ()==temp[2]) //If that chunk exists
            {
                //Set the blocks coordinates to chunk coordinates from real coordinates
                if(b.getX()>=0) {
                    b.setX(Math.abs((b.getX()-8000*chunks.get(i).getX())/500));
                }
                else{
                    b.setX(Math.abs((b.getX()-8000*chunks.get(i).getX())/500));
                }
                if(b.getY()>=0) {
                    b.setY(Math.abs((b.getY()-8000*chunks.get(i).getY())/500));
                }
                else {
                    b.setY(Math.abs((b.getY()-8000*chunks.get(i).getY())/500));
                }
                if(b.getZ()>=0) {
                    b.setZ(Math.abs(b.getZ())/500);
                }
                //Perform action on block
                if(a == 0){
                    chunks.get(i).addBlock(b);
                }
                else if (a==1){
                    chunks.get(i).highlightBlock(b);
                }
                else{
                    chunks.get(i).removeBlock(b.getX(),b.getY(),b.getZ());
                }
            }
        }
    }

    //Checks to see if a given chunk is in the world.  Can be shifted to check adjacent chunks
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

    //Returns what chunk the real world coordinate x, y, z are in
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
            ans[2] = y / 64000;
        }
        else{
            ans[2] = y/64000 - 1;
        }
        return ans;
    }
}
