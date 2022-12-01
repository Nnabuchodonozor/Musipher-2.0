package main.composition;

public class Instrument {

    public Instrument(String name, Integer upperRange, Integer lowerRange) {
        this.name = name;
        this.upperRange = upperRange;
        this.lowerRange = lowerRange;
    }

    String name;
    Integer upperRange;
    Integer lowerRange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpperRange() {
        return upperRange;
    }

    public void setUpperRange(Integer upperRange) {
        this.upperRange = upperRange;
    }

    public Integer getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(Integer lowerRange) {
        this.lowerRange = lowerRange;
    }
}
