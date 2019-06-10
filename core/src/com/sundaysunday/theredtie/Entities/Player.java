package com.sundaysunday.theredtie.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.sundaysunday.theredtie.PlayerEnum;
import com.sundaysunday.theredtie.TheRedTie;
import com.sundaysunday.theredtie.Assets;

public class Player extends Entity
{
    private float m_xVelocity;
    private float m_jumpForce;
    private float m_gravity;
    private float m_maxVelY;
    private float m_elapsed = 0;

    private PlayerEnum m_state;

    private Animation<TextureRegion> m_walkAnimation;
    private Animation<TextureRegion> m_climbingAnimation;

    private TextureRegion textRegion_jumpingAnimation = Assets.spriteSalsicha;
    private TextureRegion textRegion_standing = Assets.spriteSalsicha;

    private boolean bFlip;
    private boolean bRunning = false;

    private boolean collideWithBlocker = false;

    public Player(float x, float y)
    {
        super(new Vector2(x,y), new Circle(x,y,5), "player");

        m_state = PlayerEnum.idle;

        m_xVelocity = 35;
        m_jumpForce = 125;
        m_gravity = 10;
        m_maxVelY = 300;

        bFlip = true;

        /*m_walkAnimation = new Animation<TextureRegion>(1/15f,new TextureRegion(Assets.tileset,24,0,16,16),
                new TextureRegion(Assets.tileset,8,0,16,16),
                new TextureRegion(Assets.tileset,40,0,16,16));

        m_climbingAnimation = new Animation<TextureRegion>(1/5f, new TextureRegion(Assets.tileset,56,0,16,16),
                new TextureRegion(Assets.tileset,72,0,16,16));*/

        m_walkAnimation = new Animation<TextureRegion>(1/15f, Assets.spriteSalsicha);

        m_climbingAnimation = new Animation<TextureRegion>(1/15f, Assets.spriteSalsicha);
    }

    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);

        circleHitbox.setX(position.x);
        circleHitbox.setY(position.y);

        m_elapsed += deltaTime;

        Flip();

        velocityY -= m_gravity;

        if(velocityY > m_maxVelY) { velocityY = m_maxVelY; }
        if(velocityY < -m_maxVelY) { velocityY = -m_maxVelY; }
        if(bRunning) { position.y += 0.1; }

        int xSum = 0;
        int ySum = 0;

        if(m_state == PlayerEnum.idle)
        {
            if(Gdx.input.isKeyPressed(playerKeys[3]))
            {
                xSum++;
                bRunning = true;
            }
            if(Gdx.input.isKeyPressed(playerKeys[1]))
            {
                xSum--;
                bRunning = true;
            }
            if(Gdx.input.isKeyPressed(Keys.SPACE))
            {
                Jump();
            }
            if(!Gdx.input.isKeyPressed(playerKeys[3]) && !Gdx.input.isKeyPressed(playerKeys[1]))
            {
                bRunning = false;
            }

            velocityX = m_xVelocity * xSum;

            if(CollideWithNameTagAndEntity("ladder", this) != null)
            {
                if(Gdx.input.isKeyJustPressed(playerKeys[0]))
                {
                    m_state = PlayerEnum.climbing;
                }
            }
        }

        if(m_state == PlayerEnum.climbing)
        {
            if((CollideWithNameTagAndEntity("ladder", this)) != null)
            {
                ySum = MoveY();
                xSum = MoveX();
            }
            else
            {
                m_state = PlayerEnum.idle;
            }

            velocityY = m_xVelocity * ySum;
            velocityX = m_xVelocity/3 * xSum;
        }

        if(m_state == PlayerEnum.jumping)
        {
            xSum = MoveX();

            velocityX = m_xVelocity * xSum;
        }

        ArrayList<Entity> listWall = world.getEntitiesByTag("solid");

        if(CollideWithNameTagAndEntity("blockerRight", this) != null)
        {
            position.x += -0.7;
        }
        else if(CollideWithNameTagAndEntity("blockerLeft", this) != null)
        {
            position.x -= -0.7;
        }

        for(Entity wall : listWall)
        {
            if(CollideBetweenPOLYGONandCIRCLE(wall, this, velocityY, deltaTime))
            {
                while(CollideWithNameTagAndEntity("solid", this) == null)
                {
                    position.y += Math.signum(velocityY);
                }

                m_state = PlayerEnum.idle;
                velocityY = 0;
            }
        }

        for(Entity wall : listWall)
        {
            if(CollideBetweenPOLYGONandCIRCLE(wall, this, velocityY, deltaTime))
            {
                if(position.x + circleHitbox.x + (circleHitbox.radius/2) > wall.getPositionX() && CollideWithNameTagAndEntity("solid", this) == null)
                {
                    position.y++;
                }
            }
        }

        IfPlayerCollideWithBall();
        IfPlayerCollideWithVillain();
        PreventsPlayerExitTheScreen();
    }

    private void IfPlayerCollideWithBall()
    {
        if(CollideWithDemolitionBallTagAndPlayerCircle("demolitionBall", this) != null)
        {
            world.setbResetFlag(true);
        }
    }

    private void IfPlayerCollideWithVillain()
    {
        if(CollideWithDemolitionBallTagAndPlayerCircle("villain", this) != null)
        {
            world.bPlayerWin = true;
        }
    }

    private void PreventsPlayerExitTheScreen()
    {
        if(position.x + circleHitbox.x < 0) { position.x = -circleHitbox.x; }
        if(circleHitbox.x + circleHitbox.radius > TheRedTie.WIDTH) { position.x += TheRedTie.WIDTH - circleHitbox.radius - circleHitbox.x; }
    }

    private void Flip()
    {
        if(velocityX > 0) { bFlip = true; }
        if(velocityX < 0) { bFlip = false; }
    }

    private int MoveX()
    {
        if(Gdx.input.isKeyPressed(playerKeys[3]))
        {
            return 1;
        }
        if(Gdx.input.isKeyPressed(playerKeys[1]))
        {
            return -1;
        }
        return 0;
    }

    private int MoveY()
    {
        if(Gdx.input.isKeyPressed(playerKeys[0]))
        {
            return 1;
        }
        if(Gdx.input.isKeyPressed(playerKeys[2]))
        {
            return -1;
        }
        return 0;
    }

    public void Jump()
    {
        velocityY = m_jumpForce;
        m_state = PlayerEnum.jumping;
    }

    @Override
    public void Draw(Batch batch)
    {
        if(m_state == PlayerEnum.idle)
        {
            if(velocityX != 0)
            {
                DrawPlayer(batch, m_walkAnimation.getKeyFrame(m_elapsed,true));
            }
            else
            {
                DrawPlayer(batch, textRegion_standing);
            }
        }

        if(m_state == PlayerEnum.jumping)
        {
            DrawPlayer(batch, textRegion_jumpingAnimation);
        }

        if(m_state == PlayerEnum.climbing)
        {
            if(velocityY != 0)
            {
                DrawPlayer(batch, m_climbingAnimation.getKeyFrame(m_elapsed,true));
            }
            else
            {
                DrawPlayer(batch, m_climbingAnimation.getKeyFrames()[0]);
            }
        }
    }

    private void DrawPlayer(Batch batch, TextureRegion graphic)
    {
        batch.draw(graphic,
                position.x - 7,
                position.y - 5,
                7,
                0,
                22/2,
                74/2,
                bFlip ? 1 : -1,
                1,
                0);
    }

    @Override
    public void KeyUp(int keycode)
    {
        if(m_state == PlayerEnum.idle)
        {
            if(keycode == playerKeys[3])
            {
                velocityX = 0;
            }
            if(keycode == playerKeys[1])
            {
                velocityX = 0;
            }
        }
    }

    @Override
    public void KeyDown(int keycode)
    {
        if(m_state == PlayerEnum.idle)
        {
            if(keycode == playerKeys[3])
            {
                velocityX = m_xVelocity;
            }
            if(keycode ==playerKeys[1])
            {
                velocityX = -m_xVelocity;
            }
            if(keycode == Keys.SPACE)
            {
                Jump();
            }
        }

    }
}
