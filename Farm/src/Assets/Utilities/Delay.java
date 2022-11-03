package Assets.Utilities;

import java.time.LocalDateTime;

public final class Delay {
    private long timeDelay;

    public Delay(){
        LocalDateTime time = LocalDateTime.now();
        timeDelay = time.toLocalTime().toNanoOfDay();
    }
    public boolean isPaused(){
        LocalDateTime time = LocalDateTime.now();
        if (Math.abs(time.toLocalTime().toNanoOfDay() - timeDelay) >= 300000000){
            timeDelay = time.toLocalTime().toNanoOfDay();
            return true;
        }
        return false;
    }
}
