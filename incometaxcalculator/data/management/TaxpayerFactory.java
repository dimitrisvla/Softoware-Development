package incometaxcalculator.data.management;

public class TaxpayerFactory {
  private String fullname;
  private int taxRegistrationNumber;
  private float income;
  
  public TaxpayerFactory(String fullname, int taxRegistrationNumber, float income) {
    super();
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }
  
  public Taxpayer createTaxpayer(String status) {
    if (status.equals("Married Filing Jointly")) {
      return new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Married Filing Separately")) {
      return new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Single")) {
      return new SingleTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Head of Household")) {
      return new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income);
    } 
    return null;
  }
}
