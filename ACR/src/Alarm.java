public class Alarm extends AbstractClock{

    private boolean isSet;
    private boolean isGoingOff;
    private boolean isReset;

    public Alarm(int hours, int minutes) {
        this.isSet = false;
        this.isGoingOff = false;
        this.isReset = false;
        this.setTime(hours, minutes);
    }

    public void turnAlarmOn() {
        this.isSet = true;
        System.out.println("Alarm was turned on");
    }

    public void turnAlarmOff() {
        this.isSet = false;
        this.isGoingOff = false;
        System.out.println("Alarm was turned off");
    }

    public void resetAlarm() {
        this.isReset = true;
        this.isGoingOff = false;
        System.out.println("Alarm was reset");
    }

    public boolean isGoingOff() {
        return this.isGoingOff;
    }

    public void setGoingOff(boolean isGoingOff) {
        this.isReset = false;
        this.isGoingOff = isGoingOff;
    }

    public boolean isSet() {
        return this.isSet;
    }

    public boolean isReset() {
        return this.isReset;
    }

    public void makeSound() {
        if(this.isSet && this.isGoingOff) {
            System.out.println("Buzz Buzz Buzz");
        }
    }
}
