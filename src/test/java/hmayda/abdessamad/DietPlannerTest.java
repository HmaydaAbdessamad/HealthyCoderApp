package hmayda.abdessamad;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;
    @BeforeAll
    static void beforeAll(){
        System.out.println("Before running DietPlannerTest");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After running DietPlannerTest");
    }

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