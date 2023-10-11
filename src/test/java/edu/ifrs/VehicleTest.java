package edu.ifrs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.ifrs.business.Load;
import edu.ifrs.business.Vehicle;
import io.restassured.internal.common.assertion.Assertion;

public class VehicleTest {
    
    @Test
    public void testAddWeight (){
        Vehicle vehicle = new Vehicle(100);
        Load load1 = new Load (50);
        Load load2 = new Load (50);
        vehicle.addWeight(load1);
        
    }
}
