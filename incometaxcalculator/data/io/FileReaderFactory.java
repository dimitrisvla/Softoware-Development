package incometaxcalculator.data.io;

public class FileReaderFactory {
  public static FileReader createFileReader(String type) {
    if(type.equals("txt")) {
      return new TXTFileReader();
    }
    else if(type.equals("xml")) {
      return new XMLFileReader();
    }
    return null;
  }
}
