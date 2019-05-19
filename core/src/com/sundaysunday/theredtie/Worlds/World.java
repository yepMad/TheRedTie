package com.sundaysunday.theredtie.Worlds;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sundaysunday.theredtie.Entities.Entity;

public class World implements InputProcessor
{
    protected ArrayList<Entity> listEntities = new ArrayList<Entity>();
    protected OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public boolean shouldDrawHitbox = true;
    public boolean resetFlag = false;

    private ArrayList<Entity> listAddedEntities = new ArrayList<Entity>();

    public World(OrthographicCamera camera)
    {
        this.camera = camera;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    public void Update(float deltaTime)
    {
        for(Entity m_Entity : listEntities)
        {
            if(m_Entity != null)
            {
                m_Entity.Update(deltaTime);
            }
        }
    }

    public void draw(Batch batch)
    {
        batch.begin();
        for(Entity m_Entity : listEntities)
        {
            if(m_Entity != null)
            {
                m_Entity.draw(batch);
            }
        }
        batch.end();
    }

    public void drawHitbox()
    {
        if(shouldDrawHitbox)
        {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeType.Line);
            for(Entity m_Entity : listEntities)
            {
                if(m_Entity != null)
                    m_Entity.drawHitbox(shapeRenderer);
            }
            shapeRenderer.end();
        }
    }

    public Entity addEntity(Entity m_Entity)
    {
        m_Entity.setWorld(this);
        listAddedEntities.add(m_Entity);
        return m_Entity;
    }

    public Entity removeEntity(Entity m_Entity)
    {
        listEntities.remove(m_Entity);
        return m_Entity;
    }

    public void updateList()
    {
        for(Entity m_Entity : listAddedEntities)
        {
            listEntities.add(m_Entity);
        }
        listAddedEntities.clear();
    }

    public ArrayList<Entity> getEntitiesByTag(String nameTag)
    {
        ArrayList<Entity> listEntity = new ArrayList<Entity>();

        for(Entity m_Entity : listEntities)
        {
            if(m_Entity.getNameTag() == nameTag)
                listEntity.add(m_Entity);
        }

        return listEntity;
    }

    public boolean getResetFlag()
    {
        return resetFlag;
    }

    public void setResetFlag(boolean bReset)
    {
        resetFlag = bReset;
    }

    @Override
    public boolean keyDown(int iKeycode)
    {
        for(Entity m_Entity : listEntities)
        {
            if(m_Entity != null)
            {
                m_Entity.keyDown(iKeycode);
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int iKeycode)
    {
        for(Entity m_Entity : listEntities)
        {
            if(m_Entity != null)
                m_Entity.keyUp(iKeycode);
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character){return false;}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){return false;}

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){return false;}

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){return false;}

    @Override
    public boolean mouseMoved(int screenX, int screenY){return false;}

    @Override
    public boolean scrolled(int amount){return false;}
}
