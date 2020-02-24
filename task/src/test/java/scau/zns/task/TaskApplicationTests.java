package scau.zns.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scau.zns.task.job.FoodTask;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskApplicationTests {

    @Autowired
    private FoodTask foodTask;

    @Test
    public void setInvalidOrders() {
        foodTask.setInvalidOrders();
    }

    @Test
    public void foodStatistics(){
        foodTask.foodStatistics();
    }

    @Test
    public void orderStatistics(){
        foodTask.orderStatistics();
    }
}
