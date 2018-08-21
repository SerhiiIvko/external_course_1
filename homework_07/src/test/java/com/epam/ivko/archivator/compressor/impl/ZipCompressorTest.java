package com.epam.ivko.archivator.compressor.impl;

import com.epam.ivko.archivator.compressor.Compressor;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.omg.CORBA.BooleanHolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZipCompressorTest {

    private static final String COMPRESS_RESOURCE_ROOT = "com/epam/ivko/archivator/compressor/compress";
    private static final String DECOMPRESS_RESOURCE_ROOT = "com/epam/ivko/archivator/compressor/decompress";
    private static final String TXT_EXTENSION = ".txt";
    private static final String ZIP_EXTENSION = ".zip";

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    private Compressor compressor;

    @Test
    public void compressSingleFile() {
        try {
            // GIVEN:
            String fileName = "file1";
            File rawFile = copyFileToTmp(COMPRESS_RESOURCE_ROOT, fileName + TXT_EXTENSION);
            String expectedFileName = fileName + ZIP_EXTENSION;
            compressor = new ZipCompressor();

            // WHEN:
            File compressedFile = compressor.compress(rawFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFileName));
            Assert.assertEquals(expectedFileName, compressedFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compressSeveralFiles() {
        try {
            // GIVEN:
            String fileName1 = "file1";
            String fileName2 = "file2";
            String compressedFileName = "archive";
            List<File> rawFiles = copyFilesToTmp(Arrays.asList(fileName1, fileName2));
            compressor = new ZipCompressor();

            // WHEN:
            File compressedFile = compressor.compress(rawFiles, compressedFileName);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), compressedFileName + ZIP_EXTENSION));
            Assert.assertEquals(compressedFileName + ZIP_EXTENSION, compressedFile.getName());
            Assert.assertTrue(compareFileStructure(rawFiles, compressedFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compressSingleFileWithPath() {
        try {
            // GIVEN:
            String fileName = "file1";
            File rawFile = copyFileToTmp(COMPRESS_RESOURCE_ROOT, fileName + TXT_EXTENSION);
            File folderCompressTo = new File(tmpFolder.getRoot(), "folder");
            folderCompressTo.mkdir();
            Path pathCompressTo = folderCompressTo.toPath();
            String expectedFileName = fileName + ZIP_EXTENSION;
            compressor = new ZipCompressor();

            // WHEN:
            File compressedFile = compressor.compress(rawFile, pathCompressTo);

            // THEN:
            Assert.assertTrue(checkFileExists(folderCompressTo, expectedFileName));
            Assert.assertEquals(expectedFileName, compressedFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compressSeveralFilesWithPath() {
        try {
            // GIVEN:
            String fileName1 = "file1";
            String fileName2 = "file2";
            File folderCompressTo = new File(tmpFolder.getRoot(), "folder");
            folderCompressTo.mkdir();
            Path pathCompressTo = folderCompressTo.toPath();
            String compressedFileName = "archive";
            List<File> rawFiles = copyFilesToTmp(Arrays.asList(fileName1, fileName2));
            long actulaBytesNumber = rawFiles.stream().mapToLong(File::getTotalSpace).sum();
            compressor = new ZipCompressor();

            // WHEN:
            File compressedFile = compressor.compress(rawFiles, compressedFileName, pathCompressTo);

            // THEN:
            Assert.assertTrue(checkFileExists(folderCompressTo, compressedFileName + ZIP_EXTENSION));
            Assert.assertEquals(compressedFileName + ZIP_EXTENSION, compressedFile.getName());
            Assert.assertTrue(compareFileStructure(rawFiles, compressedFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFile() {
        // GIVEN:
        try {
            String compressedFileName = "archive1" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive1";
            String expectedFileName = "file1" + TXT_EXTENSION;
            File expectedFile = getFileFromResources(expectedFilePath + File.separator + expectedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFileName));
            Assert.assertEquals(expectedFileName, actualDecompressedFile.get(0).getName());
            Assert.assertTrue(FileUtils.contentEquals(expectedFile, actualDecompressedFile.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFileIfFileExists() {
        // GIVEN:
        try {
            String compressedFileName = "archive1" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive1";
            String expectedFileName = "file1" + TXT_EXTENSION;
            String expectedCreatedFileName = "file1(1)" + TXT_EXTENSION;
            copyFileToTmp(DECOMPRESS_RESOURCE_ROOT+ File.separator + "expectedFromArchive1", expectedFileName);
            File expectedFile = getFileFromResources(expectedFilePath + File.separator + expectedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFileName));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedCreatedFileName));
            Assert.assertEquals(expectedCreatedFileName, actualDecompressedFile.get(0).getName());
            Assert.assertTrue(FileUtils.contentEquals(expectedFile, actualDecompressedFile.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFileWithPath() {
        // GIVEN:
        try {
            String folderName = "folder";
            File folderDecompressTo = new File(tmpFolder.getRoot(), folderName);
            Path pathDecompressTo = folderDecompressTo.toPath();
            String compressedFileName = "archive1";
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName + ZIP_EXTENSION);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive1";
            String expectedFileName = "file1" + TXT_EXTENSION;
            File expectedFile = getFileFromResources(expectedFilePath + File.separator + expectedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile, pathDecompressTo);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), folderName));
            Assert.assertTrue(folderDecompressTo.exists());
            Assert.assertTrue(checkFileExists(folderDecompressTo, expectedFileName));
            Assert.assertEquals(expectedFileName, actualDecompressedFile.get(0).getName());
            Assert.assertTrue(FileUtils.contentEquals(expectedFile, actualDecompressedFile.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSeveralFiles() {
        // GIVEN:
        try {
            String currentResourceDirectoryName = "expectedFromArchive2";
            List<String> expectedFileNames = new ArrayList<>();
            expectedFileNames.add("file1" + TXT_EXTENSION);
            expectedFileNames.add("file2" + TXT_EXTENSION);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
            List<File> expectedFiles = new ArrayList<>();
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFileNames.get(0)));
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFileNames.get(1)));
            String compressedFileName = "archive2" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFiles.get(0).getName()));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFiles.get(1).getName()));
            Assert.assertTrue(isFilesExistsInList(expectedFileNames, actualDecompressedFile));
            Assert.assertEquals(expectedFiles.size(), actualDecompressedFile.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSeveralFilesWithPath() {
        // GIVEN:
        try {
            File folderCompressTo = new File(tmpFolder.getRoot(), "decompress");
            folderCompressTo.mkdir();
            Path pathCompressTo = folderCompressTo.toPath();
            String currentResourceDirectoryName = "expectedFromArchive2";
            List<String> expectedFileNames = new ArrayList<>();
            expectedFileNames.add("file1" + TXT_EXTENSION);
            expectedFileNames.add("file2" + TXT_EXTENSION);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
            List<File> expectedFiles = new ArrayList<>();
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFileNames.get(0)));
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFileNames.get(1)));
            String compressedFileName = "archive2" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile, pathCompressTo);

            // THEN:
            Assert.assertEquals(2, actualDecompressedFile.size());
            Assert.assertEquals(expectedFileNames.size(), actualDecompressedFile.size());
            Assert.assertTrue(isFilesExistsInList(expectedFileNames, actualDecompressedFile));
            Assert.assertFalse(actualDecompressedFile.get(0).isDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFolder() {
        // GIVEN:
        try {
            String compressedFileName = "archive3" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive3";
            String expectedFolderName = "folder";
            File expectedFolder = getFileFromResources(expectedFilePath + File.separator + expectedFolderName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFiles = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertEquals(3, actualDecompressedFiles.size());
            Assert.assertTrue(actualDecompressedFiles.get(0).isDirectory());
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderName));
            Assert.assertTrue(areDirectoriesEqual(expectedFolder.toPath(), actualDecompressedFiles.get(0).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFolderWithPath() {
        // GIVEN:
        try {
            File folderCompressTo = new File(tmpFolder.getRoot(), "decompress");
            folderCompressTo.mkdir();
            Path pathCompressTo = folderCompressTo.toPath();
            String compressedFileName = "archive3" + ZIP_EXTENSION;
            File compressedFile = getFileFromResources(DECOMPRESS_RESOURCE_ROOT + File.separator + compressedFileName);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive3";
            String expectedFolderName = "folder";
            File expectedFolder = getFileFromResources(expectedFilePath + File.separator + expectedFolderName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFiles = compressor.decompress(compressedFile, pathCompressTo);

            // THEN:
            Assert.assertEquals(3, actualDecompressedFiles.size());
            Assert.assertTrue(actualDecompressedFiles.get(0).isDirectory());
            Assert.assertTrue(areDirectoriesEqual(expectedFolder.toPath(), actualDecompressedFiles.get(0).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSeveralFolders() {
        // GIVEN:
        try {
            String currentResourceDirectoryName = "expectedFromArchive4";
            String compressedFileName = "archive4" + ZIP_EXTENSION;
            List<String> expectedFolderNames = new ArrayList<>();
            expectedFolderNames.add("folder1");
            expectedFolderNames.add("folder2");
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
            List<File> expectedFolders = new ArrayList<>();
            expectedFolders.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(0)));
            expectedFolders.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(1)));
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFiles = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertEquals(6, actualDecompressedFiles.size());
            Assert.assertTrue(actualDecompressedFiles.get(0).isDirectory());
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(0)));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(1)));
            Assert.assertTrue(areDirectoriesEqual(expectedFolders.get(0).toPath(),
                    getAppropriateFile(actualDecompressedFiles, expectedFolders.get(0)).toPath()));
            Assert.assertTrue(areDirectoriesEqual(expectedFolders.get(1).toPath(),
                    getAppropriateFile(actualDecompressedFiles, expectedFolders.get(1)).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSeveralFoldersWithPaths() {
        // GIVEN:
        try {
            File folderCompressTo = new File(tmpFolder.getRoot(), "decompress");
            folderCompressTo.mkdir();
            Path pathCompressTo = folderCompressTo.toPath();
            String currentResourceDirectoryName = "expectedFromArchive4";
            String compressedFileName = "archive4" + ZIP_EXTENSION;
            List<String> expectedFolderNames = new ArrayList<>();
            expectedFolderNames.add("folder1");
            expectedFolderNames.add("folder2");
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
            List<File> expectedFolders = new ArrayList<>();
            expectedFolders.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(0)));
            expectedFolders.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(1)));
            File compressedFile = getFileFromResources(DECOMPRESS_RESOURCE_ROOT + File.separator + compressedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFiles = compressor.decompress(compressedFile, pathCompressTo);

            // THEN:
            Assert.assertEquals(6, actualDecompressedFiles.size());
            Assert.assertTrue(actualDecompressedFiles.get(0).isDirectory());
            Assert.assertTrue(actualDecompressedFiles.get(3).isDirectory());
            Assert.assertTrue(areDirectoriesEqual(expectedFolders.get(0).toPath(),
                    getAppropriateFile(actualDecompressedFiles, expectedFolders.get(0)).toPath()));
            Assert.assertTrue(areDirectoriesEqual(expectedFolders.get(1).toPath(),
                    getAppropriateFile(actualDecompressedFiles, expectedFolders.get(1)).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressFoldersAndFiles() {
        // GIVEN:
        try {
            String currentResourceDirectoryName = "expectedFromArchive5";
            String compressedFileName = "archive5" + ZIP_EXTENSION;
            List<File> expectedFiles = new ArrayList<>();
            List<String> expectedFolderNames = new ArrayList<>();
            expectedFolderNames.add("folder1");
            expectedFolderNames.add("folder2");
            expectedFolderNames.add("file5" + TXT_EXTENSION);
            expectedFolderNames.add("file6" + TXT_EXTENSION);
            String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(0)));
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(1)));
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(2)));
            expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(3)));
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFilesAndFolders = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertEquals(8, actualDecompressedFilesAndFolders.size());
            Assert.assertTrue(actualDecompressedFilesAndFolders.get(0).isDirectory());
            Assert.assertTrue(actualDecompressedFilesAndFolders.get(3).isDirectory());
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(0)));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(1)));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(2)));
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFolderNames.get(3)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressFoldersAndFilesWithPath() {
        // GIVEN:
        File folderCompressTo = new File(tmpFolder.getRoot(), "decompress");
        folderCompressTo.mkdir();
        Path pathCompressTo = folderCompressTo.toPath();
        String currentResourceDirectoryName = "expectedFromArchive5";
        String compressedFileName = "archive5" + ZIP_EXTENSION;
        List<File> expectedFiles = new ArrayList<>();
        List<String> expectedFolderNames = new ArrayList<>();
        expectedFolderNames.add("folder1");
        expectedFolderNames.add("folder2");
        expectedFolderNames.add("file5" + TXT_EXTENSION);
        expectedFolderNames.add("file6" + TXT_EXTENSION);
        String expectedFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + currentResourceDirectoryName;
        expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(0)));
        expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(1)));
        expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(2)));
        expectedFiles.add(getFileFromResources(expectedFilePath + File.separator + expectedFolderNames.get(3)));
        File compressedFile = getFileFromResources(DECOMPRESS_RESOURCE_ROOT + File.separator + compressedFileName);
        compressor = new ZipCompressor();

        // WHEN:
        List<File> actualDecompressedFilesAndFolders = compressor.decompress(compressedFile, pathCompressTo);

        // THEN:
        Assert.assertEquals(8, actualDecompressedFilesAndFolders.size());
        Assert.assertTrue(actualDecompressedFilesAndFolders.get(0).isDirectory());
        Assert.assertTrue(actualDecompressedFilesAndFolders.get(3).isDirectory());
        Assert.assertTrue(actualDecompressedFilesAndFolders.get(1).isFile());
        Assert.assertTrue(actualDecompressedFilesAndFolders.get(4).isFile());
        try {
            Assert.assertTrue(areDirectoriesEqual(expectedFiles.get(0).toPath(),
                    getAppropriateFile(actualDecompressedFilesAndFolders, expectedFiles.get(0)).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compressSingleFileWhenFileExistsAlready() {
        try {
            // GIVEN:
            String fileName = "file1";
            String compressedFileName = fileName + ZIP_EXTENSION;
            copyFileToTmp(COMPRESS_RESOURCE_ROOT, compressedFileName);
            File rawFile = copyFileToTmp(COMPRESS_RESOURCE_ROOT, fileName + TXT_EXTENSION);
            String expectedFileName = fileName + "(1)" + ZIP_EXTENSION;
            compressor = new ZipCompressor();

            // WHEN:
            File compressedFile = compressor.compress(rawFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFileName));
            Assert.assertEquals(expectedFileName, compressedFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressSingleFileWhenFileExistsAlready() {
        // GIVEN:
        try {
            String compressedFileName = "archive1" + ZIP_EXTENSION;
            File compressedFile = copyFileToTmp(DECOMPRESS_RESOURCE_ROOT, compressedFileName);
            String expectedSourceFilePath = DECOMPRESS_RESOURCE_ROOT + File.separator + "expectedFromArchive1";
            String expectedFileName = "file1(1)" + TXT_EXTENSION;
            String expectedSourceFileName = "file1" + TXT_EXTENSION;
            copyFileToTmp(expectedSourceFilePath, expectedSourceFileName);
            File expectedSourceFile = getFileFromResources(expectedSourceFilePath + File.separator + expectedSourceFileName);
            compressor = new ZipCompressor();

            // WHEN:
            List<File> actualDecompressedFile = compressor.decompress(compressedFile);

            // THEN:
            Assert.assertTrue(checkFileExists(tmpFolder.getRoot(), expectedFileName));
            Assert.assertEquals(expectedFileName, actualDecompressedFile.get(0).getName());
            Assert.assertTrue(FileUtils.contentEquals(expectedSourceFile, actualDecompressedFile.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private File getAppropriateFile(List<File> files, File file) {
        return files.stream().filter(f -> file.getName().equals(f.getName())).findFirst().get();
    }

    private boolean areDirectoriesEqual(Path one, Path other) throws IOException {
        final BooleanHolder equivalityHolder = new BooleanHolder(true);
        Files.walkFileTree(one, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);
                Path relativize = one.relativize(file);
                Path fileInOther = other.resolve(relativize);
                byte[] otherBytes = Files.readAllBytes(fileInOther);
                byte[] thisBytes = Files.readAllBytes(file);
                if (!Arrays.equals(otherBytes, thisBytes)) {
                    equivalityHolder.value = false;
                }
                return result;
            }
        });
        return equivalityHolder.value;
    }

    private boolean isFilesExistsInList(List<String> list1, List<File> list2) {
        return list2.stream().map(File::getName).collect(Collectors.toList()).containsAll(list1);
    }

    private File getFileFromResources(String resourcePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(resourcePath);
        return resourceUrl != null ? new File(resourceUrl.getFile()) : null;
    }

    private boolean compareFileStructure(List<File> sourceFiles, File compressedFile) throws IOException {
        List<String> compressedPaths = getCompressedPaths(compressedFile);
        if (sourceFiles.stream().map(File::getParentFile).distinct().count() != 1) {
            throw new IllegalArgumentException("different parent directories");
        }
        File parentFile = sourceFiles.iterator().next().getParentFile();
        Function<File, Stream<File>> fileStreamFunction = file -> {
            List<File> files = new ArrayList<>();
            if (file.isDirectory()) {
                files.addAll(FileUtils.listFiles(file, null, true));
            } else {
                files.add(file);
            }
            return files.stream();
        };
        List requiredFiles = sourceFiles.stream()
                .flatMap(fileStreamFunction)
                .collect(Collectors.toList());
        Path rootPath = parentFile.toPath();
        return requiredFiles.stream()
                .map(file -> rootPath.relativize(((File) file).toPath()).toString())
                .allMatch(o -> compressedPaths.contains(o));
    }

    private List<String> getCompressedPaths(File compressedFile) throws IOException {
        Path filePath = compressedFile.toPath();
        List<String> compressedPaths = new ArrayList<>();
        try (FileSystem zipFS = FileSystems.newFileSystem(filePath, null)) {
            Path rootPath = zipFS.getPath(File.separator);
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    String pathString = path.toString();
                    pathString = pathString.startsWith(zipFS.getSeparator()) ? pathString.substring(1) : pathString;
                    compressedPaths.add(pathString);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        return compressedPaths;
    }

    private boolean checkFileExists(File parent, String name) {
        File file = new File(parent.getPath(), name);
        return file.exists();
    }

    private List<File> copyFilesToTmp(List<String> fileNames) throws IOException {
        List<File> files = new ArrayList<>();
        for (String fileName : fileNames) {
            files.add(copyFileToTmp(COMPRESS_RESOURCE_ROOT, fileName + TXT_EXTENSION));
        }
        return files;
    }

    private File copyFileToTmp(String parentPath, String fileName) throws IOException {
        ClassLoader classLoader = ZipCompressorTest.class.getClassLoader();
        InputStream resourceStream = classLoader.getResourceAsStream(parentPath + File.separator + fileName);
        String tmpFilePathString = tmpFolder.getRoot().getPath() + File.separator + fileName;
        Path tmpFilePath = Paths.get(tmpFilePathString);
        Files.copy(resourceStream, tmpFilePath, StandardCopyOption.REPLACE_EXISTING);
        return new File(tmpFilePathString);
    }
}