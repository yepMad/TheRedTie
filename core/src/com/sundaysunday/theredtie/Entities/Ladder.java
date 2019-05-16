package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;

public class Ladder extends Entity
{
    private TextureRegion graphic = new TextureRegion(Assets.ladder);

    public Ladder(float x, float y)
    {
        super(new Vector2(x,y), new Rectangle(0,0,8,8),"ladder");
    }

    @Override
    public void draw(Batch batch)
    {
        batch.draw(graphic, position.x, position.y, hitbox.width, hitbox.height);
    }
}
