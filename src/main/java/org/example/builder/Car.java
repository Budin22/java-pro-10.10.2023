package org.example.builder;

public class Car {
    private final boolean bulletProofGlass;
    private final boolean winterWheel;
    private final boolean conditioner;
    private final boolean extraInsurance;

    private Car(boolean bulletProofGlass, boolean winterWheel, boolean conditioner, boolean extraInsurance) {
        this.bulletProofGlass = bulletProofGlass;
        this.winterWheel = winterWheel;
        this.conditioner = conditioner;
        this.extraInsurance = extraInsurance;
    }

    public static class Builder {
        private boolean bulletProofGlass;
        private boolean winterWheel;
        private boolean conditioner = true;
        private boolean extraInsurance = true;

        public Builder setBulletProofGlass(boolean isBulletProofGlass) {
            bulletProofGlass = isBulletProofGlass;
            return this;
        }

        public Builder setWinterWheel(boolean isWinterWheel) {
            winterWheel = isWinterWheel;
            return this;
        }

        public Builder setExtraInsurance(boolean isExtraInsurance) {
            extraInsurance = isExtraInsurance;
            return this;
        }

        public Builder setConditioner(boolean isConditioner) {
            conditioner = isConditioner;
            return this;
        }

        public Car build() {
            return new Car(bulletProofGlass, winterWheel, conditioner, extraInsurance);
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
