package hmayda.abdessamad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void shouldReturnTrueWhenDietIsRecommended() {
        //given
        double height=1.6;
        double weight=90;
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
}