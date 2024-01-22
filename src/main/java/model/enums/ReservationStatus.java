package model.enums;

public enum ReservationStatus {
    WAITING("WAITING"), ACCEPTED_RESERVE("ACCEPTED_RESERVE"),
    ENDED("ENDED"), ACCEPTED_ENDED("ACCEPTED_ENDED"), REJECTED("REJECTED");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
