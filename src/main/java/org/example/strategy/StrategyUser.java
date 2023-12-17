package org.example.strategy;

public class StrategyUser {
    private final Strategy strategy;

    public StrategyUser(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(){
        System.out.println("Square equal: " + strategy.getSquare());
    }
}
