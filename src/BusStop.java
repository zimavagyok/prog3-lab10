public class BusStop {
    private boolean valid;
    private String name;
    private String oldName;
    private String wheelchair;
    private double distance;

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return valid;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    @Override
    public String toString() {
        return "Megálló:\n" +
                "\tNév: " + name +
                ((oldName != null) ? ("(" + oldName + ")") : "") + "\n" +
                "\tKerekesszék: " + ((wheelchair != null) ? (wheelchair) : "no");
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}