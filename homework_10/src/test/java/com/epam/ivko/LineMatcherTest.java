package com.epam.ivko;

import org.junit.Assert;
import org.junit.Test;

public class LineMatcherTest {
    private LineMatcher lineMatcher;
    private String logLine;

    @Test
    public void getDateFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedDate = "2018-07-30";
        String actualDate;

        //WHEN:
        actualDate = lineMatcher.getDate(logLine);

        //THEN:
        Assert.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getTimeOfOperationDurationFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedTimeDuration = "1721 ms";
        String actualTimeDuration;

        //WHEN:
        actualTimeDuration = lineMatcher.getTimeOperationDuration(logLine);

        //THEN:
        Assert.assertEquals(expectedTimeDuration, actualTimeDuration);
    }

    @Test
    public void getModuleNameFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedModuleName = "MIC Module:";
        String actualModuleName;

        //WHEN:
        actualModuleName = lineMatcher.getModuleName(logLine);

        //THEN:
        Assert.assertEquals(expectedModuleName, actualModuleName);
    }

    @Test
    public void getTimeOfLogLineFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedTimeOfLogLine = "11:49:27.661";
        String actualTimeOfLogLine;

        //WHEN:
        actualTimeOfLogLine = lineMatcher.getTimeOfLogLine(logLine);

        //THEN:
        Assert.assertEquals(expectedTimeOfLogLine, actualTimeOfLogLine);
    }

    @Test
    public void getHeaderOfLineFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedHeaderName = "Operation";
        String actualHeaderName;

        //WHEN:
        actualHeaderName = lineMatcher.getOperationHeader(logLine);

        //THEN:
        Assert.assertEquals(expectedHeaderName, actualHeaderName);
    }

    @Test
    public void getOperationNameFromStringLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String expectedOperationName = " Logout ";
        String actualOperationName;

        //WHEN:
        actualOperationName = lineMatcher.getOperationName(logLine);

        //THEN:
        Assert.assertEquals(expectedOperationName, actualOperationName);
    }

    @Test
    public void getIntegerFromOperationDuration() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        int expectedInteger = 1721;
        int actualInteger;

        //WHEN:
        actualInteger = lineMatcher.getIntegerDurationTime(logLine);

        //THEN:
        Assert.assertEquals(expectedInteger, actualInteger);
    }

    @Test
    public void getResultParsedLogLineFromLogLine() {
        //GIVEN:
        lineMatcher = new LineMatcher();
        logLine = "2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout Execution time: 1721 ms";
        String moduleName = lineMatcher.getModuleName(logLine);
        String operationHeader = lineMatcher.getOperationHeader(logLine);
        String operationDuration = lineMatcher.getTimeOperationDuration(logLine);
        String operationDate = lineMatcher.getDate(logLine);
        String operationTime = lineMatcher.getTimeOfLogLine(logLine);
        String operationName = lineMatcher.getOperationName(logLine);
        String appendedString = ", finished at ";
        String expectedLogLine = "MIC Module: Operation Logout 1721 ms, finished at 2018-07-30 11:49:27.661";
        String actualLogLine;

        //WHEN:
        actualLogLine = moduleName
                .concat(" ")
                .concat(operationHeader)
                .concat(operationName)
                .concat(operationDuration)
                .concat(appendedString)
                .concat(operationDate)
                .concat(" ")
                .concat(operationTime);

        //THEN:
        Assert.assertEquals(expectedLogLine, actualLogLine);
    }
}