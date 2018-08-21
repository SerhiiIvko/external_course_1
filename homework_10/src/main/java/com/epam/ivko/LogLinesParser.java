package com.epam.ivko;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogLinesParser implements Serializable {
    private String fileSaveTo;
    private LineMatcher lineMatcher = new LineMatcher();

    public void parse(String fileName, int limit, String fileSaveTo) {
        this.fileSaveTo = fileSaveTo;
        List<String> logLines = getLogLines(fileName);
        Map<String, List<String>> resultMap = getResultMap(logLines, limit);
        handleResult(resultMap);
    }

    private List<String> getLogLines(String fileName) {
        List<String> logLines = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (Stream linesStream = Files.lines(path)) {
            linesStream.forEach(line -> logLines.add(lineMatcher.getResultParsedLine((String) line)));
        } catch (IOException e) {
            e.getMessage();
        }
        Collections.sort(logLines);
        return logLines;
    }

    private Map<String, List<String>> getResultMap(List<String> logLines, int limit) {
        LogLinesParser parser = new LogLinesParser();
        logLines.sort(getComparator());
        Map<String, List<String>> map = parser.getMultimap(logLines);
        Map<String, List<String>> limitedListMap = new HashMap<>();
        map.keySet().forEach(key -> {
            List<String> strings = map.get(key);
            if (strings != null) {
                List<String> limitedList = strings
                        .stream()
                        .sorted(getComparator())
                        .limit(limit)
                        .collect(Collectors.toList());
                limitedListMap.put(key, limitedList);
            }
        });
        return limitedListMap;
    }

    private void handleResult(Map<String, List<String>> limitedListMap) {
        StringBuilder sb = new StringBuilder();
        List<String> toSort = new ArrayList<>(limitedListMap.keySet());
        toSort.sort(null);
        toSort.forEach(key -> {
            System.out.println(key);
            sb.append(key);
            limitedListMap.get(key).stream().map(value -> "\t" + value).forEach(data -> {
                System.out.println(data);
                sb.append(data);
            });
        });
        if (fileSaveTo != null) {
            serializeLog(sb.toString());
        }
    }

    public void serializeLog(String sb) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileSaveTo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sb);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String deserializeLogDatFile(File file) {
        String result = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            result = (String) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Map<String, List<String>> getMultimap(List<String> logLines) {
        Map<String, List<String>> map = new HashMap<>();
        logLines.forEach(log -> {
            String key = log.substring(0, 11);
            String value = "\t" + log.substring(12, log.length() - 1);
            List<String> stringList = map.computeIfAbsent(key, k -> new ArrayList<>());
            stringList.add(value);
        });
        return map;
    }

    private Comparator<String> getComparator() {
        final Pattern compile = Pattern.compile("(\\d+)\\s* ms");
        return (line1, line2) -> {
            final Matcher matcher1 = compile.matcher(line1);
            final Matcher matcher2 = compile.matcher(line2);
            while (true) {
                final boolean found1 = matcher1.find();
                final boolean found2 = matcher2.find();
                if (!found1 || !found2) {
                    return Boolean.compare(found1, found2);
                } else if (!matcher1.group().equals(matcher2.group())) {
                    if (matcher1.group(1) == null || matcher2.group(1) == null) {
                        return matcher1.group().compareTo(matcher2.group());
                    } else {
                        return Integer.valueOf(matcher2.group(1)).compareTo(Integer.valueOf(matcher1.group(1)));
                    }
                }
            }
        };
    }
}