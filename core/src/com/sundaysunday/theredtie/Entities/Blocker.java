package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;

public class Blocker extends Entity
{
    public Blocker(int x, int y, int width, int height, String direction)
    {
        super(new Vector2(x,y), x, y, width, height, direction, 0);
    }
}
