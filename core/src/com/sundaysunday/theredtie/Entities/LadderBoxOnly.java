package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.math.Vector2;

public class LadderBoxOnly extends Entity
{
    public LadderBoxOnly(int x, int y, int width, int height)
    {
        super(new Vector2(x,y), x, y, width, height,"ladder", 0);
    }
}
