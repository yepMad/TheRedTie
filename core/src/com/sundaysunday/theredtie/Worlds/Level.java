package com.sundaysunday.theredtie.Worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sundaysunday.theredtie.Entities.*;
import jdk.nashorn.internal.ir.Block;

public class Level extends World
{
    private float timerController = 0;
    private float timeToSpawnBall = 4;

    public Level(OrthographicCamera camera)
    {
        super(camera);
        bShouldDrawHitbox = false;

        //FLOOR 0
        Floors floor0 = new Floors(0, -12, 0, 0, 192, 15, 0, 0);
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
        LadderBoxOnly ladderBoxOnly_00 = new LadderBoxOnly(10,5,8,80);
        AddEntity(ladderBoxOnly_00);
        LadderBoxOnly ladderBoxOnly_0 = new LadderBoxOnly(20,5,8,80);
        AddEntity(ladderBoxOnly_0);
        LadderBoxOnly ladderBoxOnly_1 = new LadderBoxOnly(30,5,8,80);
        AddEntity(ladderBoxOnly_1);

        //SECOND LADDER
        LadderBoxOnly ladderBoxOnly_2 = new LadderBoxOnly(115,80,8,80);
        AddEntity(ladderBoxOnly_2);
        LadderBoxOnly ladderBoxOnly_3 = new LadderBoxOnly(125,80,8,80);
        AddEntity(ladderBoxOnly_3);
        LadderBoxOnly ladderBoxOnly_3other = new LadderBoxOnly(135,80,8,80);
        AddEntity(ladderBoxOnly_3other);

        //THIRTH LADDER
        LadderBoxOnly ladderBoxOnly_4other = new LadderBoxOnly(50,155,8,80);
        AddEntity(ladderBoxOnly_4other);
        LadderBoxOnly ladderBoxOnly_4 = new LadderBoxOnly(60,155,8,80);
        AddEntity(ladderBoxOnly_4);
        LadderBoxOnly ladderBoxOnly_5 = new LadderBoxOnly(70,155,8,80);
        AddEntity(ladderBoxOnly_5);

        //FOURTHLY LADDER
        LadderBoxOnly ladderBoxOnly_6 = new LadderBoxOnly(135,230,8,70);
        AddEntity(ladderBoxOnly_6);
        LadderBoxOnly ladderBoxOnly_7 = new LadderBoxOnly(145,230,8,70);
        AddEntity(ladderBoxOnly_7);

        //DEMOLITION BALL
        DemolitionBall demolitionBall = new DemolitionBall(40,300,true);
        AddEntity(demolitionBall);

        //PLAYER
        Player player = new Player(300,30);
        AddEntity(player);

        Villain villain = new Villain(20,300);
        AddEntity(villain);

        Blocker blocker_0 = new Blocker(42,60,8,14, "blockerRight");
        AddEntity(blocker_0);
        Blocker blocker_2 = new Blocker(155,136,8,14, "blockerRight");
        AddEntity(blocker_2);
        Blocker blocker_4 = new Blocker(80,210,8,13, "blockerRight");
        AddEntity(blocker_4);
        Blocker blocker_5 = new Blocker(165,280,8,13, "blockerRight");
        AddEntity(blocker_5);

        Blocker blocker_1 = new Blocker(112,135,8,13, "blockerLeft");
        AddEntity(blocker_1);
        Blocker blocker_3 = new Blocker(42,212,8,13, "blockerLeft");
        AddEntity(blocker_3);
        Blocker blocker_6 = new Blocker(124,280,8,13, "blockerLeft");
        AddEntity(blocker_6);
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

        if(timerController >= timeToSpawnBall && !bPlayerWin)
        {
            DemolitionBall demolitionBall = new DemolitionBall(40,300,true);
            AddEntity(demolitionBall);
            timerController = 0;
        }
    }
}