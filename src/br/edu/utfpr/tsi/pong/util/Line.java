package br.edu.utfpr.tsi.pong.util;


public final class Line {
	private final double[] point1;
	private final double[] point2;
	public final double angular;
	public final double linear;

	public Line(double x1, double y1, double x2, double y2) {
		point1 = new double[] {x1, y1};
		point2 = new double[] {x2, y2};
		angular = (point1[1] - point2[1])/(point1[0] - point2[0]);
		linear = point1[1] - point1[0]*angular;
	}
	
	public double[] calculateNewX(double y) {
		final double x = (y - linear)/angular;
		return new double[] {x,y};
	}
	
	public double[] calculateNewY(double x) {
		final double y = angular * x + linear;
		return new double[] {x,y};
	}
	
}
