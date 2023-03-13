package incometaxcalculator.data.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter implements FileWriter {
  private TaxpayerManager manager = new TaxpayerManager();

  protected abstract String  getStartString(String type);
  protected abstract String  getEndString(String type);
  
  public void generateFile(int taxRegistrationNumber) throws IOException {
    
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
    outputStream.println(getStartString("Name") + manager.getTaxpayerName(taxRegistrationNumber) + getEndString("Name"));
    outputStream.println(getStartString("AFM") + taxRegistrationNumber + getEndString("AFM"));
    outputStream.println(getStartString("Status") + manager.getTaxpayerStatus(taxRegistrationNumber) + getEndString("Status"));
    outputStream.println(getStartString("Income") + manager.getTaxpayerIncome(taxRegistrationNumber) + getEndString("Income"));
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println(getStartString("Receipts"));
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {

    HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println(getStartString("ReceiptID") + getReceiptId(receipt) + getEndString("ReceiptID"));
      outputStream.println(getStartString("Date") + getReceiptIssueDate(receipt) + getEndString("Date"));
      outputStream.println(getStartString("Kind") + getReceiptKind(receipt) + getEndString("Kind"));
      outputStream.println(getStartString("Amount") + getReceiptAmount(receipt) + getEndString("Amount"));
      outputStream.println(getStartString("Company") + getCompanyName(receipt) + getEndString("Company"));
      outputStream.println(getStartString("Country") + getCompanyCountry(receipt) + getEndString("Country"));
      outputStream.println(getStartString("City") + getCompanyCity(receipt) + getEndString("City"));
      outputStream.println(getStartString("Street") + getCompanyStreet(receipt) + getEndString("Street"));
      outputStream.println(getStartString("Number") + getCompanyNumber(receipt) + getEndString("Number"));
      outputStream.println();
    }
  }
  
  public void readFile(String fileName, HashMap<String, String> map) {
    try {
      Scanner input = new Scanner(new FileInputStream(fileName));
      while(input.hasNextLine()) {
        String line = input.nextLine();
        System.out.println(line);
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
  
  public int getReceiptId(Receipt receipt) {
    return receipt.getId();
  }

  public String getReceiptIssueDate(Receipt receipt) {
    return receipt.getIssueDate();
  }

  public String getReceiptKind(Receipt receipt) {
    return receipt.getKind();
  }

  public float getReceiptAmount(Receipt receipt) {
    return receipt.getAmount();
  }

  public String getCompanyName(Receipt receipt) {
    return receipt.getCompany().getName();
  }

  public String getCompanyCountry(Receipt receipt) {
    return receipt.getCompany().getCountry();
  }

  public String getCompanyCity(Receipt receipt) {
    return receipt.getCompany().getCity();
  }

  public String getCompanyStreet(Receipt receipt) {
    return receipt.getCompany().getStreet();
  }

  public int getCompanyNumber(Receipt receipt) {
    return receipt.getCompany().getNumber();
  }
}
