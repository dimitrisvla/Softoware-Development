package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {

  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    
    double[] incomeCategory = {0, 24680, 81080, 90000, 152540};
    double[] taxRatio = {0.0535, 0.0705, 0.0785, 0.0785, 0.0985};
    double[] initialTax = {0, 1320.38, 5296.58, 5996.80, 10906.19};
    
    this.incomeCategory = incomeCategory;
    this.taxRatio = taxRatio;
    this.initialTax = initialTax;
  }

}