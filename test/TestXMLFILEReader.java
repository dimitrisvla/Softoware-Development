package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;

public class TestXMLFILEReader {


  @Before
  public void setUp() throws Exception {
    XMLFileReader reader = new XMLFileReader();
    reader.readFile("130456094_INFO.xml");
  }

  @Test
  public void testCreateTaxpayer() {
    TaxpayerManager manager = new TaxpayerManager();
    Taxpayer taxPayer = manager.getTaxpayer(130456094);
    assertNotNull(taxPayer);
    
    int num = taxPayer.getReceiptHashMap().size();
    
    assertEquals(num, 2);
    
    double income = taxPayer.getIncome();
    assertEquals(income, 40000.0, 0.01);
  }


}
