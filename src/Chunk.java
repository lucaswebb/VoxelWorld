/**
 * Created by arjun on 5/15/15.
 */

public class Chunk {
    private Block[][][] chunk;
    int chunckLocation;

    Chunk(int n){
        chunk = new Block[16][16][16];
        chunckLocation = n;
    }

    public void addBlock(Block block){
        if(chunk[block.getX()][block.getY()][block.getZ()]==null) {
            chunk[block.getX()][block.getY()][block.getZ()] = block;
        }
    }

    public void removeBlock(int x, int y, int z) {
        if (chunk[x][y][z] != null) {
            chunk[x][y][z] = null;
        }
    }

    public Block[][][] getChunk(){
        return chunk;
    }

    public void render() {
        for (int i = 0; i < chunk.length; i++) {
            for (int j = 0; j < chunk[0].length; j++) {
                for (int k = 0; k < chunk[0][0].length; k++) {


                }
            }
        }
    }
}
