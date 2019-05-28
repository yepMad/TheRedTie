package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;

public class Ladder extends Entity
{
    private static TextureRegion tile = new TextureRegion(Assets.ladder);
    public Ladder(int x, int y, int width, int height)
    {
        super(new Vector2(x,y), x, y, width, height,"ladder", 0);
    }

    @Override
    public void Draw(Batch batch)
    {
        batch.draw(tile, position.x, position.y,0, 0,30,60,1,1,0);
    }
}
