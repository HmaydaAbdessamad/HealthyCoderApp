package hmayda.abdessamad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UserActivityTest {

    @Nested
    class RateTesting{
        private UserActivity userActivity;
        @BeforeEach
        void initialization(){
            this.userActivity=new UserActivity();
        }
        @Test
        void should_ReturnBad_When_AvgBelow20() {
            //given
            int cardio=40;
            int numSession=1;
            String expected="bad";
            //when
            String actual=this.userActivity.rate(cardio,numSession);
            //then
            assertEquals(expected,actual);
        }

        @Test
        void should_ReturnAverage_When_AvgBetween20And40() {

            //given
            int cardio=50;
            int numSession=4;
            String expected="average";
            //when
            String actual=this.userActivity.rate(cardio,numSession);
            //then
            assertEquals(expected,actual);
        }
        @Test
        void should_ReturnGood_When_AvgAbove40() {
            //given
            int cardio=60;
            int numSession=7;
            String expected="good";
            //when
            String actual=this.userActivity.rate(cardio,numSession);
            //then
            assertEquals(expected,actual);
        }
        @ParameterizedTest(name = "cardio={0},session={1}")
        @CsvSource(value = {"-30,4","40,-5"})
        void should_ThrowException_When_InputBelowZero(int cardio,int numbSession) {
            //given
            int cardioMin=cardio;
            int sessionNum=numbSession;
            //when
            Executable executable=()->this.userActivity.rate(cardioMin,sessionNum);
            //then
            assertThrows(RuntimeException.class,executable);
        }


    }

}