package ua.syt0r.actors.entities;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ua.syt0r.GameManager;

public class Player extends Entity {

    public static final int MAX_HEALTH = 3;

    private int health;

    private float minX, maxX, minY, maxY;

    public Player(int bulletType){

        super();
        setTexture(GameManager.assetManager.get("game.atlas", TextureAtlas.class).findRegion("player2"));
        setBody(150,100,5);
        setBounds(150,100,80,80);
        setSpeed(500f);

        setMovementBounds(0,0,0,0);

        setHealth(MAX_HEALTH);

    }

    public void setMovementBounds(float minX, float maxX, float minY, float maxY){

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

    }

    @Override
    public void update(float delta) {

        float newX = getX() + getVelocity().x * getSpeed() * delta;
        if (newX > minX && newX < maxX)
            setX(newX);

        float newY = getY() + getVelocity().y * getSpeed() * delta;
        if (newY > minY && newY < maxY)
            setY(newY);

        getBody().setPosition(getX(),getY());

    }

    @Override
    public void damage() {
        health--;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}