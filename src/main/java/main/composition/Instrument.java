package main.composition;

public class Instrument {

    public Instrument(String name) {
        this.name = name;

        switch (name){
            case "Piano":
                this.lowerRange = 50;
                this.upperRange = 80;
                break;
            case "Violin":
                this.lowerRange = 55;
                this.upperRange = 75;
                break;
            case "Cello":
                this.lowerRange = 45;
                this.upperRange = 70;
                break;
            case "Flute":
                this.lowerRange = 60;
                this.upperRange = 90;
                break;
            case "Guitar":
                this.lowerRange = 55;
                this.upperRange = 80;
                break;
            case "Church_organ":
                this.lowerRange = 45;
                this.upperRange = 85;
                break;
            case "Viola":
                this.lowerRange = 50;
                this.upperRange = 75;
                break;
            case "Clarinet":
                this.lowerRange = 65;
                this.upperRange = 95;
                break;
            case "Soprano_sax":
                this.lowerRange = 65;
                this.upperRange = 90;
                break;
            default:
                break;
        }

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
