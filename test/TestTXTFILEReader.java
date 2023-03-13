package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;

public class TestTXTFILEReader {
  @Before
  public void setUp() throws Exception {
    TXTFileReader reader = new TXTFileReader();
    reader.readFile("130456093_INFO.txt");
  }

  @Test
  public void testCreateTaxpayer() {
    TaxpayerManager manager = new TaxpayerManager();
    Taxpayer taxPayer = manager.getTaxpayer(130456093);
    assertNotNull(taxPayer);
    
    int num = taxPayer.getReceiptHashMap().size();
    
    assertEquals(num, 5);
    
    double income = taxPayer.getIncome();
    assertEquals(income, 22570.0, 0.01);
  }


}
