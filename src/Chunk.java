/**
 * Created by arjun on 5/15/15.
 */

public class Chunk {
    private Block[][][] chunk; //3D Array holding Blocks
    //Values determining where the chunk is
    private int x;  //Int determining which chunk it is
    private int y;
    private int z;
    private int count = 0;

    TerrainGen generator = new TerrainGen();

    Chunk(int a, int b, int c) {
        //Initialize Variables
        chunk = new Block[16][16][128];
        x = a;
        y = b;
        z = c;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }

    public void addBlock(Block block) {
        //Adds block to a chunk
        if(block.getX()<16&&block.getX()>=0
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] == null) {
                chunk[block.getX()][block.getY()][block.getZ()] = block;
            }
        }
    }

    public boolean checkBlock(Block block) {
        //Adds block to a chunk
        if(block.getX()<16&&block.getX()>=0
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] != null) {
                return true;
            }
        }
        return false;
    }


    public void highlighBlock(Block block) {
        //highlightBlock
        if(block.getX()<16&&block.getX()>=0
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] != null) {
                chunk[block.getX()][block.getY()][block.getZ()].setRed(chunk[block.getX()][block.getY()][block.getZ()].getRed()+.2f);
                chunk[block.getX()][block.getY()][block.getZ()].setGreen(chunk[block.getX()][block.getY()][block.getZ()].getGreen() + .2f);
                chunk[block.getX()][block.getY()][block.getZ()].setBlue(chunk[block.getX()][block.getY()][block.getZ()].getBlue() + .2f);
                chunk[block.getX()][block.getY()][block.getZ()].setAlpha(chunk[block.getX()][block.getY()][block.getZ()].getAlpha()-1f);
            }
            else{
                addBlock(block);
            }
        }
    }


    public void removeBlock(int x, int y, int z) {
        //removes a block from chunk
        if(x<16&&x>=0 &&y<16&&y>=0 &&z<128&&z>=0) {
            if (chunk[x][y][z] != null) {
                chunk[x][y][z] = null;
            }
        }
    }

    public Block[][][] getChunk() {
        //returns a chunk
        return chunk;
    }

    public boolean isAdjacentBlock(int x, int y, int z) {
        //used in render method.  Checks to see if a block has a gap next to it.
        if (chunk[x][y][z] != null) {
            if (x == 0 || y == 0 || z == 0 || x == 15 || y == 15 || z == 127) {
                return true;
            } else if (chunk[x + 1][y][z] == null ||
                    chunk[x - 1][y][z] == null ||
                    chunk[x][y + 1][z] == null ||
                    chunk[x][y - 1][z] == null ||
                    chunk[x][y][z + 1] == null ||
                    chunk[x][y][z - 1] == null) {
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    //O((16^3) == O(1)  Renders all blocks in chunk that are visisble.
    public void render() {
        for (int i = 0; i < chunk.length; i++) {
            for (int j = 0; j < chunk[0].length; j++) {
                for (int k = 0; k < chunk[0][0].length; k++) {
                    if(isAdjacentBlock(i,j,k)){
                        chunk[i][j][k].render(this.x, this.y, this.z);
                    }
                }
            }
        }
    }

    public void setUp(){
        for(int i=0;i<chunk.length;i++){
            for(int j=0;j<chunk[0].length;j++){
                int wx = 16 * this.getX() + i;
                int wy = 16 * this.getY() + j;
                float height = generator.getHeight(wx,wy);
                for(int h=(int)height;h>=0;h--) {
                    Block temp = new Block(i, j, h, height/32, (32-(2*Math.abs(5.5f-height)))/32, (11-height)/11, 1);
                    this.addBlock(temp);
                }
            }
        }



        /*for(int i = 0; i < chunk.length; i++) {
            for (int j = 0; j < chunk[0].length; j++) {
                if(j==0||j==15 && i==0||i==15) {
                    Block temp = new Block(i, j, 0, 0, 1, 0, 1);
                    this.addBlock(temp);
                }
                else{
                    Block temp = new Block(i, j, 0, 0, 0, 1, 1);
                    this.addBlock(temp);
                }
            }
        }
        */

    }
}
