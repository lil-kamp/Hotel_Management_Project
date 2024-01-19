package model.enums;

public enum ReservationStatus {
    WAITING("WAITING"), ACCEPTED("ACCEPTED"), ENDED("ENDED");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
