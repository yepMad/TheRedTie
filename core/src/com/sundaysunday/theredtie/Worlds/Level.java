package com.sundaysunday.theredtie.Worlds;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.sundaysunday.theredtie.Entities.Jumpman;
//import com.sundaysunday.theredtie.Entities.Kong;
import com.sundaysunday.theredtie.Entities.*;
//import com.sundaysunday.theredtie.Entities.Pauline;


public class Level extends World
{
    //private Jumpman jumpman;
    //private ArrayList<Floor0> solids;

    public Level(OrthographicCamera camera)
    {
        super(camera);
        //solids = new ArrayList<Floor0>();
        shouldDrawHitbox = false;

        //Ladder base
        /*addEntity(new Ladder(80,8));
        addEntity(new Ladder(80,30));
        addEntity(new Ladder(184,13));
        addEntity(new Ladder(184,21));
        addEntity(new Ladder(96,42));
        addEntity(new Ladder(64,72));
        addEntity(new Ladder(32,46));
        addEntity(new Ladder(64,96));
        addEntity(new Ladder(112,75));
        addEntity(new Ladder(184,80));
        addEntity(new Ladder(168,104));
        addEntity(new Ladder(72,110));
        addEntity(new Ladder(32,112));
        addEntity(new Ladder(88,139));
        addEntity(new Ladder(88,160));
        addEntity(new Ladder(184,145));
        addEntity(new Ladder(128,172));
        addEntity(new Ladder(80,172));
        addEntity(new Ladder(64,172));
        addEntity(new Ladder(168,130));

        addEntity(new Ladder(96,50));
        addEntity(new Ladder(96,58));
        addEntity(new Ladder(32,54));
        addEntity(new Ladder(112,83));
        addEntity(new Ladder(112,91));
        addEntity(new Ladder(184,88));
        addEntity(new Ladder(72,118));
        addEntity(new Ladder(72,126));
        addEntity(new Ladder(32,120));
        addEntity(new Ladder(184,153));
        addEntity(new Ladder(128,180));
        addEntity(new Ladder(80,180));
        addEntity(new Ladder(64,180));
        addEntity(new Ladder(128,188));
        addEntity(new Ladder(80,188));
        addEntity(new Ladder(64,188));
        addEntity(new Ladder(80,196));
        addEntity(new Ladder(64,196));
        addEntity(new Ladder(80,204));
        addEntity(new Ladder(64,204));
        addEntity(new Ladder(80,212));
        addEntity(new Ladder(64,212));*/

        //Floor1
        /*for(int i=0;i<14;i++)
        {
            solid = new Solid(i*8,0,8,8);
            solids.add(solid);
            addEntity(solid);
        }
        for(int i=14;i<28;i++)
        {
            solid = new Solid(i*8,Math.round((i-13)/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor2
        for(int i=0;i<26;i++)
        {
            solid = new Solid(i*8,41 - Math.round((i+1)/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor 3
        for(int i=1;i<28;i++)
        {
            solid = new Solid((i*8)+16,61 + Math.round(i/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor4
        for(int i=0;i<26;i++)
        {
            solid = new Solid(i*8,107 - Math.round((i+1)/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor 5
        for(int i=1;i<28;i++)
        {
            solid = new Solid((i*8)+16,128 + Math.round(i/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor6
        for(int i=0;i<18;i++)
        {
            solid = new Solid(i*8,165,8,8);
            solids.add(solid);
            addEntity(solid);
        }
        for(int i=18;i<26;i++)
        {
            solid = new Solid(i*8,165 - Math.round((i-17)/2f),8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //Floor 7
        for(int i=11;i<17;i++)
        {
            solid = new Solid(i*8,193,8,8);
            solids.add(solid);
            addEntity(solid);
        }

        //jumpman = new Jumpman(9,9);
        //addEntity(jumpman);
        //addEntity(new Pauline(88,201));
        //addEntity(new Kong(16,173,jumpman));*/

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
    }

    @Override
    public void Update(float dt)
    {
        super.Update(dt);
        if(Gdx.input.isKeyJustPressed(Keys.Q))
            shouldDrawHitbox = !shouldDrawHitbox;
    }


}