package model.enums;

public enum ReservationStatus {
    WAITING_BEFORE_RESERVE("WAITING_BEFORE_RESERVE"), ACCEPTED_RESERVE("ACCEPTED_RESERVE"),
    WAITING_ENDED("WAITING_ENDED"),
    ACCEPTED_ENDED("ACCEPTED_ENDED"), REJECTED("REJECTED");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
