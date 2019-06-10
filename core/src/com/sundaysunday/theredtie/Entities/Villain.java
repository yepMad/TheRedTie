package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;

public class Villain extends Entity
{
    private boolean bFlip = true;

    public Villain(float x, float y)
    {
        super(new Vector2(x,y), new Circle(x,y,10), "villain");
    }

    @Override
    public void Draw(Batch batch)
    {
        batch.draw(Assets.spriteVillain,
                position.x - 7,
                position.y - 5,
                7,
                0,
                52/2,
                74/2,
                bFlip ? 1 : -1,
                1,
                0);
    }
}
