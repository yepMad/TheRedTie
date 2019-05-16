package com.sundaysunday.theredtie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Assets
{
    public static final Texture floor0 = new Texture(Gdx.files.internal("floor0.png"));
    public static final Texture floor1 = new Texture(Gdx.files.internal("floor1.png"));
    public static final Texture floor2at0 = new Texture(Gdx.files.internal("floor2at0.png"));
    public static final Texture floor2at1 = new Texture(Gdx.files.internal("floor2at1.png"));
    public static final Texture floor3at1 = new Texture(Gdx.files.internal("floor3at1.png"));
    public static final Texture tileset = new Texture(Gdx.files.internal("tileset.png"));
        public static final TextureRegion ladder =  new TextureRegion(tileset,0,8,8,8);
}