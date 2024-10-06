import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String directoryPath = (args.length > 0) ? args[0] : System.getProperty("user.dir");
        String configFilePath = System.getProperty("user.home") + "/.config/file_organizer/file-categories.properties";
        createDefaultConfig(configFilePath);
        Map<String, String> fileCategoryMap = loadFileCategoriesFromConfig(configFilePath);
        organizeFilesByCategory(directoryPath, fileCategoryMap);
    }

    private static void createDefaultConfig(String configFilePath) {
        File configFile = new File(configFilePath);

        // If config exists, no need to create default config
        if (configFile.exists()) {
            return;
        }

        try {
            File configDir = configFile.getParentFile();

            // Create config directory if it doesn't exist
            if (!configDir.exists()) {
                if (configDir.mkdirs())
                    System.out.println("Created config directory: " + configDir.getAbsolutePath());
                else {
                    System.err.println("Failed to create config directory: " + configDir.getAbsolutePath());
                    System.exit(2);
                }
            }

            // Create default config file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write("# Default file categories\n");
                writer.write("jpg=Images\n");
                writer.write("jpeg=Images\n");
                writer.write("png=Images\n");
                writer.write("gif=Images\n");
                writer.write("bmp=Images\n");
                writer.write("mp3=Audio\n");
                writer.write("wav=Audio\n");
                writer.write("flac=Audio\n");
                writer.write("aac=Audio\n");
                writer.write("mp4=Videos\n");
                writer.write("avi=Videos\n");
                writer.write("mov=Videos\n");
                writer.write("mkv=Videos\n");
                writer.write("pdf=Documents\n");
                writer.write("doc=Documents\n");
                writer.write("docx=Documents\n");
                writer.write("txt=Documents\n");
                writer.write("xls=Documents\n");
                writer.write("xlsx=Documents\n");
                writer.write("zip=Archives\n");
                writer.write("rar=Archives\n");
                writer.write("7z=Archives\n");
                writer.write("tar=Archives\n");
                writer.write("exe=Programs\n");
                writer.write("jar=Programs\n");
                writer.write("bat=Programs\n");
            }

            System.out.println("Default configuration file created at: " + configFilePath);

        } catch (IOException e) {
            System.err.println("Failed to create default config file: " + e.getMessage());
            System.exit(3);
        }

    }

    public static Map<String, String> loadFileCategoriesFromConfig(String configFilePath) {
        Map<String, String> fileCategoryMap = new HashMap<>();
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);

            for (String key : properties.stringPropertyNames()) {
                fileCategoryMap.put(key.toLowerCase(), properties.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("Failed to load file categories: " + e.getMessage());
            System.exit(4);
        }

        return fileCategoryMap;
    }

    public static void organizeFilesByCategory(String directoryPath, Map<String, String> fileCategoryMap) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files to organize.");
            System.exit(0);
        }

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                int lastDotIndex = fileName.lastIndexOf(".");

                // Get the file extension
                if (lastDotIndex != -1) {
                    String extension = fileName.substring(lastDotIndex + 1).toLowerCase();
                    String folderName = fileCategoryMap.getOrDefault(extension, "Others");

                    // Create folder if it doesn't exist
                    File folder = new File(directory, folderName);
                    if (!folder.exists()) {
                        if (folder.mkdir())
                            System.out.println("Created folder: " + folderName);
                        else {
                            System.err.println("Failed to create folder: " + folderName);
                            continue;
                        }
                    }

                    // Move file to the corresponding folder
                    try {
                        Files.move(file.toPath(), Path.of(folder.getAbsolutePath(), file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Moved " + fileName + " to " + folderName);
                    } catch (IOException e) {
                        System.err.println("Failed to move " + fileName + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
