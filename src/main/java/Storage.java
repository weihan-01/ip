import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {
    private static final String DIR = "data";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path dirPath = Paths.get(Storage.DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            Path filePath = Paths.get(this.filePath);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                return tasks;
            }
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task t = null;
                switch (parts[0]) {
                    case "T":
                        t = new Todo(parts[2]);
                        break;
                    case "D":
                        t = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        t = new Event(parts[2], parts[3], parts.length > 4 ? parts[4] : "");
                        break;
                }
                if (t != null && parts[1].equals("1")) {
                    t.markAsDone();
                }
                if (t != null) {
                    tasks.add(t);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Warning: Could not load tasks. File may be missing or corrupted.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            Path dirPath = Paths.get(Storage.DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            for (Task t : tasks) {
                bw.write(t.toSaveFormat());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Warning: Could not save tasks.");
        }
    }
}