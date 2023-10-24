package org.example.competition.athlete;

public class Athlete {
    private final String name;
    private final int distance;
    private final int jump;

    public Athlete(String name, int distance, int jump) {
        this.name = name;
        this.distance = distance;
        this.jump = jump;
    }
    public boolean run(int trackDistance){
        return  distance >= trackDistance;
    }

    public boolean jump(int wallHeight){
        return  jump >= wallHeight;
    }

    public String getName() {
        return name;
    }
}
