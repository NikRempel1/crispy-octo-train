package tools;

import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

public class B2WorldCreator {
	private TiledMap mainMap;
	private Vector<Integer> tileOrder = new Vector<Integer>();
	
	public B2WorldCreator(World world){
		
		GenerateTileOrder();
		
		CreateCollision(world);		
		}
	
	public TiledMap GetTextures(){
		return mainMap;
	}
	
	private void GenerateTileOrder(){
		//test values
		tileOrder.addElement(0);
		tileOrder.addElement(1);
		tileOrder.addElement(1);
		tileOrder.addElement(1);
		
	}
	
	private void CreateCollision(World world){
		TmxMapLoader ml = new TmxMapLoader();
		TiledMap map = new TiledMap();
		
		TiledMapTileLayer mainLayer = new TiledMapTileLayer(8*tileOrder.size(), 8, 32, 32);
		TiledMapTileLayer tempLayer;
		MapLayer collisionLayer;
		MapObjects tempObj;
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		collisionLayer = new MapLayer();
		collisionLayer.setName("collision");
		mainMap = new TiledMap();
		
		for(int i = 0; i < tileOrder.size(); i++){
			//Finds which map to load
			switch (tileOrder.get(i)){
			case 0:
				map = ml.load("start-grass.tmx");
				break;
			case 1:
				map = ml.load("test.tmx");
				break;
			}
			
			tempLayer = (TiledMapTileLayer)map.getLayers().get("background");
			
			for(int x = 0; x < tempLayer.getWidth(); x++){
				for(int y = 0; y < tempLayer.getHeight(); y++){
					if(tempLayer.getCell(x, y) != null){
						mainLayer.setCell(x + 8*(i), y, tempLayer.getCell(x, y));
					}
				}	
			}
			
			tempLayer = (TiledMapTileLayer)map.getLayers().get("ground");
			
			for(int x = 0; x < tempLayer.getWidth(); x++){
				for(int y = 0; y < tempLayer.getHeight(); y++){
					if(tempLayer.getCell(x, y) != null){
						mainLayer.setCell(x + 8*(i), y, tempLayer.getCell(x, y));
					}
				}	
			}
			
			tempObj = map.getLayers().get("collision").getObjects();
			for(Iterator<RectangleMapObject> iter = tempObj.getByType(RectangleMapObject.class).iterator(); iter.hasNext();){
				
				RectangleMapObject object =  iter.next();
				
				object.getRectangle().x += 256 * i;
				collisionLayer.getObjects().add(object);
				
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				
				bdef.type = BodyDef.BodyType.StaticBody;
				bdef.position.set((rect.getX() + rect.getWidth()/2)/ MyGdxGame.PPM, (rect.getY() + rect.getHeight()/2)/ MyGdxGame.PPM);
				
				body = world.createBody(bdef);
				
				shape.setAsBox(rect.getWidth()/2/ MyGdxGame.PPM, rect.getHeight()/2/ MyGdxGame.PPM);
				fdef.shape = shape;
				body.createFixture(fdef);
			}	
			
		}
		
		mainMap.getLayers().add(mainLayer);
		mainMap.getLayers().add(collisionLayer);
		
	}
	
}
