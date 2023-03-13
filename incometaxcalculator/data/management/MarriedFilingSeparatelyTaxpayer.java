package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {

  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    
    double[] incomeCategory = {0, 18040, 71680, 90000, 127120};
    double[] taxRatio = {0.0535, 0.0705, 0.0785, 0.0785, 0.0985};
    double[] initialTax = {0, 965.14, 4746.76, 6184.88, 9098.80};
    
    this.incomeCategory = incomeCategory;
    this.taxRatio = taxRatio;
    this.initialTax = initialTax;
  }
}