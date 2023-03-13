package incometaxcalculator.data.io;

public class XMLFileReader extends FileReader {

  
  protected int checkValue(String[] values) throws NumberFormatException {
    if (values[0].equals("<ReceiptID>")) {
      int receiptId = Integer.parseInt(values[1].trim());
      return receiptId;
    }
    return -1;
  }
  
  protected String getString(String values[]) {
    String valueReversed[] = new StringBuilder(values[1]).reverse().toString().trim()
        .split(" ", 2);
    return new StringBuilder(valueReversed[1]).reverse().toString();
  }

}
