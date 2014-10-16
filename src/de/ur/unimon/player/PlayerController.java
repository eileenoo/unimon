package de.ur.unimon.player;

public final class PlayerController {
	private static Player player;

	private PlayerController() {
	}

	public synchronized static Player getInstance() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public static void reset() {
		player = new Player();
	}

	public static Player setInstance(Player p) {
		player = p;
		return player;
	}

}
