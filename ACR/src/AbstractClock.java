import java.time.Duration;
import java.time.LocalTime;

/**
 * Not really a truly abstract class -- does not have any abstract methods.  Just leaving it as abstract
 * for right now in case we add onto it later on.
 */
public abstract class AbstractClock {

    private LocalTime localTime;

    protected AbstractClock(){}

    protected void setTime(LocalTime localTime) {
        this.localTime = LocalTime.from(localTime);
    }

    protected void setTime(int hours, int minutes) {
        this.setTime(LocalTime.of(hours, minutes));
    }

    protected void advanceDuration(Duration duration) {
        this.setTime(this.localTime.plus(duration));
    }

    protected LocalTime getTime() {
        return this.localTime;
    }
}
