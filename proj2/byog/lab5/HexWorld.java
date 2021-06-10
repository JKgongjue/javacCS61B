package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * 1初始化tile rendering engine，这个应该翻译成块渲染引擎
 * 2生成一个二维TETile[][]数组
 * 3使用引擎显示数组
 *
 *
 *
 *
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private static final long SEED = 242155;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return 每行的宽度（即 有多少个正方体）
     */
    public static int hexRowWidth(int s, int i) {
    /*
         *       这一块最难推的就是这个effectiveI了
         *         几个公式：
         *         $i_max = 2 * s -1$
         *         前半部分： 3 + i * 2 = s + 2 * eff (eff = i)
         *         后半部分： 7 - (i - 3) * 2  = 7 + (3 - i) * 2
         *         $width_max =s + 2 * (s - 1) = 3 * s -2$ // 单行最长个数
         *         当行数超越六边形尺寸时 -> 3 变成 7  此时的 7 就是单行最长个数 将其代入公式
         *         if(i > = s) {
         *              令eff = s - i; （即上式 3 - i）
         *              width = 3*s - 2(这里是width_max) + eff * 2 = 3s -2 + 2s - 2i = s + 4s -2 - 2i = s + 2 * (2s -i - 1)
         *              所以 令 eff = 2s - i -1
         *              则width = s + 2*eff 这样这条公式形式上就与前半部分公式一致
         *              好绕，绕来绕去
         * }
     **/

        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }
    /**
     * Computesrelative x coordinate(坐标) of the leftmost(最左边的) tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxxx
     *   xxxx
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return 每行最左边的x偏移量（假设最下角的点的x坐标为0，计算偏移量）
     */
    public static int hexRowOffset(int s, int i) {
        int eff = i;
        if (i >= s) {
            eff = 2 * s - 1 - i;
        }
        return -eff;
    }
    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }

    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int yi = 0; yi < 2 * s; yi++) {
//          获得每一行坐标的y值
            int thisRowY = p.y + yi;
//          获得每一行开头的x坐标值
            int xRowStartP = p.x + hexRowOffset(s,yi);
//          获取每一行开头的(x,y)坐标值
            Position rowStartP = new Position(xRowStartP,thisRowY);
//          获取每一行的宽度值
            int rowWidth = hexRowWidth(s,yi);
            addRow(world,rowStartP,rowWidth,t);
        }
    }



    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.SAND;
            case 3 -> Tileset.LOCKED_DOOR;
            case 4 -> Tileset.GRASS;
            case 5 -> Tileset.MOUNTAIN;
            default -> Tileset.NOTHING;
        };
    }
    private static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int height) {
        for (int hi = 0; hi < height; hi +=1) {
            int startXi = p.x;
            int startYi = p.y + hi * 2 * s;
            Position startPi = new Position(startXi, startYi);
            addHexagon(world, startPi, s, randomTile());
        }
    }
    private static void drawRandomHexes(TETile[][] world, Position p, int s,int height) {
        int leftXi = p.x;
        int rightXi = p.x;
        int Yi = p.y;
        int H = 0;
        while (leftXi >= 0 && rightXi <= WIDTH && Yi <= HEIGHT) {
            Position leftP = new Position(leftXi,Yi);
            Position rightP = new Position(rightXi,Yi);
            drawRandomVerticalHexes(world,leftP,s,height - H);
            drawRandomVerticalHexes(world,rightP,s,height - H);
            leftXi = leftXi - (2 * s - 1);
            rightXi = rightXi + (2 * s - 1);
            Yi = Yi + s;
            H++;

        }
    }
    private static int  getHeight(int s) {
        return HEIGHT / (2 * s);
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH,HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //初始化这个界面
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(WIDTH/2 - 1, 0);
        drawRandomHexes(world,p,5,getHeight(5));
        ter.renderFrame(world);

    }




}
