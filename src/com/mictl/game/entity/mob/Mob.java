package com.mictl.game.entity.mob;

import com.mictl.game.entity.Entity;
import com.mictl.game.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0; // -4 NW, -3 N, -2 NE, -1 W, 0 Origin, 1 E, 2 SW, 3 S, 4 SE
    protected boolean moving = false;

    void move(int xa, int ya) {
        dir = xa + (ya * 3);

        if (!collision()) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public void update() {
    }

    private boolean collision() {
        return false;
    }

    public void render() {
    }

}
