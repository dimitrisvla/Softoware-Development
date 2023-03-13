package incometaxcalculator.data.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class LogWriter implements FileWriter {
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  private TaxpayerManager manager = new TaxpayerManager(); 
  
  protected abstract String  getStartString(String type);
  protected abstract String  getEndString(String type);
  
  
  public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
    outputStream.println(getStartString("Name") + manager.getTaxpayerName(taxRegistrationNumber) + getEndString("Name"));
    outputStream.println(getStartString("AFM") + taxRegistrationNumber + getEndString("AFM"));
    outputStream.println(getStartString("Income") + manager.getTaxpayerIncome(taxRegistrationNumber) + getEndString("Income"));
    outputStream
        .println(getStartString("BasicTax") + manager.getTaxpayerBasicTax(taxRegistrationNumber) + getEndString("BasicTax"));
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println(getStartString("TaxIncrease")
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + getEndString("TaxIncrease"));
    } else {
      outputStream.println(getStartString("TaxDecrease")
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + getEndString("TaxDecrease"));
    }
    outputStream
        .println(getStartString("TotalTax") + manager.getTaxpayerTotalTax(taxRegistrationNumber) + getEndString("TotalTax"));
    outputStream.println(
        getStartString("Receipts") + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + getEndString("Receipts"));
    outputStream.println(
        getStartString("Entertainment") + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
            + getStartString("Entertainment"));
    outputStream.println(
        getStartString("Basic") + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + getEndString("Basic"));
    outputStream.println(
        getStartString("Travel") + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + getEndString("Travel"));
    outputStream.println(
        getStartString("Health") + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + getEndString("Health"));
    outputStream.println(
        getStartString("Other") + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + getEndString("Other"));
    outputStream.close();
  }
  
  public void readFile(String fileName, HashMap<String, String> map) {
    try {
      Scanner input = new Scanner(new FileInputStream(fileName));
      while(input.hasNextLine()) {
        String line = input.nextLine();
        String[] tokens = line.split("\t");
        String key = tokens[0];
        String value = tokens[1];
        map.put(key, value);
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
