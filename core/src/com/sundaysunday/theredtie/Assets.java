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

    //MENU
    public static TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("background-game.png")));
    public static TextureRegion backgroundMenu = new TextureRegion(new Texture(Gdx.files.internal("background-menu.png")));
    public static TextureRegion backgroundMenuWithoutLogo = new TextureRegion(new Texture(Gdx.files.internal("background-without-logo.png")));
    public static TextureRegion backgroundARROWS = new TextureRegion(new Texture(Gdx.files.internal("ARROWSBackground.png")));
    public static TextureRegion backgroundWASD = new TextureRegion(new Texture(Gdx.files.internal("WASDBackground.png")));
    public static TextureRegion backgroundEnd = new TextureRegion(new Texture(Gdx.files.internal("end.png")));

    public static TextureRegion arrowsNotTrigger = new TextureRegion(new Texture(Gdx.files.internal("arrowsDontTrigger.png")));
    public static TextureRegion wasdNotTrigger = new TextureRegion(new Texture(Gdx.files.internal("wasdDontTrigger.png")));
    public static TextureRegion arrowsTrigger = new TextureRegion(new Texture(Gdx.files.internal("arrowsTrigger.png")));
    public static TextureRegion wasdTrigger = new TextureRegion(new Texture(Gdx.files.internal("wasdTrigger.png")));

    public static TextureRegion startTrigger = new TextureRegion(new Texture(Gdx.files.internal("startTrigger.png")));
    public static TextureRegion startNotTrigger = new TextureRegion(new Texture(Gdx.files.internal("startNotTrigger.png")));

    //CHARACTERS SPRITES
    public static TextureRegion spriteSalsicha = new TextureRegion(new Texture(Gdx.files.internal("salsicha.png")), 22, 74);
    public static TextureRegion spriteVillain = new TextureRegion(new Texture(Gdx.files.internal("vilao.png")), 52, 74);

    //CREDITS SPRITE
    public static TextureRegion spriteCredits = new TextureRegion(new Texture(Gdx.files.internal("team.png")));

    //DEMOLITION BALL
    public static TextureRegion demolitionBall = new TextureRegion(new Texture(Gdx.files.internal("demolitionBall.png")));
}