package Server;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DataManagement {
    private HealthHive healthHive;


    public DataManagement(HealthHive healthHive){
        this.healthHive = healthHive;
    }

    public void getData() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/Server/HealthHive.data");
        Scanner scan = new Scanner(inputStream, "UTF-8");
  
        // Skip invalid entries
        while (scan.hasNextLine()) {
          String line = scan.nextLine();
          String[] params = line.split(":");
          int dashCounter = 0;
          for (int i = 0; i < params[0].length(); i++) {
            if (params[0].charAt(i) == '-') {
              dashCounter++;
            }
          }
  
          switch (dashCounter) {
            case 0:
              healthHive.addPatient(params[0], params[1], Integer.parseInt(params[2].strip()), params[3]);
              break;
            case 1:
              healthHive.addItem(params[0].replace("-", ""), Integer.parseInt(params[1].strip()));
              break;
            case 2:
              healthHive.addDoctor(params[0].replace("--", ""), params[1]);
              break;
            default:
              break;
          }
        }
        scan.close();
    }

    public void saveData(String content) {
      String filePath = "app/src/main/java/Server/HealthHive.data";
  
      try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Server/HealthHive.data");
           FileWriter writer = new FileWriter(filePath)) {
  
          InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
          BufferedReader bufferedReader = new BufferedReader(reader);
  
          String line;
          while ((line = bufferedReader.readLine()) != null) {
              writer.write(line);
              writer.write(System.lineSeparator());
          }
  
          writer.write(content);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
