package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.Assets;
import com.sundaysunday.theredtie.TheRedTie;

import java.util.ArrayList;

public class Barrel extends Entity
{
    private float m_velocity = 53f;
    private float m_gravity = 10f;
    private float m_maxYVel = 100f;

    private float m_elapsed;
    private Animation<TextureRegion> walkAnimation;

    public Barrel(float x, float y, boolean moveRight)
    {
        super(new Vector2(x,y), new Circle(0,30,5), "barrel");

        if(moveRight) { velocityX = m_velocity; }
        else { velocityX = -m_velocity; }

        m_elapsed = 0;
        walkAnimation = new Animation<TextureRegion>(1/5f, new TextureRegion(Assets.tileset,136,0,16,16),
                new TextureRegion(Assets.tileset,152,0,16,16),
                new TextureRegion(Assets.tileset,168,0,16,16),
                new TextureRegion(Assets.tileset,184,0,16,16));
    }

    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);
        m_elapsed += deltaTime;

        velocityY -= m_gravity;

        if(velocityY > m_maxYVel) velocityY = m_maxYVel;
        if(velocityY < -m_maxYVel) velocityY = -m_maxYVel;

        ArrayList<Entity> listWall = world.getEntitiesByTag("solid");
        for(Entity wall : listWall)
        {
            if(CollideWithInBetweenPolygonAndCircle(wall, this, velocityY, deltaTime))
            {
                while(CollideWithByNameTag("solid", this) == null)
                {
                    position.y += Math.signum(velocityY);
                }

                velocityY = 0;
            }
        }

        if(position.y > 85)
        {
            if(position.x < 32) velocityX = -velocityX;
            if(position.x > (TheRedTie.WIDTH - 25)) velocityX = -velocityX;
        }
        else if(position.y <= 85)
        {
            if(position.x < 10) velocityX = -velocityX;
        }
    }

    @Override
    public void Draw(Batch batch)
    {
        batch.draw(walkAnimation.getKeyFrame(m_elapsed, true),position.x, position.y);
    }

}