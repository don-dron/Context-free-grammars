package com.zvoa.maths.tfl.grammar.tests;

import com.zvoa.maths.tfl.grammar.*;
import com.zvoa.maths.tfl.grammar.Rule;
import org.junit.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class GrammarBuilderTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before com.zvoa.maths.tfl.grammar.cf.tests.CalculatorTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After com.zvoa.maths.tfl.grammar.cf.tests.CalculatorTest.class");
    }

    @Before
    public void initTest() {
        //
    }

    @Test(timeout = 500)
    public void emptyGrammar() {
        Grammar grammar = GrammarFactory.build().endGrammar();
        //
    }

    @Test(timeout = 500)
    public void withOneNonTerminalOneRuleGrammar() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("a")
                            .addSymbol("*")
                            .addSymbol("b")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void withOneNonTerminalMultipleRuleGrammar() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("a")
                            .addSymbol("*")
                            .addSymbol("b")
                        .endRule()
                        .addRule()
                            .addSymbol("c")
                            .addSymbol("+")
                            .addSymbol("d")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void withExternalConstructorGrammar() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("S")
                        .addRule(new Rule(Arrays.asList(new Symbol("a"),new Symbol("+"),new Symbol("b"))))
                        .addRule()
                            .addSymbol("c")
                            .addSymbol("+")
                            .addSymbol("d")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal(new NonTerminal("R",
                            new LinkedHashSet<>(Arrays.asList(
                                    new Rule(Arrays.asList(new Symbol("r"), new Symbol("-"),new Symbol("r"))),
                                    new Rule(Arrays.asList(new Symbol("t"), new Symbol("/"),new Symbol("u")))))))
                .endGrammar();

        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("T")
                            .addSymbol("b")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("T")
                        .addRule()
                            .addSymbol("S")
                            .addSymbol("a")
                        .endRule()
                        .addRule()
                            .addSymbol("T")
                            .addSymbol("a")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

//        Grammar grammar = GrammarFactory
//                .build()
//                    .addNonTerminal("A")
//                        .addRule()
//                            .addSymbol("S")
//                            .addSymbol("a")
//                        .endRule()
//                        .addRule()
//                            .addSymbol("A")
//                            .addSymbol("a")
//                        .endRule()
//                    .endNonTerminal()
//                    .addNonTerminal("S")
//                        .addRule()
//                            .addSymbol("A")
//                            .addSymbol("b")
//                        .endRule()
//                    .endNonTerminal()
//                .endGrammar();
        System.out.println(grammar);
//        LeftRecursionEliminate.eliminateLeftRecursion(grammar);
//        System.out.println(grammar);
    }

    @After
    public void timeStampTest() {
        //
    }
}