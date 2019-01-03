package com.mictl.game.level.tile;

import com.mictl.game.graphics.Screen;
import com.mictl.game.graphics.Sprite;

public class Tile {

    public int x, y;
    public int tileX, tileY;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    // Package-private constructor
    Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    //empty render -- for overrides
    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }

    protected void convertToTiles(int x, int y) {
        tileX = x * 16;
        tileY = y * 16;
    }

}
