package Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DataManagement {
    private HealthHive healthHive;


    public DataManagement(HealthHive healthHive){
        this.healthHive = healthHive;
    }

    public void getData() throws IOException {

      File file = new File("data/HealthHive.data");
      Scanner scan = new Scanner(file, "UTF-8");
  
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
      String filePath = "data/HealthHive.data";
  
      try (FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
        writer.write(content);
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}
