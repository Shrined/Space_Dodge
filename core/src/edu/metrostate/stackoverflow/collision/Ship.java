package edu.metrostate.stackoverflow.collision;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Ship extends CollidingObject {

    private static final String bullet1 = "bullets/Bullet1.png";

    private long tFiredBullet = 0;

    private final ConcurrentLinkedQueue<CollidingObject> flyingBullets = new ConcurrentLinkedQueue<>();
    private static final String defaultShipSprite = "ships/SpaceShip.png";

    public Ship() {
        super(new Texture(defaultShipSprite), 0, 0);
    }

    public Ship(Texture texture) {
        super(texture, 0, 0);
    }

    public void shoot() {
        if(tFiredBullet == 0) {
            tFiredBullet = System.currentTimeMillis();
            flyingBullets.add(new CollidingObject(new Texture(bullet1), this.getX() + 100, this.getY() + 25));
        }
        else if(System.currentTimeMillis() - tFiredBullet > 1000) {
            flyingBullets.add(new CollidingObject(new Texture(bullet1), this.getX() + 100, this.getY() + 25));
            tFiredBullet = System.currentTimeMillis();
        }

    }

    public ConcurrentLinkedQueue<CollidingObject> getBullets() {
        return flyingBullets;
    }

    @Override
    public void render(SpriteBatch batch) {
        for(CollidingObject bullet: flyingBullets) {
            batch.draw(bullet.getTexture(), bullet.getX(), bullet.getY());
        }
        super.render(batch);
    }
}
