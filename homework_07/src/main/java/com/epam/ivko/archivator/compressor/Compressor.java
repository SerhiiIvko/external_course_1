package com.epam.ivko.archivator.compressor;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface Compressor {
    File compress(File file);
    File compress(File file, Path path);
    File compress(List<File> files, String name);
    File compress(List<File> files, String name, Path path);
    List<File> decompress(File file);
    List<File> decompress(File file, Path path);
}