package com.sundaysunday.theredtie.Worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sundaysunday.theredtie.Entities.*;

public class Level extends World
{
    private Player player;

    public Level(OrthographicCamera camera)
    {
        super(camera);
        shouldDrawHitbox = false;

        Floor0 floor0 = new Floor0(0,0,192,15);
        addEntity(floor0);

        Floor1 floor1 = new Floor1(42,60,152,15);
        addEntity(floor1);

        Floor2at0 floor2at0 = new Floor2at0(-1,140,120,15);
        addEntity(floor2at0);

        Floor2at1 floor2at1 = new Floor2at1(155,135,52,15); //145 em X talvez seja melhor.
        addEntity(floor2at1);

        Floor3at0 floor3at0 = new Floor3at0(-3,220,52,15); //145 em X talvez seja melhor.
        addEntity(floor3at0);

        Floor3at1 floor3at1 = new Floor3at1(80,210,131,15); //VERSAO FINAL COLOCAR 264
        addEntity(floor3at1);

        Floor0 floor4 = new Floor0(0,280,192,15);
        addEntity(floor4);

        player = new Player(10,30);
        addEntity(player);
    }

    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);

        if(Gdx.input.isKeyJustPressed(Keys.Q))
        {
            shouldDrawHitbox = !shouldDrawHitbox;
        }
    }
}