package br.edu.utfpr.tsi.pong;

import br.edu.utfpr.tsi.pong.obj.SimpleBar;
import jgame.JGColor;

public interface Player {
	public static final int INITIAL_X_1 = SimpleBar.WIDTH/2;
	public static final int INITIAL_X_2 = Const.SCREEN_WIDTH - SimpleBar.WIDTH/2;
	public static final JGColor COLOR_1 = JGColor.cyan;
	public static final JGColor COLOR_2 = JGColor.yellow; 
}
