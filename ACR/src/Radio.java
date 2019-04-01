public class Radio {

    private String station;
    private boolean isOn;

    public Radio() {
        this.station = "1234 AM";
        this.isOn = false;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println("Radio was turned on");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println("Radio was turned off");
    }

    public void setStation(String station) {
        this.station = station;
        System.out.println("Radio station was set to " + station);
    }

    public String getStation() {
        return station;
    }

    public void displayRadioStation() {
        System.out.println("The radio station is currently set to " + this.station);
    }
}