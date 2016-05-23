package br.edu.utfpr.tsi.pong.bot;

import java.util.Arrays;

import br.edu.utfpr.tsi.pong.spi.Ball;
import br.edu.utfpr.tsi.pong.spi.Bot;
import br.edu.utfpr.tsi.pong.spi.Direction;
import br.edu.utfpr.tsi.pong.spi.VerticalBar;

public class BotHard implements Bot {
	private int playerNumber;
	private VerticalBar me;
	private VerticalBar other;
	private Ball ball;
	private double[] x = {160,160};
	private double[] y = {120,120};
	private boolean wait = true;
	private double yGoal = -1;
	private boolean gotThere = false;

	private double playerBarX = 0;
	private double playerNewPointX = -1;
	
	@Override
	public void init(int yourPlayerNumber, Ball ball, VerticalBar player1, VerticalBar player2) {
		this.playerNumber = yourPlayerNumber;
		me = player1;
		other = player2;
		if (playerNumber == 2) {
			playerBarX = 320;
			playerNewPointX = 1;
			me = player2;
			other = player1;
		}
		this.ball = ball;
	}
	
	/**
	 * Calculate where the ball goes
	 */
	@Override
	public Direction calculate() {
		x[0] = x[1];
		y[0] = y[1];
		x[1] = ball.getX();
		y[1] = ball.getY();
		if (playerNumber == 1 && x[1] >= x[0]
				|| playerNumber == 2 && x[1] <= x[0]) {
			wait = true;
			return Math.signum(me.getY() - other.getY()) == 1 ? Direction.UP : Direction.DOWN;
		}
		if (wait) {
			wait = false;
			gotThere = false;
			yGoal = -1;
			return Math.signum(me.getY() - other.getY()) == 1 ? Direction.UP : Direction.DOWN;
		}
		if (gotThere) return Direction.STAY;
		if (yGoal == -1) {
			yGoal = calcPath(x, y);
			double[] tempX = Arrays.copyOf(x, 2);
			double[] tempY = Arrays.copyOf(y, 2);
			while (yGoal < 0 || yGoal > 240) {
				updatePoints(tempX, tempY, yGoal);
				yGoal = calcPath(tempX, tempY);
			}
		}
		if (Math.abs(me.getY() - yGoal) < 1.0) {
			gotThere = true;
		}
		return Math.signum(me.getY() - yGoal) == 1 ? Direction.UP : Direction.DOWN;
	}
	
	private double calcPath(double[] tempX, double[] tempY) {
		final double coeficienteAngular = (tempY[1] - tempY[0])/(tempX[1] - tempX[0]);
		final double coeficienteLinear = tempY[0] - tempX[0]*coeficienteAngular;
		return coeficienteAngular*playerBarX + coeficienteLinear;
	}
	
	private void updatePoints(double[] tempX, double[] tempY, double yDestino) {
		final double yVirada = yDestino < 0 ? 0 : 240;		
		final double coeficienteAngular = (tempY[1] - tempY[0])/(tempX[1] - tempX[0]);
		final double coeficienteLinear = tempY[0] - tempX[0]*coeficienteAngular;
		// y = ax + b
		// (y - b)/a = x
		double xVirada = (yVirada - coeficienteLinear)/coeficienteAngular;		
		tempX[0] = xVirada;
		tempY[0] = yVirada;
		tempX[1] = tempX[0]+playerNewPointX;
		tempY[1] = coeficienteAngular*tempX[1] + coeficienteLinear;
		tempY[1] = tempY[1] < 0 ? -tempY[1] : 240-(tempY[1]-240);
	}
	
}
