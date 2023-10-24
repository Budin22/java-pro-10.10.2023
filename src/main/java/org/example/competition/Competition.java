package org.example.competition;

import org.example.competition.athlete.Athlete;
import org.example.competition.obstacle.Obstacle;

import java.util.List;

public class Compitition {
    private final List<Obstacle> obstacleList;
    private final List<Athlete> athleteList;

    public Compitition(List<Obstacle> obstacleList, List<Athlete> athleteList) {
        this.obstacleList = obstacleList;
        this.athleteList = athleteList;
    }

    public void startCompetition(){
        for (int i = 0; i < athleteList.size(); i++) {
            boolean athleteIsPass = true;
            Athlete athlete = athleteList.get(i);
            int obstacleIndex = 0;
            while (athleteIsPass && obstacleList.size() > obstacleIndex){
                Obstacle obstacle = obstacleList.get(obstacleIndex);
                athleteIsPass = obstacle.overcome(athlete);
                obstacleIndex++;
            }

            if(athleteIsPass){
                System.out.printf("The %s passed all obstacles", athlete.getName());
            } else {
                System.out.printf("The %s passed all obstacles", athlete.getName());
            }
        }
    }
}
