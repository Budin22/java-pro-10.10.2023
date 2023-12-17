package org.example.strategy;

public class StrategyUser {
    private final Strategy strategy;

    public StrategyUser(Strategy strategy) {
        if(strategy == null) throw new NullPointerException();
        this.strategy = strategy;
    }

    public void executeStrategy(){
        System.out.println("Square equal: " + strategy.getSquare());
    }
}
