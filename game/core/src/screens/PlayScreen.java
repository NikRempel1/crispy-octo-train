package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import commmands.Command;
import commmands.InputHandler;
import scenes.Hud;
import sprites.Player;
import tools.B2WorldCreator;

public class PlayScreen implements Screen {
	private MyGdxGame game;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private Player player;
	private InputHandler input;
	private Command command;
	
	public PlayScreen(MyGdxGame game) {
		B2WorldCreator b2wc;
		
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new StretchViewport(MyGdxGame.V_WIDTH / MyGdxGame.PPM, MyGdxGame.V_HEIGHT/ MyGdxGame.PPM, gamecam);
		hud = new Hud(game.batch);
		
		
		
		world = new World(new Vector2(0, -9.8f), true);
		b2dr = new Box2DDebugRenderer();
		
		b2wc = new B2WorldCreator(world);
		
		mapLoader = new TmxMapLoader();
		map = b2wc.GetTextures();
		renderer = new OrthogonalTiledMapRenderer(map, 1 / MyGdxGame.PPM);
		
		gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
		
		player = new Player(world);
		input = new InputHandler();
		
		
	}
	
	public void HandleInput(float delta){
		command = input.HandleInput();
		if(command != null) command.Execute(player);
	}
	
	public void Update(float delta){
		HandleInput(delta);
		
		world.step(1/60f, 6, 2);
		
		gamecam.position.x = player.getPosition().x;
		
		gamecam.update();
		renderer.setView(gamecam);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();

	}

}
