package com.sundaysunday.theredtie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Assets
{
    //FLOORS
    public static TextureRegion floor0 = new TextureRegion(new Texture(Gdx.files.internal("floor0.png")));
    public static TextureRegion floor1 = new TextureRegion(new Texture(Gdx.files.internal("floor1.png")));
    public static TextureRegion floor2at0 = new TextureRegion(new Texture(Gdx.files.internal("floor2at0.png")));
    public static TextureRegion floor2at1 = new TextureRegion(new Texture(Gdx.files.internal("floor2at1.png")));
    public static TextureRegion floor3at0 = new TextureRegion(new Texture(Gdx.files.internal("floor2at1.png")));
    public static TextureRegion floor3at1 = new TextureRegion(new Texture(Gdx.files.internal("floor3at1.png")));
    public static TextureRegion floor4at0 = new TextureRegion(new Texture(Gdx.files.internal("floor4at0.png")));
    public static TextureRegion floor4at1 = new TextureRegion(new Texture(Gdx.files.internal("floor4at1.png")));

    public static TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("background.png")));
    public static TextureRegion backgroundMenu = new TextureRegion(new Texture(Gdx.files.internal("background-menu.png")));
    public static TextureRegion backgroundMenuWithoutLogo = new TextureRegion(new Texture(Gdx.files.internal("background-without-logo.png")));

    public static TextureRegion creditsTextImage = new TextureRegion(new Texture(Gdx.files.internal("team.png")));

    //LADDER
    public static final Texture ladder = new Texture(Gdx.files.internal("ladder.png"));

    //TILESET TEMPORARY
    public static final Texture tileset = new Texture(Gdx.files.internal("tileset.png"));

    //DEMOLITION BALL
    public static TextureRegion demolitionBall = new TextureRegion(new Texture(Gdx.files.internal("demolitionBall.png")));
}