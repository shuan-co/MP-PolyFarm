package Assets.Utilities;

import java.time.LocalDateTime;

/*
    This class keeps track of key inputs, 
    it will only return true if there is an interval 
    of a certain number of seconds per key input otherwise false.

    This was done in order to avoid overflowing of actions that might cause bugs,
    errors, and performance issues with the number of animations to play and updates to consider.

    Sample Case: user is able to travel across all grids, user was able to travel so fast
    animation that are still playing will cause issues.
    Sample case 2: user travels quickly over tiles over and over again which causes multiple executions
    in quick successions that results in performance issues or lag
 */
// Static Utility Class
public final class Delay {
    // Store current nano seconds of the day in variable
    private static long timeDelay = LocalDateTime.now()
                                                 .toLocalTime()
                                                 .toNanoOfDay();

    /*
        Function to check if there was a certain time interval 
        between the last time the function returned true when a key was pressed.
    */ 
    public static boolean isPaused(){
        // get new current time to be compared with previous timeDelay
        LocalDateTime time = LocalDateTime.now();
        // Check if a certain time interval occured during the previous key press
        if (Math.abs(time.toLocalTime().toNanoOfDay() - timeDelay) >= 300000000){
            // update new time delay to be compared
            timeDelay = time.toLocalTime()
                            .toNanoOfDay();
            return true;
        }
        return false;
    }
}
