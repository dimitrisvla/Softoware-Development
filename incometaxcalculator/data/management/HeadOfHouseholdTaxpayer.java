package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {

  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    
    double[] incomeCategory = {0, 30390, 90000, 122110, 203390};
    double[] taxRatio = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    double[] initialTax = {0, 1625.87, 5828.38, 8092.13, 14472.61};
    
    this.incomeCategory = incomeCategory;
    this.taxRatio = taxRatio;
    this.initialTax = initialTax;
  }

}
