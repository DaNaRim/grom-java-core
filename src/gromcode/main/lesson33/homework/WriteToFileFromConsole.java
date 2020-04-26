package gromcode.main.lesson33.homework;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class WriteToFileFromConsole {

    public static void writeToFileFromConsole(String path) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            System.err.println("File with path " + path + " not found");
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file content to write in the file:");

        try {
            String line;
            while (!(line = br.readLine()).equals("wr")) {
                bw.append(line);
                bw.append("\r\n");
            }
        } catch (IOException e) {
            System.err.println("Can't write to file with path " + path);
        } finally {
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(br);
        }
    }
}