package xyz.truthy.dev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Config {

  private static File configFile;
  private static File configPath;
  private static JsonObject configObject;

    public Config(String filePath, String configFile) {
    configPath = new File(filePath);
    configObject = new JsonObject();
    this.configFile = new File(configPath + "\\" + configFile);

    System.out.println("Config Folder: " + this.configPath.getAbsolutePath());
    System.out.println("Config File:   " + this.configFile.getAbsolutePath());

    init();
    loadFromFile();
  }

  private void init() {
    if (!configFile.exists()) {
      try {
        configPath.mkdirs();
        System.out.println("Created Folder at " + configPath.getAbsolutePath());
        configFile.createNewFile();
        System.out.println("Created file at " + configFile.getAbsolutePath());

        putInParent("credits", "config-author", "Truthy_dev");
        putInParent("credits", "config-source", "https://github.com/NilsJanosch/EasyConfig");
        putInParent("credits", "config-version", "1.0.0");
        
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void put(String key, Object value) {
    configObject.add(key, JsonParser.parseString(String.valueOf(value)));
    saveToFile();
  }

  public void putInParent(String parent, String key, Object value) {
    JsonObject parentObj = configObject.getAsJsonObject(parent);
    if (parentObj == null) {
      parentObj = new JsonObject();
    }
    parentObj.add(key, new Gson().toJsonTree(value));
    configObject.add(parent, parentObj);
    saveToFile();
  }

  public Object get(String key) {
    loadFromFile();
    if (configObject.has(key)) {
      return configObject.get(key);
    } else {
      return null;
    }
  }

  public Object getFromParent(String parent, String key) {
    loadFromFile();
    if (configObject.has(parent)) {
      JsonObject parentObj = configObject.getAsJsonObject(parent);
      if (parentObj.has(key)) {
        return parentObj.get(key);
      }
    }
    return null;
  }

  private void loadFromFile() {
    if (!configFile.exists()) {
      init();
      return;
    }

    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    JsonParser jsonParser = new JsonParser();
    configObject = jsonParser.parse(resultStringBuilder.toString()).getAsJsonObject();
  }

  private void saveToFile() {
    if (!configFile.exists()) {
      init();
      return;
    }

    try (FileWriter fileWriter = new FileWriter(configFile)) {
      Gson gson = new Gson();
      String jsonContent = gson.toJson(configObject);
      fileWriter.write(jsonContent);
    } catch (IOException e) {
      throw new RuntimeException("Error saving config to file", e);
    }
  }
}
