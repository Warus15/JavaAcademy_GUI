package fx;

import java.io.File;

public class UserData {
    private String key;
    private File dataFile;

    public UserData(String key, File dataFile) {
        this.key = key;
        this.dataFile = dataFile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }
}
