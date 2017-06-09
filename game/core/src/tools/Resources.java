package tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Resources extends GameActor {

	public Resources(World world, TiledMap map, Rectangle bounds) {
		super(BodyDef.BodyType.StaticBody);
		// TODO Auto-generated constructor stub
	}

}