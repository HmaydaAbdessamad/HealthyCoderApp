package hmayda.abdessamad;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Assumptions.*;

class BMICalculatorTest {

    private String env="dev";

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before running BMICalculatorTest");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After running BMICalculatorTest");
    }

    @Nested
    class isDietRecommendedisDietRecommended{
        @ParameterizedTest(name = "Weight={0},Height={1}")
        @CsvFileSource(resources = {"/diet-recommended-input-data.csv"},numLinesToSkip = 1)
        void shouldReturnTrueWhenDietIsRecommended(double coderWeight,double coderHeight) {
            //given
            double height=coderHeight;
            double weight=coderWeight;
            //when
            boolean dietRecommended=BMICalculator.isDietRecommended(weight,height);
            //then
            assertTrue(dietRecommended);
        }
        @Test
        void shouldReturnFalseWhenDietIsNotRecommended() {
            //given
            double height=2;
            double weight=80;
            //when
            boolean dietNotRecommended=BMICalculator.isDietRecommended(weight,height);
            //then
            assertFalse(dietNotRecommended);
        }
        @Test
        void raiseArithmitiqueExceptionWhenHeightIZero() {
            //given
            double height=0;
            double weight= (new Random().nextDouble())*100;
            //when
            Executable executable=()->BMICalculator.isDietRecommended(weight,height);
            //then
            assertThrows(ArithmeticException.class,executable);
        }
    }



    @Nested
    class findCoderWithWorstBMI{
        @Test
        void findCoderWithWorstBMIWhenListNotEmpty() {
            //given
            List<Coder> coders=new ArrayList<>();
            coders.add(new Coder(1.80,65));
            coders.add(new Coder(1.82,98));
            coders.add(new Coder(1.82,64.7));

            //when
            Coder coderWithWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertAll(
                    ()->assertEquals(1.82,coderWithWorstBMI.getHeight()),
                    ()->assertEquals(98,coderWithWorstBMI.getWeight())
            );
        }

        @Test
        void findCoder_WithWorstBMI_in1Ms_WhenListHas_1kElements() {
            assumeTrue(BMICalculatorTest.this.env.equals("prod"));
            //given
            List<Coder> coders=new ArrayList<>();
            for(int i=0;i<1000;i++){
                coders.add(new Coder(1+i,10+i));
            }

            //when
            Executable executable=()->BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(400),executable);
        }
        @Test
        void findCoderWithWorstBMIWhenLisEmpty() {
            //given
            List<Coder> coders=new ArrayList<>();

            //when
            Coder coderWithWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertNull(coderWithWorstBMI);
        }
        @RepeatedTest(value=5,name = RepeatedTest.LONG_DISPLAY_NAME)
        void findCoderWithWorstBMIWhenLisContainOneCoder() {
            //given
            List<Coder> coders=new ArrayList<>();
            coders.add(new Coder(1.82,65));
            //when
            Coder coderWithWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertAll(
                    ()->assertEquals(1.82,coderWithWorstBMI.getHeight()),
                    ()->assertEquals(65,coderWithWorstBMI.getWeight())
            );
        }
    }

    @Nested
    class getBMIScores{
        @Test
        void getBMIScoresWhenListOfCoderIsNotEmpty() {
            //given
            //list of coders
            List<Coder> coders=new ArrayList<>();
            coders.add(new Coder(1.80,60));
            coders.add(new Coder(1.82,98));
            coders.add(new Coder(1.82,64.7));
            //expected BMI scores
            double[] expectedBMIScores={18.52,29.59,19.53};
            //when
            double[] BMIScores = BMICalculator.getBMIScores(coders);
            //then
            assertArrayEquals(expectedBMIScores,BMIScores);


        }
    }



}