package org.example;

import org.example.competition.Competition;
import org.example.competition.athlete.Athlete;
import org.example.competition.obstacle.Obstacle;
import org.example.competition.obstacle.Track;
import org.example.competition.obstacle.Wall;
import org.example.figure.Circle;
import org.example.figure.Rectangle;
import org.example.figure.AbleToCalculateArea;
import org.example.figure.Triangle;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<AbleToCalculateArea> figureList = List.of(new Triangle(3.0,4.0,5.0), new Circle(1), new Rectangle(1.0, 1.0));

        double totalArea = 0;
        for (AbleToCalculateArea figure : figureList){
            totalArea = totalArea + figure.getArea();
        }
        System.out.println("Total area equal: " + totalArea);


        Athlete human = new Athlete("Human", 120, 2);
        Athlete cat = new Athlete("Cat", 250, 1);
        Athlete robot = new Athlete("Robot", 2, 0);
        Athlete superman = new Athlete("Superman", 1000, 1000);
        List<Athlete> athleteList = List.of(human, cat, robot, superman);

        Track track1 = new Track(50);
        Track track2 = new Track(150);
        Wall wall1 = new Wall(1);
        Wall wall2 = new Wall(2);
        List<Obstacle> obstacleList = List.of(track1, wall1, wall2, track2);

        Competition completion = new Competition(obstacleList, athleteList);
        completion.startCompetition();

    }

}