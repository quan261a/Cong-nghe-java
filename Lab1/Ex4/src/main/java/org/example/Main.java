package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file.");
            return;
        }

        String urlString = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(urlString)) {
            System.out.println("This is not a valid URL.");
            return;
        }

        try {
            URL url = new URL(urlString);
            File destinationFile = new File(url.getFile());
            FileUtils.copyURLToFile(url, destinationFile);
            System.out.println("File downloaded: " + destinationFile.getName());
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL format.");
        } catch (IOException e) {
            System.out.println("Error downloading the file: " + e.getMessage());
        }
    }
}
