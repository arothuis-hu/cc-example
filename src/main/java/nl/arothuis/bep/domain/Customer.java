package nl.arothuis.bep.domain;

public class Customer {
    private final int age;
    private final boolean recentlyJoined;
    private final Region region;

    public Customer(boolean recentlyJoined, int age, Region region) {
        this.recentlyJoined = recentlyJoined;
        this.age = age;
        this.region = region;
    }

    public boolean isSenior() {
        return this.age >= 65;
    }

    public boolean hasRecentlyJoined() {
        return this.recentlyJoined;
    }

    public boolean livesInTheNetherlands() {
        return this.region.equals(Region.Netherlands);
    }

    public boolean livesOutsideEurope() {
        return this.region.equals(Region.International);
    }
}
