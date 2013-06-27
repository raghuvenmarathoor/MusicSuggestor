/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 *
 * @author Staff
 */
public class CheckCRC {

    long checksum;

    public long getCRC(String filePath) {
        try {

            CheckedInputStream cis = null;
            long fileSize = 0;
            try {
                // Computer CRC32 checksum
                cis = new CheckedInputStream(
                        new FileInputStream(filePath), new CRC32());

                fileSize = new File(filePath).length();

            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
                System.exit(1);
            }

            byte[] buf = new byte[128];
            while (cis.read(buf) >= 0) {
            }

            checksum = cis.getChecksum().getValue();
            //System.out.println(checksum + " " + fileSize + " " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return checksum;


    }
}
