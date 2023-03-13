package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  /*private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  */
  protected double[] incomeCategory; // gia na tous allazei to paidi
  protected double[] taxRatio;
  protected double[] initialTax;
  
  public double calculateBasicTax() {
    
    for(int i = 1; i < incomeCategory.length; i++) {
      if(income < incomeCategory[i]) {
        return initialTax[i-1] + taxRatio[i-1]*(income - incomeCategory[i-1]);
      }
    }
    int last = initialTax.length - 1;
    return initialTax[last] + taxRatio[last]*(income - incomeCategory[last]);
    
  }

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    String[] receiptKind = {"Entertainment", "Basic", "Travel", "Health", "Other"};
    int i = 0;
    
    for(i = 0; i < receiptKind.length; i++) {
      if (receipt.getKind().equals(receiptKind[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        receiptHashMap.put(receipt.getId(), receipt);
        totalReceiptsGathered++;
        return;
      } 
    }
    throw new WrongReceiptKindException();
    
    /*
    if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] += receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] += receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] += receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] += receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] += receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }*/
    
    
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    
    String[] receiptKind = {"Entertainment", "Basic", "Travel", "Health", "Other"};
    int i = 0;
    
    for(i = 0; i < receiptKind.length; i++) {
      if (receipt.getKind().equals(receiptKind[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        totalReceiptsGathered--;
        receiptHashMap.remove(receiptId);
        return;
      } 
    }
    throw new WrongReceiptKindException();
    /*if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] -= receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }*/
    
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    double[] incomeRatio = {0.2, 0.4, 0.6};
    double[] basicTaxRatio = {0.08, 0.04, -0.15, -0.3};
    
    for(int i = 0; i < incomeRatio.length; i++) {
      if(totalAmountOfReceipts < incomeRatio[i] * income) {
        return basicTaxRatio[i]*calculateBasicTax();
      }
    }
    
    return basicTaxRatio[basicTaxRatio.length - 1]*calculateBasicTax();
    /*
    if (totalAmountOfReceipts < 0.2 * income) {
      return calculateBasicTax() * 0.08;
    } else if (totalAmountOfReceipts < 0.4 * income) {
      return calculateBasicTax() * 0.04;
    } else if (totalAmountOfReceipts < 0.6 * income) {
      return -calculateBasicTax() * 0.15;//calculateBasicTax() * (-0.15);
    } else {
      return -calculateBasicTax() * 0.3;//calculateBasicTax() *(-0.3);
    }*/
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}