package incometaxcalculator.data.io;

public class TXTFileReader extends FileReader {

  protected int checkValue(String[] values) throws NumberFormatException {
    if (values[0].equals("Receipt")) {
      if (values[1].equals("ID:")) {
        int receiptId = Integer.parseInt(values[2].trim());
        return receiptId;
      }
    }
    return -1;
  }
  
  protected String getString(String values[]) {
    return values[1].trim();
  }

}