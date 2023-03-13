package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;


public class TestTaxpayerManager {
  private static TaxpayerManager manager;
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  @Before
  public void setUp() throws Exception {
    manager = new TaxpayerManager();
    try {
      manager.createTaxpayer("name", 0, "Single", (float)12000.0);
    } catch (WrongTaxpayerStatusException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateTaxpayer() {
    Taxpayer taxPayer = manager.getTaxpayer(0);
    assertNotNull(taxPayer);
  }

  @Test
  public void testRemoveTaxpayer() {
    manager.removeTaxpayer(0);
    
    boolean flag = manager.containsTaxpayer(0);
    assertFalse(flag);
  }

  @Test
  public void testAddReceipt() {
    try {
      manager.addReceipt(22, "09/11/2022", (float)10, "Basic", "UOI", "Greece", "Ioannina", "Dourouti", 11, 0);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WrongReceiptKindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WrongReceiptDateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ReceiptAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    boolean flag = manager.containsReceipt(22);
    assertTrue(flag);
    
  }

  @Test
  public void testRemoveReceipt() {
    try {
      manager.addReceipt(23, "09/11/2022", (float)10, "Basic", "UOI", "Greece", "Ioannina", "Dourouti", 11, 0);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WrongReceiptKindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WrongReceiptDateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ReceiptAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    

    try {
      manager.removeReceipt(23);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WrongReceiptKindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    boolean flag = manager.containsReceipt(23);
    assertFalse(flag);
  }

  @Test
  public void testContainsTaxpayerInt() {
   
    boolean flag = manager.containsTaxpayer(0);
    assertTrue(flag);
  }

  @Test
  public void testContainsReceipt() {
    boolean flag = manager.containsReceipt(24);
    assertFalse(flag);
    
    flag = manager.containsReceipt(22);
    assertTrue(flag);
  }

  @Test
  public void testViewReport() {
    assertEquals(manager.getTaxpayerBasicTax(0), 642.0, 0.01);
    assertEquals(manager.getTaxpayerVariationTaxOnReceipts(0), 51.36, 0.01);
    assertEquals(manager.getTaxpayerTotalTax(0), 693.36, 0.01);

    assertEquals(manager.getTaxpayerAmountOfReceiptKind(0, ENTERTAINMENT), 0, 0.01);
    assertEquals(manager.getTaxpayerAmountOfReceiptKind(0, BASIC), 0, 0.01);
    assertEquals(manager.getTaxpayerAmountOfReceiptKind(0, TRAVEL),0, 0.01);
    assertEquals(manager.getTaxpayerAmountOfReceiptKind(0, HEALTH),0, 0.01);
    assertEquals(manager.getTaxpayerAmountOfReceiptKind(0, OTHER),0, 0.01);

  }
}
