package tools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class GameActor {
	protected BodyDef bdef;
	protected FixtureDef fdef;
	protected BodyDef.BodyType btype;
	protected Body body;
	
	public GameActor(BodyDef.BodyType btype){
		this.bdef = new BodyDef();
		bdef.type = btype;
		this.fdef = new FixtureDef();
		
	}
	
	public void Jump(){
		body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
	}
	
	public void MoveRight(){
		if(body.getLinearVelocity().x <= 2)
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
	}
	
	public void MoveLeft(){
		if(body.getLinearVelocity().x >= -2)
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);
	}
	
	public Vector2 getPosition(){
		return body.getWorldCenter();
	}
}
