# EasyConfig

EasyConfig is a Java library designed to simplify configuration file handling using JSON files.

## Features

- **Simple Configuration Handling**: EasyConfig provides straightforward methods to manage JSON-based configuration files.
- **Flexible Usage**: Easily set, get, and modify configuration options within your Java application.
- **Customizable Structure**: Allows nesting configurations within parent keys for organized data storage.

## Getting Started

### Installation

To use EasyConfig in your Java project, follow these steps:

1. **Download**: Clone or download the EasyConfig repository from [GitHub](https://github.com/NilsJanosch/EasyConfig).
2. **Include Library**: Add the EasyConfig library to your Java project's build path.

### Usage

1. **Initialize EasyConfig**:
   ```java
   // Example initialization
   Config config = new Config("path/to/config/folder", "config.json");
   ```
2. Set Configuration Values:
   ```java
    // Set a value directly
    config.put("key", "value");
    // Set a value under a parent key
    config.putInParent("parentKey", "key", "value");
   ```
    
3. Retrieve Configuration Values:
   ```java
    // Get a value by key
    Object value = config.get("key");
    // Get a value from under a parent key
    Object parentValue = config.getFromParent("parentKey", "key");
    ```
4. Modify and Save Configuration
    ```java
    // Get a value by key
    Object value = config.get("key");
    // Get a value from under a parent key
    Object parentValue = config.getFromParent("parentKey", "key");
    ```
## Example Config
 ```json
{
  // put() 
  "version": "1.0.0",

  // putInParent()
  "client": {
    "author": "NilsJanosch",
    "admin": true,
    "age": 15
  }
}
```
This JSON structure demonstrates the usage of put() and putInParent() methods in EasyConfig:

put(): Used to directly set a key-value pair in the configuration. Here, the "version" key is set to "1.0.0".

putInParent(): This method is utilized to nest values under a parent key. In this case, within the "client" object, three attributes are added:

"author": Holds the value "NilsJanosch".
"admin": Represents a boolean value, true.
"age": Stores an integer value, 15.
These methods allow easy manipulation and organization of configuration data within the JSON file using the EasyConfig library.
## Contributing
Contributions are welcome! Feel free to raise issues or submit pull requests to help improve EasyConfig.
## License
This project is licensed under the MIT License - see the LICENSE file for details.
