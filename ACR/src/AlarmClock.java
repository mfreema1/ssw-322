import java.time.Duration;
import java.time.LocalTime;

public class AlarmClock {

    private Clock clock;
    private Alarm alarm;
    private LocalTime snoozeTime;
    private boolean inSnoozeMode;

    public AlarmClock() {
        this.clock = new Clock(8, 0);
        this.alarm = new Alarm(8, 0);
    }

    public void hitSnooze() {
        System.out.println("Snooze was hit");
        if(this.alarm.isGoingOff()) {
            if(!this.inSnoozeMode) {
                this.snoozeTime = LocalTime.from(this.clock.getTime());
                this.alarm.setGoingOff(false);
                this.inSnoozeMode = true;
            }
            this.snoozeTime = this.snoozeTime.plus(Duration.ofMinutes(9));
        }
    }

    public Alarm getAlarm() {
        return this.alarm;
    }

    public Clock getClock() {
        return this.clock;
    }

    public void tick() {
        this.clock.advanceDuration(Duration.ofMinutes(1));
        System.out.println(this.clock.getTime());
        //make sure the alarm has not been reset
        if(this.alarm.isReset()) {
            this.inSnoozeMode = false;
            this.snoozeTime = this.alarm.getTime();
        }
        //just a standard alarm triggering
        if(this.alarm.isSet() && this.clock.getTime().equals(this.alarm.getTime())) {
            this.alarm.setGoingOff(true);
        }
        //if they're in a snooze
        if(this.alarm.isSet() && this.inSnoozeMode && this.clock.getTime().equals(this.snoozeTime)) {
            this.alarm.setGoingOff(true);
            this.inSnoozeMode = false;
        }
        //give the alarm an opportunity to make a sound if it should
        this.alarm.makeSound();
    }
}