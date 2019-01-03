package com.mictl.game.graphics;

import com.mictl.game.level.tile.Tile;

import java.util.Random;

public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int TILE_SIZE = 16;
    public int xOffset, yOffset;
    private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    //constructor
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
        Random random = new Random();

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
            tiles[0] = 0x000000;
        }
    }

    //clear screen
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if ((xa < -tile.sprite.SIZE) || (xa >= width) || (ya < -tile.sprite.SIZE) || (ya >= height)) break;
                if (xa < 0) xa = 0;
                if (ya < 0) ya = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


}
