package scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Hud implements Disposable{
	public Stage stage;
	public float mainTimer;
	private Viewport viewport;
	
	private Integer worldTimer;
	
	Label countdownLabel;
	Label timeLabel;
	
	public Hud(SpriteBatch batch){
		worldTimer = 300;
		mainTimer = worldTimer;
		
		viewport = new StretchViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));
		
		table.add(timeLabel).expandX().padTop(10);
		table.add(countdownLabel).expandX().padTop(10);
		
		stage.addActor(table);
	}
	
	public void UpdateTime(float delta){
		mainTimer -= delta;
		worldTimer = (int)mainTimer;
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}
}
