package com.epam.ivko.archivator.compressor.impl;

import com.epam.ivko.archivator.compressor.Compressor;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompressor implements Compressor {
    private static final String ZIP_EXTENSION = ".zip";

    @Override
    public File compress(File file) {
        return compress(file, file.getParentFile().toPath());
    }

    @Override
    public File compress(File file, Path path) {
        String compressedFileName = FilenameUtils.removeExtension(file.getName());
        File compressedFile = createNewFile(path.toFile(), compressedFileName + ZIP_EXTENSION);
        try (ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(compressedFile));
             FileInputStream inputStream = new FileInputStream(file.getPath())) {
            ZipEntry zipEntry = new ZipEntry(file.getName());
            outputStream.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.write(buffer);
            outputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressedFile;
    }

    @Override
    public File compress(List<File> files, String name) {
        return compress(files, name, files.get(0).getParentFile().toPath());
    }

    @Override
    public File compress(List<File> files, String name, Path path) {
        File compressedFile = createNewFile(path.toFile(), name + ZIP_EXTENSION);
        byte[] buffer = new byte[1024];
        try {
            ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(compressedFile));
            for (File file : files) {
                FileInputStream inputStream = new FileInputStream(file.getPath());
                outputStream.putNextEntry(new ZipEntry(file.getName()));
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.closeEntry();
                inputStream.close();
            }
            outputStream.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return compressedFile;
    }

    @Override
    public List<File> decompress(File file) {
        return decompress(file, file.getParentFile().toPath());
    }

    @Override
    public List<File> decompress(File file, Path path) {
        byte[] buffer = new byte[1024];
        List<File> decompressedFiles = new ArrayList<>();
        try {
            File folderDecompressTo = path.toFile();
            if (!folderDecompressTo.exists()) {
                folderDecompressTo.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = createNewFile(folderDecompressTo, fileName);
                if (zipEntry.isDirectory()) {
                    newFile.mkdir();
                } else {
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                decompressedFiles.add(newFile);
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return decompressedFiles;
    }

    private File createNewFile(File folderDecompressTo, String fileName) {
        File file = new File(folderDecompressTo, fileName);
        if (file.exists()) {
            String newName = createNewFileName(fileName);
            file = new File(folderDecompressTo, newName);
        }
        return file;
    }

    private String createNewFileName(String fileName) {
        if (fileName == null) {
            return null;
        }
        String rawFileName = FilenameUtils.removeExtension(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        Pattern pattern = Pattern.compile("(\\()(\\d+)(\\)$)");
        Matcher matcher = pattern.matcher(rawFileName);
        if (!matcher.find()) {
            rawFileName = rawFileName + ("(1)");
        } else {
            int fileNumber = Integer.parseInt(matcher.group(2)) + 1;
            rawFileName = rawFileName + matcher.group(1) + fileNumber + matcher.group(3);
        }
        return rawFileName + "." + extension;
    }
}