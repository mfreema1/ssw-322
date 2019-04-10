public class Main {

    //just a little driver code to run a sample program
    public static void main(String[] args) {
        AlarmClockRadio alarmClockRadio = new AlarmClockRadio();
        alarmClockRadio.getAlarmClock().getAlarm().setTime(8, 10);
        alarmClockRadio.getAlarmClock().getAlarm().turnAlarmOn();
        alarmClockRadio.getRadio().turnOn();
        alarmClockRadio.getRadio().setStation("1060 AM");
        alarmClockRadio.turnVolumeUp();
        for(int i = 0; i < 60; i++) {
            alarmClockRadio.getAlarmClock().tick();
            if(i == 20 || i == 40)
                alarmClockRadio.getAlarmClock().hitSnooze();
            if(i == 45)
                alarmClockRadio.getAlarmClock().getAlarm().resetAlarm();
        }

        alarmClockRadio.getAlarmClock().getAlarm().turnAlarmOff();
    }
}