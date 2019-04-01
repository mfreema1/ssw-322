public class AlarmClockRadio {

    private AlarmClock alarmClock;
    private Radio radio;
    private int volume;

    public AlarmClockRadio() {
        this.alarmClock = new AlarmClock();
        this.radio = new Radio();
        this.volume = 5;
    }

    public AlarmClock getAlarmClock() {
        return this.alarmClock;
    }

    public Radio getRadio() {
        return this.radio;
    }

    public void turnVolumeUp() {
        if(volume < 10) {
            System.out.println("Volume was turned up to " + ++volume);
        }
        else {
            System.out.println("Volume is already at 10, cannot turn up anymore");
        }
    }

    public void turnVolumeDown() {
        if(volume > 0) {
            System.out.println("Volume was turned down to " + --volume);
        }
        else {
            System.out.println("Volume is already at 0, cannot turn down anymore");
        }
    }
}