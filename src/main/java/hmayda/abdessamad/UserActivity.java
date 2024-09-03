package hmayda.abdessamad;

public class UserActivity {

    private static final int WORKOUT_DURATION_MIN=45;

    /**
     *
     * @param cardio weekly time [min] spent doing cardio
     * @param numberOfSession number of workout session
     * @return based on the daily average we return a string presenting the state
     */
    String rate(int cardio,int numberOfSession){
        if(cardio<0 || numberOfSession<0){
            throw new RuntimeException("Input Below 0");
        }
        int totalMinutes=cardio+(numberOfSession*WORKOUT_DURATION_MIN);
        double avg=totalMinutes/7.0;
        String result;
        if(avg<20){
            result="bad";
        } else if (avg>20 && avg<40) {
            result="average";
        }else {
            result="good";
        }
        return result;
    }
}
