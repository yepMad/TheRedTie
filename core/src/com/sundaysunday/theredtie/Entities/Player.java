package com.sundaysunday.theredtie.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
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
    private float elapsed = 0;

    private boolean flip;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> climbingAnimation;
    private TextureRegion jumpingAnimation = new TextureRegion(Assets.tileset,24,0,16,16);
    private TextureRegion standing = new TextureRegion(Assets.tileset,8,0,16,16);
    private boolean running = false;

    public Player(float x, float y)
    {
        super(new Vector2(0,30), new Circle(0,30,5), "player");
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
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);
        elapsed += deltaTime;

        if(velocityX > 0)flip = true;
        if(velocityX < 0)flip = false;

        velocityY -= gravity;

        if(velocityY > maxYVel) velocityY = maxYVel;
        if(velocityY < -maxYVel) velocityY = -maxYVel;
        if(running) position.y += 0.1;

        int ySign = 0;
        int xSign = 0;

        if(state == PlayerEnum.idle)
        {
            if(Gdx.input.isKeyPressed(Keys.D))
            {
                xSign++;
                running = true;
            }

            if(Gdx.input.isKeyPressed(Keys.A))
            {
                xSign--;
                running = true;
            }

            if(Gdx.input.isKeyPressed(Keys.SPACE))
            {
                jump();
            }

            if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A))
            {
                running = false;
            }

            velocityX = m_xVelocity * xSign;

            if(bCollideWith("ladder", this) != null)
            {
                if(Gdx.input.isKeyJustPressed(Keys.W))
                {
                    state = PlayerEnum.climbing;
                }
            }
        }

        if(state == PlayerEnum.climbing)
        {
            if((bCollideWith("ladder", this)) != null)
            {
                ySign = moveY();
                xSign = moveX();
            }
            else
            {
                state = PlayerEnum.idle;
            }

            velocityY = m_xVelocity * ySign;
            velocityX = m_xVelocity/3 * xSign;
        }

        if(state == PlayerEnum.jumping)
        {
            xSign = moveX();

            velocityX = m_xVelocity * xSign;
        }

        ArrayList<Entity> listWall = world.getEntitiesByTag("solid");
        for(Entity wall : listWall)
        {
            if(tbCollideWith(wall, this, velocityY, deltaTime))
            {
                while(bCollideWith("solid", this) == null)
                {
                    position.y += Math.signum(velocityY);
                }

                state = PlayerEnum.idle;
                velocityY = 0;
            }
        }

        //Horizontal collision
        for(Entity wall : listWall)
        {
            if(tbCollideWith(wall, this, velocityY, deltaTime))
            {
                if(position.x + circleHitbox.x + (circleHitbox.radius/2) > wall.getPositionX() && bCollideWith("solid", this) == null)
                {
                    position.y++;
                }
            }
        }

        //Impede que o player saia da tela, são as bordas da tela / Prevents the player from leave the screen, are the edges of the screen
        if(position.x + circleHitbox.x < 0) position.x = -circleHitbox.x;
        if(position.x + circleHitbox.x + circleHitbox.radius > TheRedTie.WIDTH) position.x = TheRedTie.WIDTH - circleHitbox.radius - circleHitbox.x;
    }


    private int moveX()
    {
        if(Gdx.input.isKeyPressed(Keys.D))
        {
            return 1;
        }
        if(Gdx.input.isKeyPressed(Keys.A))
        {
            return -1;
        }
        return 0;
    }

    private int moveY()
    {
        if(Gdx.input.isKeyPressed(Keys.W))
        {
            return 1;
        }
        if(Gdx.input.isKeyPressed(Keys.S))
        {
            return -1;
        }
        return 0;
    }

    @Override
    public void draw(Batch batch)
    {
        if(state == PlayerEnum.idle)
        {
            if(velocityX != 0)
            {
                drawPlayer(batch, walkAnimation.getKeyFrame(elapsed,true));
            }
            else
            {
                drawPlayer(batch, standing);
            }
        }

        if(state == PlayerEnum.jumping)
        {
            drawPlayer(batch, jumpingAnimation);
        }

        if(state == PlayerEnum.climbing)
        {
            if(velocityY != 0)
            {
                drawPlayer(batch, climbingAnimation.getKeyFrame(elapsed,true));
            }
            else
            {
                drawPlayer(batch, climbingAnimation.getKeyFrames()[0]);
            }
        }
    }

    private void drawPlayer(Batch batch, TextureRegion graphic)
    {
        batch.draw(graphic,
                position.x-7,
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
            {
                velocityX = 0;
            }
            if(keycode == Keys.A)
            {
                velocityX = 0;
            }
        }
    }

    @Override
    public void keyDown(int keycode)
    {
        if(state == PlayerEnum.idle)
        {
            if(keycode == Keys.D)
            {
                velocityX = m_xVelocity;
            }
            if(keycode == Keys.A)
            {
                velocityX = -m_xVelocity;
            }
            if(keycode == Keys.SPACE)
            {
                jump();
            }
        }

    }
}
