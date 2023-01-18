import java.io.*;
import java.net.*;

public class JGet {

  public static void main(String[] args) {
    try (BufferedInputStream in = new BufferedInputStream(new URL("http://isis.unice.fr/~mgautero/ext/sae302/bd/ecowatt.db").openStream());
    FileOutputStream fileOutputStream = new FileOutputStream("www/ext/sae302/ecowatt.db")) {
    byte dataBuffer[] = new byte[1024];
    int bytesRead;
    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        fileOutputStream.write(dataBuffer, 0, bytesRead);
      }
    } catch (IOException e) {
      // handle exception
    }

  }
}
