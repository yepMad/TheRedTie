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
    private ShapeRenderer m_shapeRenderer;
    public boolean bShouldDrawHitbox = true;
    public boolean resetFlag = false;

    private ArrayList<Entity> listAddedEntities = new ArrayList<Entity>();

    public World(OrthographicCamera camera)
    {
        this.camera = camera;
        m_shapeRenderer = new ShapeRenderer();
        m_shapeRenderer.setAutoShapeType(true);
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

    public void Draw(Batch batch)
    {
        batch.begin();
        for(Entity entity : listEntities)
        {
            if(entity != null)
            {
                entity.Draw(batch);
            }
        }
        batch.end();
    }

    public void DrawHitbox()
    {
        if(bShouldDrawHitbox)
        {
            m_shapeRenderer.setProjectionMatrix(camera.combined);
            m_shapeRenderer.begin(ShapeType.Line);
            for(Entity entity : listEntities)
            {
                if(entity != null)
                {
                    entity.DrawHitbox(m_shapeRenderer);
                }
            }
            m_shapeRenderer.end();
        }
    }

    public Entity AddEntity(Entity entity)
    {
        entity.setWorld(this);
        listAddedEntities.add(entity);
        return entity;
    }

    public Entity RemoveEntity(Entity m_Entity)
    {
        listEntities.remove(m_Entity);
        return m_Entity;
    }

    public void UpdateList()
    {
        for(Entity entity : listAddedEntities)
        {
            listEntities.add(entity);
        }
        listAddedEntities.clear();
    }

    public ArrayList<Entity> getEntitiesByTag(String nameTag)
    {
        ArrayList<Entity> listEntity = new ArrayList<Entity>();

        for(Entity entity : listEntities)
        {
            if(entity.getM_nameTag() == nameTag)
                listEntity.add(entity);
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
                m_Entity.KeyDown(iKeycode);
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int iKeycode)
    {
        for(Entity entity : listEntities)
        {
            if(entity != null)
                entity.KeyUp(iKeycode);
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
