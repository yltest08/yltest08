package org.example.test;

import org.example.util.DataUtil;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T2 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("D:\\gitcode\\maventools\\src\\main\\resources\\static\\dest.java");
            File outputFile = new File("D:\\gitcode\\maventools\\src\\main\\resources\\static\\local.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            Set<String> set = new HashSet<>();
            Pattern pattern = Pattern.compile("request.get\\w+\\(ITag\\.(\\w+)\\)");
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    set.add(matcher.group(1));
                }
            }
            reader.close();

            List<String> list = new ArrayList<>(set);

            // Sort the list
            Collections.sort(list);
            for (String element : list) {
                writer.write(element+"\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
