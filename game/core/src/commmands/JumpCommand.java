package commmands;

import tools.GameActor;

public class JumpCommand implements Command {

	@Override
	public void Execute(GameActor actor) {
		actor.Jump();
	}
}