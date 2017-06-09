package commmands;

import tools.GameActor;

public class MoveLeftCommand implements Command{

	@Override
	public void Execute(GameActor actor) {
		actor.MoveLeft();
		
	}

}
