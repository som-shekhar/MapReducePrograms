package com.lpu;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGenerator {

    public static void main(String[] args) throws IOException {
        String[] firstNames = {
            "John", "Emma", "Michael", "Sophia", "Daniel", "Olivia",
            "James", "Isabella", "Robert", "Ava", "David", "Mia",
            "William", "Charlotte", "Ethan", "Amelia", "Alexander", "Harper",
            "Matthew", "Ella", "Joseph", "Abigail", "Lucas", "Emily"
        };

        String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris"
        };

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/somshekhar/ss_data", true)));

        List<String> list = new ArrayList<>();

        // Generate combinations
        for (String f : firstNames) {
            for (String l : lastNames) {
                list.add(f + "\t" + l);
            }
        }

        // Shuffle so data looks random
        Collections.shuffle(list);

        System.out.println("Total records: " + list.size());

        int recordCount = 0;
        for (String s : list) {
            recordCount++;
            out.println(s); // println handles new line
            if (recordCount % 1000 == 0) { // just to see progress
                System.out.println("Written: " + recordCount);
            }
        }

        out.close();
        System.out.println("Done! Records written: " + recordCount);
    }
}
