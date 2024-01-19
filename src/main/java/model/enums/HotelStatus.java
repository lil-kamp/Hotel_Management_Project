package model.enums;

public enum HotelStatus {

    CLOSE("CLOSE"), OPEN("OPEN");

    private final String value;

    HotelStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
