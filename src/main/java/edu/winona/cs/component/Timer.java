
package edu.winona.cs.component;

import edu.winona.cs.log.Log;

public class Timer {
	private static final Log LOG = new Log("Timer");
    
    private double startTime;
    private double endTime;
    private double difference;

    public Timer() {
    }

    public Timer(double startTime, double endTime, double difference) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.difference = difference;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "Start Time: " + startTime + 
                "End Time: " + endTime + 
                "Difference: " + difference;
    }
    
    //TODO method for pausing
    
}
