package com.epam.ivko;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CommandLineAnalyzer implements Serializable {
    private static final String FILE_ARG = "file";
    private static final String LOAD_ARG = "load";
    private static final String VIEW_ARG = "view";
    private static final String SAVE_ARG = "save";

    public void analyze(String[] args) {
        Map<String, String> commands = parseCommands(args);
        launchCommands(commands);
    }

    private Map<String, String> parseCommands(String[] args) {
        Map<String, String> commandsWithArgument = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("Flag should start with '-': " + args[i]);
            }
            if (i + 1 >= args.length) {
                throw new IllegalStateException("Flag " + args[i] + " must have argument");
            }
            commandsWithArgument.put(args[i].substring(1), args[i + 1]);
        }
        return commandsWithArgument;
    }

    private void launchCommands(Map<String, String> commands) {
        LogLinesParser parser = new LogLinesParser();
        if (commands.keySet().contains(LOAD_ARG) && commands.size() == 1) {
            String data = parser.deserializeLogDatFile(new File(commands.get(LOAD_ARG)));
            System.out.println(data);
        } else if (commands.keySet().contains(FILE_ARG) && commands.keySet().contains(VIEW_ARG)) {
            try {
                String viewArgument = commands.get(VIEW_ARG);
                int limit = Integer.parseInt(viewArgument);
                if (limit > 1) {
                    parser.parse(commands.get(FILE_ARG), limit, commands.get(SAVE_ARG));
                } else {
                    throw new IllegalArgumentException("Limit for output must be more than 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Illegal view argument" + commands.get(VIEW_ARG)
                        + ": " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Incorrect input arguments!");
        }
    }
}