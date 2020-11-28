package com.zvoa.maths.tfl.grammar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zvoa.maths.tfl.grammar.Grammar.NON_TERMINALS_DELIMITER;
import static com.zvoa.maths.tfl.grammar.Grammar.TERMINALS_DELIMITER;
import static com.zvoa.maths.tfl.grammar.NonTerminal.NAME_DELIMITER;
import static com.zvoa.maths.tfl.grammar.NonTerminal.RULES_DELIMITER;
import static com.zvoa.maths.tfl.grammar.Rule.SYMBOLS_DELIMITER;

public class ColorPrintOutput {
    public static final String ANSI_RESET  = "\u001B[0m";

    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK  = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED    = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN  = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE   = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN   = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE  = "\u001B[97m";

    private static Map<Integer,String> colorsMap = new HashMap<>();

    static {
        colorsMap.put(0,ANSI_BLACK);
        colorsMap.put(1,ANSI_RED);
        colorsMap.put(2,ANSI_GREEN);
        colorsMap.put(3,ANSI_YELLOW);
        colorsMap.put(4,ANSI_BLUE);
        colorsMap.put(5,ANSI_PURPLE);
        colorsMap.put(6,ANSI_CYAN);
        colorsMap.put(7,ANSI_WHITE);

        colorsMap.put(8,ANSI_BRIGHT_BLACK);
        colorsMap.put(9,ANSI_BRIGHT_RED);
        colorsMap.put(10,ANSI_BRIGHT_GREEN);
        colorsMap.put(11,ANSI_BRIGHT_YELLOW);
        colorsMap.put(12,ANSI_BRIGHT_BLUE);
        colorsMap.put(13,ANSI_BRIGHT_PURPLE);
        colorsMap.put(14,ANSI_BRIGHT_CYAN);
        colorsMap.put(15,ANSI_BRIGHT_WHITE);
    }

    public static String getColorBySymbol(Symbol symbol) {
        return colorsMap.get(symbol.hashCode() % colorsMap.size());
    }

    public static void printGrammar(Grammar grammar) {
        System.out.println(ANSI_RESET + "Terminals:");
        Iterator<Terminal> terminalIterator = grammar.getTerminals().iterator();

        while(terminalIterator.hasNext()) {
            Terminal terminal = terminalIterator.next();
            printSymbol(terminal);

            if(terminalIterator.hasNext()) {
                System.out.print(ANSI_RESET + TERMINALS_DELIMITER);
            } else {
                System.out.println();
            }
        }

        System.out.println(ANSI_RESET + "NonTerminals:");
        Iterator<NonTerminal> nonTerminalIterator = grammar.getNonTerminals().iterator();
        while(nonTerminalIterator.hasNext()) {
            NonTerminal nonTerminal = nonTerminalIterator.next();
            printNonTerminal(nonTerminal);

            if(nonTerminalIterator.hasNext()) {
                System.out.print(ANSI_RESET + NON_TERMINALS_DELIMITER);
            } else {
                System.out.println();
            }
        }
    }

    public static void printNonTerminal(NonTerminal nonTerminal) {
        printSymbol(nonTerminal);
        System.out.print(ANSI_RESET + NAME_DELIMITER);

        Iterator<Rule> ruleIterator = nonTerminal.getRules().iterator();
        while(ruleIterator.hasNext()) {
            Rule rule = ruleIterator.next();
            rule.getSymbols().forEach(symbol -> {
                printSymbol(symbol);
                System.out.print(ANSI_RESET + SYMBOLS_DELIMITER);
            });

            if(ruleIterator.hasNext()) {
                System.out.print(ANSI_RESET +  RULES_DELIMITER);
            }
        };
    }

    public static void printSymbol(Symbol symbol) {
        System.out.print(getColorBySymbol(symbol) + symbol.getName());
    }
}
