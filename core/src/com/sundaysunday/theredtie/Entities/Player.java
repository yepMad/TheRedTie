package com.sundaysunday.theredtie.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.PlayerEnum;
import com.sundaysunday.theredtie.TheRedTie;
import com.sundaysunday.theredtie.Assets;

public class Player extends Entity
{
    private PlayerEnum state;
    private float m_xVelocity;
    private float jumpForce;
    private float gravity;
    private float maxYVel;
    private boolean flip;

    private float elapsed = 0;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> climbingAnimation;
    private TextureRegion jumpingAnimation = new TextureRegion(Assets.tileset,24,0,16,16);
    private TextureRegion standing = new TextureRegion(Assets.tileset,8,0,16,16);

    public Player(float x, float y)
    {
        super(new Vector2(x,y),new Rectangle(4,0,9,11), "player", 0);
        state = PlayerEnum.idle;
        m_xVelocity = 35;
        jumpForce = 900;
        gravity = 10;
        maxYVel = 300;
        flip = true;

        walkAnimation = new Animation<TextureRegion>(1/15f,new TextureRegion(Assets.tileset,24,0,16,16),
                new TextureRegion(Assets.tileset,8,0,16,16),
                new TextureRegion(Assets.tileset,40,0,16,16));

        climbingAnimation = new Animation<TextureRegion>(1/5f, new TextureRegion(Assets.tileset,56,0,16,16),
                new TextureRegion(Assets.tileset,72,0,16,16));
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);
        elapsed += dt;

        if(velocityX > 0)flip = true;
        if(velocityX < 0)flip = false;

        velocityY -= gravity;

        if(velocityY > maxYVel) velocityY = maxYVel;
        if(velocityY < -maxYVel) velocityY = -maxYVel;

        int sign = 0;

        if(state == PlayerEnum.idle)
        {
            if(Gdx.input.isKeyPressed(Keys.D))
                sign++;
            if(Gdx.input.isKeyPressed(Keys.A))
                sign--;
            if(Gdx.input.isKeyPressed(Keys.SPACE))
                jump();
            velocityX = m_xVelocity * sign;

            // Climb the ladder
            if(collideWith(position.x,position.y,"ladder") != null)
            {
                if(Gdx.input.isKeyJustPressed(Keys.UP))
                {
                    state = PlayerEnum.climbing;
                    velocityX=0;
                    velocityX=0;
                }
            }
        }

        if(state == PlayerEnum.climbing)
        {
            sign = 0;
            // Climb the ladder
            if(collideWith(position.x,position.y,"ladder") != null || collideWith(position.x, position.y,"solid") != null)
            {
                if(Gdx.input.isKeyPressed(Keys.UP))
                    sign += 1;
                if(Gdx.input.isKeyPressed(Keys.DOWN))
                    sign -= 1;
            }
            else
            {
                state = PlayerEnum.idle;
            }
            velocityY = m_xVelocity * sign;
        }

        // Vertical collision
        ArrayList<Entity> wallList = world.getEntitiesByTag("solid");
        for(Entity wall : wallList)
        {
            if(collideWith(position.x, position.y+(velocityY*dt),wall))
            {
                if( (wall.getPosition().y + wall.getHitbox().y + wall.getHitbox().height) < (position.y + hitbox.y))
                {
                    while(collideWith(position.x,position.y+Math.signum(velocityY),"solid") == null)
                        position.y += Math.signum(velocityY);

                    state = PlayerEnum.idle;
                    velocityY = 0;
                }
            }
        }

        // Horizontal collision
        for(Entity wall : wallList)
        {
            if(collideWith(position.x+(velocityX*dt), position.y, wall))
                if(position.x + hitbox.x + (hitbox.width/2) > wall.getX() && collideWith(position.x, position.y+1, "solid") == null)
                    position.y++;
        }

        if(collideWith(position.x, position.y, "barrel") != null)
        {
            world.setResetFlag(true);
        }

        if(collideWith(position.x, position.y, "pauline") != null)
        {
            world.setResetFlag(true);
        }

        // Snap to screen
        if(position.x + hitbox.x < 0) position.x = -hitbox.x;
        if(position.x + hitbox.x + hitbox.width > TheRedTie.WIDTH) position.x = TheRedTie.WIDTH - hitbox.width - hitbox.x;

    }

    @Override
    public void draw(Batch batch)
    {
        if(state == PlayerEnum.idle)
        {
            // If he's moving
            if(velocityX != 0)
                drawPlayer(batch, walkAnimation.getKeyFrame(elapsed,true));
            else
                drawPlayer(batch, standing);
        }
        if(state == PlayerEnum.jumping)
        {
            drawPlayer(batch, jumpingAnimation);
        }
        if(state == PlayerEnum.climbing)
        {
            // If he's moving
            if(velocityY != 0)
                drawPlayer(batch, climbingAnimation.getKeyFrame(elapsed,true));
            else
                drawPlayer(batch, climbingAnimation.getKeyFrames()[0]);
        }
    }

    private void drawPlayer(Batch batch, TextureRegion graphic)
    {
        batch.draw(graphic,
                position.x,
                position.y,
                8,
                8,
                16,
                16,
                flip ? -1 : 1,
                1,
                0);
    }

    public void jump()
    {
        velocityY = jumpForce;
        state = PlayerEnum.jumping;
    }

    @Override
    public void keyUp(int keycode)
    {
        if(state == PlayerEnum.idle)
        {
            if(keycode == Keys.D)
                velocityX = 0;
            if(keycode == Keys.A)
                velocityX = 0;
        }
    }

    @Override
    public void keyDown(int keycode)
    {
        if(state == PlayerEnum.idle)
        {
            if(keycode == Keys.D)
                velocityX = m_xVelocity;
            if(keycode == Keys.A)
                velocityX = -m_xVelocity;
            if(keycode == Keys.SPACE)
                jump();
        }

    }
}
