package incometaxcalculator.data.io;

public class FileWriterFactory {
  
  public static FileWriter createFileWriter(String type) {
    if(type.equals("xmlInfo")) {
      return new XMLInfoWriter();
    }
    else if(type.equals("txtInfo")) {
      return new TXTInfoWriter();
    }
    else if(type.equals("xml")) {
      return new XMLLogWriter();
    }
    else if(type.equals("txt")) {
      return new TXTLogWriter();
    }
    return null;
  }
}
