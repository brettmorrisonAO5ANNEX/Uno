package Model;

//represents an uno card
public class Card {
    private final String name;
    private final String color;
    private final int value;
    private final int additiveValue;

    public Card(String name, String color, int value, int additiveValue) {
        this.name = name;
        this.color = color;
        this.value = value;
        this.additiveValue = additiveValue;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public int getAdditiveValue() {
        return additiveValue;
    }
}
