package com.epam.ivko;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineMatcher {
    String getDate(String line) {
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = datePattern.matcher(line);
        String date = null;
        if (matcher.find(0)) {
            date = matcher.group();
        }
        return date;
    }

    String getModuleName(String line) {
        Pattern moduleNamePattern = Pattern.compile("\\b(Module)(=)[A-Z]{3}");
        Matcher matcher = moduleNamePattern.matcher(line);
        String name = null;
        if (matcher.find(0)) {
            name = matcher.group();
        }
        if (name != null) {
            name = name.substring(name.length() - 3)
                    .concat(" ")
                    .concat(name.substring(0, 6))
                    .concat(":");
        }
        return name;
    }

    String getTimeOfLogLine(String line) {
        Pattern timePattern = Pattern.compile("(?m)(\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
        Matcher matcher = timePattern.matcher(line);
        String timeLogLine = null;
        if (matcher.find(0)) {
            timeLogLine = matcher.group();
        }
        return timeLogLine;
    }

    String getTimeOperationDuration(String line) {
        Pattern durationOperationTimePattern = Pattern.compile("(\\d+)\\s* ms");
        Matcher matcher = durationOperationTimePattern.matcher(line);
        String durationOperation = null;
        if (matcher.find(0)) {
            durationOperation = matcher.group(0);
        }
        return durationOperation;
    }

    String getOperationHeader(String line) {
        Pattern operationPattern = Pattern.compile("Operation");
        Matcher matcher = operationPattern.matcher(line);
        String operationHeader = null;
        if (matcher.find(0)) {
            operationHeader = matcher.group();
        }
        return operationHeader;
    }

    String getOperationName(String line) {
        Pattern operationPattern = Pattern.compile("Operation:(.*)Execution");
        Matcher matcher = operationPattern.matcher(line);
        String operationHeader = null;
        if (matcher.find(0)) {
            operationHeader = matcher.group(1);
        }
        return operationHeader;
    }

    String getResultParsedLine(String line) {
        String moduleName = getModuleName(line);
        String operationHeader = getOperationHeader(line);
        String operationDuration = getTimeOperationDuration(line);
        String operationDate = getDate(line);
        String operationTime = getTimeOfLogLine(line);
        String operationName = getOperationName(line);
        String appendedString = ", finished at ";
        return moduleName
                .concat(" ")
                .concat(operationHeader)
                .concat(operationName)
                .concat(operationDuration)
                .concat(appendedString)
                .concat(operationDate)
                .concat(" ")
                .concat(operationTime);
    }

    int getIntegerDurationTime(String line) {
        String num = getTimeOperationDuration(line).replaceAll("\\D", "");
        return Integer.parseInt(num);
    }
}