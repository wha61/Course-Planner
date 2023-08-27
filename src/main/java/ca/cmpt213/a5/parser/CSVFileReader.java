package ca.cmpt213.a5.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * CSVFileReader class to read data from .csv file and save it as 2D String array
 */
public class CSVFileReader {
    public CSVFileReader() {
    }

    //read in csv file and store in a 2D array
    //reference: https://blog.csdn.net/u010087338/article/details/119917410?ops_request_misc=&request_id=&biz_id=102&utm_term=java%E8%AF%BB%E5%8F%96csv%E6%96%87%E4%BB%B6%E5%88%B0array&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-119917410.142^v5^pc_search_result_control_group,157^v4^control&spm=1018.2226.3001.4187
    public List<String> readLine(String line) {
        List<String> oneLineValues = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                oneLineValues.add(rowScanner.next());
            }
        }
        return oneLineValues;
    }
    public List<List<String>> csvToArray(String path) throws FileNotFoundException {
        List<List<String>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                List<String> oneLineValues = readLine(scanner.nextLine());
                data.add(oneLineValues);
            }
        }
        return data;
    }

}


