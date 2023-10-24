package org.example.competition.obstacle;

import org.example.competition.athlete.Athlete;

public class Wall implements Obstacle{
    private final int height;
    public Wall(int height){
        this.height = height;
    }
    @Override
    public boolean overcome(Athlete athlete) {
        boolean athleteIsPass = athlete.jump(height);
        if(athleteIsPass){
            System.out.printf("The %s jumped over the obstacle with height %d \n", athlete.getName(), height);
        } else {
            System.out.printf("The %s didn't jump over the obstacle with height %d \n", athlete.getName(), height);
        }

        return athleteIsPass;
    }
}
