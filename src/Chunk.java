/**
 * Created by arjun on 5/15/15.
 */
public class Chunk {
    private Block[][][] chunk;
    Chunk(){
        chunk = new Block[16][16][16];
    }
    public void addBlock(Block block)
    {
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
}
