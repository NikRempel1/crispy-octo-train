package sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import tools.GameActor;

public class Items extends GameActor  {

	public Items(World world, TiledMap map, Rectangle bounds) {
		super(BodyDef.BodyType.DynamicBody);
		// TODO Auto-generated constructor stub
	}

}
