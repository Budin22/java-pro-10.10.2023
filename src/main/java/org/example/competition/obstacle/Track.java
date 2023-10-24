package org.example.competition.obstacle;

import org.example.competition.athlete.Athlete;

public class Track implements Obstacle{
    private final int distance;
    public Track(int distance){
        this.distance = distance;
    }
    @Override
    public boolean overcome(Athlete athlete) {
        boolean athleteIsPass = athlete.run(distance);
        if(athleteIsPass){
            System.out.printf("The %s completed the track with distance %d m\n", athlete.getName(), distance);
        } else {
            System.out.printf("The %s doesn't finish the track  with distance %d m\n", athlete.getName(), distance);
        }

        return athleteIsPass;
    }
}
