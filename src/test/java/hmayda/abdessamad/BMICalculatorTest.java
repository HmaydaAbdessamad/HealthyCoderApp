package hmayda.abdessamad;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before running BMICalculatorTest");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After running BMICalculatorTest");
    }
    @ParameterizedTest
    @ValueSource(doubles = {89.0,95.0,110.0})
    void shouldReturnTrueWhenDietIsRecommended(double coderWeight) {
        //given
        double height=1.72;
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
    void findCoderWithWorstBMIWhenLisEmpty() {
        //given
        List<Coder> coders=new ArrayList<>();

        //when
        Coder coderWithWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

        //then
       assertNull(coderWithWorstBMI);
    }
    @Test
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