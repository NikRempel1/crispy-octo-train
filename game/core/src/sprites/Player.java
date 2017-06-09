package sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

import tools.GameActor;

public class Player extends GameActor  {

	public Player(World world) {
		super(BodyDef.BodyType.DynamicBody);
		
		bdef.position.set(32/ MyGdxGame.PPM,100/ MyGdxGame.PPM);
		
		body = world.createBody(bdef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(5/ MyGdxGame.PPM);
		
		fdef.shape = shape;
		body.createFixture(fdef);
	}
	
	public void Jump(){
		body.applyLinearImpulse(new Vector2(0.1f, 4f), body.getWorldCenter(), true);
	}

}
