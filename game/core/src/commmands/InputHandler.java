package commmands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {
	private Command jump, moveRight, moveLeft;
	
	public InputHandler(){
		jump = new JumpCommand();
		moveRight = new MoveRightCommand();
		moveLeft = new MoveLeftCommand();
	}
	
	public Command HandleInput(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)) return jump;
		if(Gdx.input.isKeyPressed(Input.Keys.D)) return moveRight;
		if(Gdx.input.isKeyPressed(Input.Keys.A)) return moveLeft;
		return null;
		
	}
	
}
