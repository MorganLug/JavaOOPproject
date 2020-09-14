package fr.Group13.DesignPatternClasses;


public class StatisticVector implements Comparable<StatisticVector> {
	private Object object;
	private double statistic;
	
	public StatisticVector(Object object, double statistic) {
		this.object = object;
		this.statistic = statistic;
	}

	@Override
	public int compareTo(StatisticVector vectorCompared) {
		if (this.statistic > vectorCompared.getStatistic()) {return 1;}
		else if (this.statistic == vectorCompared.getStatistic()) {return 0;}
		else {return -1;}
	}
	
	//getters & setters
	public Object getObject() {
		return object;
	}

	public double getStatistic() {
		return statistic;
	}
	
}
