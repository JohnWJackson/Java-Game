package com.mictl.game.entity.mob;

import com.mictl.game.input.InputHandler;

public class Player extends Mob {

    private InputHandler input;

    public Player(InputHandler input) {
        this.input = input;
    }

    public Player(int x, int y, InputHandler input) {
        this.input = input;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) move(xa, ya);
    }

    @Override
    public void render() {
    }

}
