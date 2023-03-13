package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.management.SingleTaxpayer;
import incometaxcalculator.data.management.Taxpayer;

public class TestTaxpayer {
  private static Taxpayer taxpayer = null;
  
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCalculateBasicTaxSingle() {
    float[] income = {20000, 50000, 85000, 150000, 200000};
    
    double[] expectedTax = {1070, 3105.44, 5604.3, 10706.8, 15581};
    
    for(int i = 0; i < income.length; i++) {
      taxpayer = new SingleTaxpayer("jim", 0, income[i]);
      assertEquals(expectedTax[i], taxpayer.getBasicTax(), 0.01);
    }
    
    
  }
  
  @Test
  public void testCalculateBasicTaxMarriedFilingSeparately() {
    float[] income = {15000, 50000, 85000, 100000, 200000};
    
    double[] expectedTax = {802.5, 3105.44, 5604.3, 6781.8, 15581};
    
    for(int i = 0; i < income.length; i++) {
      taxpayer = new SingleTaxpayer("jim", 0, income[i]);
      assertEquals(expectedTax[i], taxpayer.getBasicTax(), 0.01);
    }
    
  }
  
  @Test
  public void testCalculateBasicTaxMarriedFilingJointly() {
    float[] income = {15000, 50000, 100000, 150000, 260000};
    
    double[] expectedTax = {802.5, 3105.44, 6781.8, 10706.8, 21491.0};
    
    for(int i = 0; i < income.length; i++) {
      taxpayer = new SingleTaxpayer("jim", 0, income[i]);
      assertEquals(expectedTax[i], taxpayer.getBasicTax(), 0.01);
    }
    
  }
  
  @Test
  public void testCalculateBasicTaxHeadOfHousehold() {
    float[] income = {15000, 50000, 100000, 150000, 260000};
    
    double[] expectedTax = {802.5, 3105.44, 6781.8, 10706.8, 21491.0};
    
    for(int i = 0; i < income.length; i++) {
      taxpayer = new SingleTaxpayer("jim", 0, income[i]);
      assertEquals(expectedTax[i], taxpayer.getBasicTax(), 0.01);
    }
    
  }
/*
  @Test
  public void testAddReceipt() {
    fail("Not yet implemented");
  }

  @Test
  public void testRemoveReceipt() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTotalTax() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBasicTax() {
    fail("Not yet implemented");
  }*/

}
