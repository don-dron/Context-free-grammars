package com.zvoa.maths.tfl.grammar;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class Application {
    public static void main(String[] args) {
//        Grammar grammar = GrammarFactory
//                .build()
//                .addNonTerminal("S")
//                .addRule()
//                .addSymbol("A")
//                .addSymbol("b")
//                .endRule()
//                .endNonTerminal()
//                .addNonTerminal("A")
//                .addRule()
//                .addSymbol("S")
//                .addSymbol("a")
//                .endRule()
//                .addRule()
//                .addSymbol("A")
//                .addSymbol("a")
//                .endRule()
//                .endNonTerminal()
//                .endGrammar();

        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("A")
                        .addRule()
                            .addSymbol("S")
                            .addSymbol("a")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("S")
                            .addSymbol("b")
                        .endRule()
                        .addRule()
                            .addSymbol("A")
                            .addSymbol("g")
                        .endRule()
                        .addRule()
                            .addSymbol("b")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();
        System.out.println(grammar);
        LeftRecursionEliminate.eliminateLeftRecursion(grammar);
//        System.out.println(grammar);
    }
}
