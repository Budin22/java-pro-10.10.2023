package org.example.competition;

import org.example.competition.athlete.Athlete;
import org.example.competition.obstacle.Obstacle;

import java.util.List;

public class Competition {
    private final List<Obstacle> obstacleList;
    private final List<Athlete> athleteList;

    public Competition(List<Obstacle> obstacleList, List<Athlete> athleteList) {
        this.obstacleList = obstacleList;
        this.athleteList = athleteList;
    }

    public void startCompetition(){
        for (Athlete athlete : athleteList) {
            boolean athleteIsPass = athletePassAllObstacles(athlete, obstacleList);
            if(athleteIsPass){
                System.out.printf("The %s passed all obstacles \n", athlete.getName());
            } else {
                System.out.printf("The %s didn't pass all obstacles \n", athlete.getName());
            }
        }
    }

    private boolean athletePassAllObstacles(Athlete athlete, List<Obstacle> obstacles){
        for(Obstacle obstacle: obstacles){
            boolean isPass = obstacle.overcome(athlete);
            if(!isPass) {
                return false;
            }
        }
        return true;
    }
}
