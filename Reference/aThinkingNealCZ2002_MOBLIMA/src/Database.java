import java.io.*;
import java.util.ArrayList;


/**
 * Records all the essential methods for other database classes
 */
public interface Database {

    void addRecord();

    void deleteRecord();

    void updateRecord();

    void saveToFile();

}
