public class Chunk {
    private Block[][][] chunk; //3D Array holding Blocks
    //Values determining where the chunk is
    private int x;
    private int y;
    private int z;

    TerrainGen generator = new TerrainGen(); //Used to determine

    //Constructor
    Chunk(int a, int b, int c) {
        //Initialize Variables
        chunk = new Block[16][16][128];
        x = a;
        y = b;
        z = c;
    }

    //Get Chunk Location
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
        if(block.getX()<16&&block.getX()>=0 //Check to see if it is in chunk
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] == null) {
                chunk[block.getX()][block.getY()][block.getZ()] = block; //Place in Chunk
            }
        }
    }

    //Checks to see if block exists
    public boolean checkBlock(Block block) {
        if(block.getX()<16&&block.getX()>=0
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] != null) {
                return true;
            }
        }
        return false;
    }

    //Draws a fake highligh block in a chunk
    public void highlightBlock(Block block) {
        //highlightBlock
        if(block.getX()<16&&block.getX()>=0
                &&block.getY()<16&&block.getY()>=0
                &&block.getZ()<128&&block.getZ()>=0) {
            if (chunk[block.getX()][block.getY()][block.getZ()] == null) {
                block.render(this.x,this.y,this.z);
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

    //Checks to see if a block has a gap next to it returns true if there is a space of null
    //On average excludes around 300 blocks from being rendered per chunk
    public boolean isAdjacentBlock(int x, int y, int z) {
        if (chunk[x][y][z] != null) {
            if (x == 0 || y == 0 || z == 0 || x == 15 || y == 15 || z == 127) {
                return true;
            } else if (chunk[x + 1][y][z] == null ||  //Check to see if there is a aspace of null
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

    //Sets ups a chunk and places initial blocks
    public void setUp(){
        for(int i=0;i<chunk.length;i++){
            for(int j=0;j<chunk[i].length;j++){
                //Check location in generator map
                int wx = 16 * this.getX() + j;
                int wy = 16 * this.getY() + i;
                float height = generator.getHeight(wx,wy); //Gets height from generator
                for (int h = (int) height; h >= 0; h--) {
                    Block temp = new Block(j, i, h, height / 32, (32 - (2 * Math.abs(16.5f - height))) / 32, (32 - height) / 32, 1);
                    this.addBlock(temp);
                }
            }
        }
    }
}
