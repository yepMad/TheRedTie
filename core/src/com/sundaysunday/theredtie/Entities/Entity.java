package com.sundaysunday.theredtie.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.sundaysunday.theredtie.Worlds.World;
import sun.security.ssl.Debug;

public class Entity
{
    protected Vector2 position;

    protected Rectangle hitbox; //Rectangle Hitbox
    protected Polygon polygon; //Polygon Hitbox
    protected Circle circle; //Circle Hitbox


    protected float velocityX = 0, velocityY = 0;
    protected World world;


    public Color hitboxColor = Color.YELLOW;
    private String nameTag;


    public Entity(Vector2 position, Rectangle hitbox, String nameTag, float rotation)
    {
        this.position = position;
        this.hitbox = hitbox;
        this.nameTag = nameTag;

        //polygon = new Polygon(new float[]{hitbox.x,hitbox.y,hitbox.width,0,hitbox.width,hitbox.height,0,hitbox.height});
        polygon = new Polygon(new float[] {
                hitbox.x, hitbox.y,
                hitbox.x, hitbox.y + hitbox.height,
                hitbox.x + hitbox.width, hitbox.y + hitbox.height,
                hitbox.x + hitbox.width, hitbox.y
        });
        polygon.setOrigin(hitbox.width/2, hitbox.height/2);
        polygon.rotate(rotation);
    }

    public Entity(Vector2 position, Circle circle, String nameTag)
    {
        this.position = position;
        this.circle = circle;
        this.nameTag = nameTag;
    }

    public Entity()
    {
        this.position = new Vector2(0,0) ;
        this.hitbox = new Rectangle(0,0,0,0);
        this.nameTag = "";
    }

    public void drawHitbox(ShapeRenderer renderer)
    {
        renderer.end();
        renderer.setColor(Color.RED);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        //renderer.rect(hitbox.x + position.x, hitbox.y + position.y, hitbox.width, hitbox.height); //Desenha retángulos
        //renderer.setColor(hitboxColor);
        //renderer.polygon(polygon.getTransformedVertices());
        if(nameTag == "player")
        {
            renderer.circle(position.x, position.y, circle.radius);
        }
        renderer.end();

        /*renderer.setColor(hitboxColor);
        renderer.rect(hitbox.x + position.x, hitbox.y + position.y, hitbox.width, hitbox.height);*/
    }

    public void update(float dt)
    {
        position.x += velocityX * dt;
        position.y += velocityY * dt;
    }

    public boolean collideWith(float x, float y, Entity entity)
    {
        Rectangle collideWithWhat = new Rectangle(entity.getHitbox());
        collideWithWhat.x += entity.getX();
        collideWithWhat.y += entity.getY();

        return collideWithWhat.overlaps(new Rectangle(x + hitbox.x, y + hitbox.y, hitbox.width, hitbox.height));
    }

    public boolean collideWith(Entity poly, Player cir) {

        Polygon polygon = poly.getPoly();
        Circle circle = cir.getCircle();

        float []vertices = polygon.getTransformedVertices();
        Vector2 center = new Vector2(cir.getX(), cir.getY());
        float squareRadius=circle.radius*circle.radius;
        for (int i=0;i<vertices.length;i+=2){
            if (i==0){
                if (Intersector.intersectSegmentCircle(new Vector2(vertices[vertices.length-2], vertices[vertices.length-1]), new Vector2(vertices[i], vertices[i+1]), center, squareRadius))
                    return true;
            } else {
                if (Intersector.intersectSegmentCircle(new Vector2(vertices[i-2], vertices[i-1]), new Vector2(vertices[i], vertices[i+1]), center, squareRadius))
                    return true;
            }
        }
        return false;
    }

    public Entity collideWith(float x, float y, String nameTag)
    {
        for(Entity entity : world.getEntitiesByTag(nameTag))
        {
            if(collideWith(x, y, entity))
                return entity;
        }
        return null;
    }

    public void         draw(Batch batch){}

    public void         keyUp(int keycode){}
    public void         keyDown(int keycode){}

    public String       getNameTag(){return nameTag;}

    public Vector2      getPosition(){return position;}
    public void         setPosition(Vector2 position){this.position = position;}

    public float		getX(){return position.x;}
    public void         setVelocityX(float velocityX){this.velocityX = velocityX;}

    public float		getY(){return position.y;}
    public void         setVelocityY(float velocityY){this.velocityY = velocityY;}

    public Rectangle 	getHitbox(){return hitbox;}
    public void			setHitbox(Rectangle hitbox){this.hitbox = hitbox;}

    public Circle      getCircle(){System.out.println(circle); return circle;};

    public Polygon getPoly() {
        return new Polygon(polygon.getTransformedVertices());
    }

    public float        getVelocityX(){return velocityX;}
    public float        getVelocityY(){return velocityY;}

    public void			setWorld(World world){this.world = world;}

    public void			setX(float x){ position.x = x;}
    public void			setY(float y){ position.y = y;}
}
