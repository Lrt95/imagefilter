package com.komdab.imagefilter;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Conf {
    public String input;
    public String output;
    public String fileLog;
    public String[] filters;
    public boolean created;

    public Conf(String nameFile) throws FileNotFoundException {
        File config = new File(nameFile);
        if(!nameFile.equals("config.ini")) {
            if(!config.exists()) {
                throw new FileNotFoundException("File " + nameFile + " not found !");
            }
        }
        if(!config.exists())
            createConfigDefault();

        Ini ini = null;
        try {
            ini = new Ini(config);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert ini != null;
        this.filters = ini.get("config", "filters").split("\\|");
        this.input = ini.get("config", "inputDir");
        this.output = ini.get("config", "outputDir");
        this.fileLog = ini.get("config","logFile");
    }

    private void createConfigDefault() {
        try {
            FileWriter myWriter = new FileWriter("config.ini");
            myWriter.write("[config]\n");
            myWriter.write("inputDir = imgs\n");
            myWriter.write("outputDir = output_imgs\n");
            myWriter.write("logFile = image.log\n");
            myWriter.write("filters = blur:3|grayscale\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred to create init file.");
            e.printStackTrace();
        }
        created = true;
    }
}