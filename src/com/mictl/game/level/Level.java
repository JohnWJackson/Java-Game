package com.mictl.game.level;

import com.mictl.game.graphics.Screen;
import com.mictl.game.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tiles;

    //constructor for generating new levels
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    //constructor for generating fixed levels from file
    public Level(String path) {
        loadLevel(path);
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll - screen.TILE_SIZE)/ screen.TILE_SIZE;
        int x1 = (xScroll + screen.width + screen.TILE_SIZE) / screen.TILE_SIZE;
        int y0 = (yScroll - screen.TILE_SIZE) / screen.TILE_SIZE;
        int y1 = (yScroll + screen.height + screen.TILE_SIZE) / screen.TILE_SIZE;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;

        if (tiles[x + y * width] == 0) return Tile.grass;

        return Tile.voidTile;
    }

// Empty methods are for overrides

    protected void generateLevel() {
    }

    private void loadLevel(String path) {
    }

    public void update() {
    }

    private void time() {
    }
}
