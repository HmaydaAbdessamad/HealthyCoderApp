package hmayda.abdessamad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void initialization(){
        this.dietPlanner=new DietPlanner(20,30,50);
    }


    @Test
    void calculateDiet() {
        //given
        Coder coder=new Coder(1.82,75,26,Gender.MALE);
        DietPlan expected=new DietPlan(2202,110,73,275);
        //when
        DietPlan actual=this.dietPlanner.calculateDiet(coder);
        //then
        assertAll(
                ()->assertEquals(expected.getFat(),actual.getFat()),
                ()->assertEquals(expected.getCalories(),actual.getCalories()),
                ()->assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate()),
                ()->assertEquals(expected.getProtein(),actual.getProtein())
        );
    }
}