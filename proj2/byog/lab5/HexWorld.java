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
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private int cofficient(int size,int row) {
        int cofficient = 1;
        if (row > size) {
            return -cofficient;
        }
        return cofficient;
    }
    private int calRowwidth(int size, int row) {
        int width;
        if (row > size) {
            width =
        }
        width = size + row * cofficient(size, row) * 2;


    }
    private int calBlank(int size, int row) {
        if (row == 0) {
            return size - 1;
        }
        return calBlank(size, row-1) - 1;
    }
    public void addHexagon(int size) {

    }
    @Test
    public void test1() {
        addHexagon(5);
    }
}
