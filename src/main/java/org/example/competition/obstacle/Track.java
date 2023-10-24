package org.example.competition.obstacle;

import org.example.competition.athlete.Athlete;

public class Trace implements Obstacle{
    private final int distance;
    public Trace(int distance){
        this.distance = distance;
    }
    @Override
    public void overcome(Athlete athlete) {
        String result = athlete.running(distance) ? "Athlete pass trace" : "Athlete don't pass trace";
        System.out.println(result);
    }
}
