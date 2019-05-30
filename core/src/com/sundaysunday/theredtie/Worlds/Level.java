package com.sundaysunday.theredtie.Worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sundaysunday.theredtie.Entities.*;

public class Level extends World
{
    private float timerController = 0;
    private float timeToSpawnBall = 5;

    public Level(OrthographicCamera camera)
    {
        super(camera);
        bShouldDrawHitbox = false;

        //FLOOR 0
        Floors floor0 = new Floors(0, 0, 0, 0, 192, 15, 0, 0);
        AddEntity(floor0);

        //FLOOR 1
        Floors floor1 = new Floors(42,60, 3, 2, 152,15, 3, 1);
        AddEntity(floor1);

        //FLOOR 2
        Floors floor2at0 = new Floors(-1,140, -7, -3,120,15, -3, 2);
        AddEntity(floor2at0);
        Floors floor2at1 = new Floors(155,135, 20, -24,52,15, 10,3);
        AddEntity(floor2at1);

        //FLOOR 3
        Floors floor3at0 = new Floors(-3,220, -36, -8,52,15, -10, 4);
        AddEntity(floor3at0);
        Floors floor3at1 = new Floors(80,210, 18, -2, 131, 15, 5,5);
        AddEntity(floor3at1);

        //FLOOR 4
        Floors floor4at0 = new Floors(0,280, 0, 0, 132,15, 0, 6);
        AddEntity(floor4at0);
        Floors floor4at1 = new Floors(165,280, 0,0, 36,15, 0,7);
        AddEntity(floor4at1);

        //FIRST LADDER
        Ladder ladder = new Ladder(7, 17, 10, 60);
        AddEntity(ladder);
        LadderBoxOnly ladderBoxOnly_0 = new LadderBoxOnly(17,17,10,60);
        AddEntity(ladderBoxOnly_0);
        LadderBoxOnly ladderBoxOnly_1 = new LadderBoxOnly(27,17,10,60);
        AddEntity(ladderBoxOnly_1);

        //DEMOLITION BALL
        DemolitionBall demolitionBall = new DemolitionBall(40,300,true);
        AddEntity(demolitionBall);

        //PLAYER
        Player player = new Player(10,30);
        AddEntity(player);
    }

    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);
        timerController += deltaTime;

        if(Gdx.input.isKeyJustPressed(Keys.Q))
        {
            bShouldDrawHitbox = !bShouldDrawHitbox;
        }

        System.out.println(timerController);

        if(timerController >= timeToSpawnBall)
        {
            DemolitionBall demolitionBall = new DemolitionBall(40,300,true);
            AddEntity(demolitionBall);
            timerController = 0;
        }
    }
}