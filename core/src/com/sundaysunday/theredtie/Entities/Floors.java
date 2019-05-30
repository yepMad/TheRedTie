package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;

public class Floors extends Entity
{
    private int m_whatsTheFloor;
    private int m_width;
    private int m_height;
    private int m_rotation;

    private static TextureRegion[] m_textureRegion =
            {
            Assets.floor0,
            Assets.floor1,
            Assets.floor2at0,
            Assets.floor2at1,
            Assets.floor3at0,
            Assets.floor3at1,
            Assets.floor4at0,
            Assets.floor4at1
            };

    public Floors(int positionX, int positionY, int offsetColliderX, int offsetColliderY, int width, int height, int rotation, int whatsTheFloor)
    {
        super(new Vector2(positionX, positionY), (positionX + offsetColliderX), (positionY + offsetColliderY), width, height,"solid", rotation);

        m_whatsTheFloor = whatsTheFloor;
        m_width = width;
        m_height = height;
        m_rotation = rotation;
    }

    @Override
    public void Draw(Batch batch)
    {
        batch.draw(m_textureRegion[m_whatsTheFloor], position.x, position.y,0, 0, m_width, m_height,1,1, m_rotation);
    }
}
