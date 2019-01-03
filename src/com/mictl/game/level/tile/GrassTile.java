package com.mictl.game.level.tile;

import com.mictl.game.graphics.Screen;
import com.mictl.game.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        convertToTiles(x, y);
        screen.renderTile(tileX, tileY, this);
    }
}
