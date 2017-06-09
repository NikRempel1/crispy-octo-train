package commmands;

import tools.GameActor;

public class MoveRightCommand implements Command {

	@Override
	public void Execute(GameActor actor) {
		actor.MoveRight();
		
	}

}
