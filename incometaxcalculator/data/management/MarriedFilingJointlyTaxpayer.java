package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {

  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    
    double[] incomeCategory = {0, 36080, 90000, 143350, 254240};
    double[] taxRatio = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    double[] initialTax = {0, 1930.28, 5731.64, 9492.82, 18197.69};
    
    this.incomeCategory = incomeCategory;
    this.taxRatio = taxRatio;
    this.initialTax = initialTax;
  }

}