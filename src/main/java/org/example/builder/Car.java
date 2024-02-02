package org.example.builder;

public class Car {
    private boolean bulletProofGlass;
    private boolean winterWheel;
    private boolean conditioner;
    private boolean extraInsurance;

    private Car() {}

    public static class Builder {
        private final Car car = new Car();
        private boolean bulletProofGlass;
        private boolean winterWheel;
        private boolean conditioner = true;
        private boolean extraInsurance = true;

        public Builder setBulletProofGlass(boolean isBulletProofGlass) {
            car.bulletProofGlass = isBulletProofGlass;
            return this;
        }

        public Builder setWinterWheel(boolean isWinterWheel) {
            car.winterWheel = isWinterWheel;
            return this;
        }

        public Builder setExtraInsurance(boolean isExtraInsurance) {
            car.extraInsurance = isExtraInsurance;
            return this;
        }

        public Builder setConditioner(boolean isConditioner) {
            car.conditioner = isConditioner;
            return this;
        }

        public Car build() {
            return car;
        }

    }

    public boolean isBulletProofGlass() {
        return bulletProofGlass;
    }

    public boolean isWinterWheel() {
        return winterWheel;
    }

    public boolean isConditioner() {
        return conditioner;
    }

    public boolean isExtraInsurance() {
        return extraInsurance;
    }
}
