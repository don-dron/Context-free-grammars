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
    public void eliminateLeftRecursiveTest1() {
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

        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest2() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("A")
                        .addRule()
                            .addSymbol("S")
                            .addSymbol("a")
                        .endRule()
                        .addRule()
                            .addSymbol("A")
                            .addSymbol("a")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("A")
                            .addSymbol("b")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest3() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("Expression")
                        .addRule()
                            .addSymbol("Expression")
                            .addSymbol("-")
                            .addSymbol("Term")
                        .endRule()
                        .addRule()
                            .addSymbol("Term")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("Term")
                        .addRule()
                            .addSymbol("Term")
                            .addSymbol("*")
                            .addSymbol("Factor")
                        .endRule()
                        .addRule()
                            .addSymbol("Factor")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("Factor")
                        .addRule()
                            .addSymbol("(")
                            .addSymbol("Expression")
                            .addSymbol(")")
                        .endRule()
                        .addRule()
                            .addSymbol("Integer")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest4() {
        Grammar grammar = GrammarFactory
        .build()
            .addNonTerminal("E")
                .addRule()
                    .addSymbol("E")
                    .addSymbol("+")
                    .addSymbol("T")
                .endRule()
                .addRule()
                    .addSymbol("T")
                .endRule()
            .endNonTerminal()
            .addNonTerminal("T")
                .addRule()
                    .addSymbol("T")
                    .addSymbol("*")
                    .addSymbol("F")
                .endRule()
                .addRule()
                    .addSymbol("F")
                .endRule()
            .endNonTerminal()
            .addNonTerminal("F")
                .addRule()
                    .addSymbol("(")
                    .addSymbol("E")
                    .addSymbol(")")
                .endRule()
                .addRule()
                    .addSymbol("a")
                .endRule()
            .endNonTerminal()
        .endGrammar();
        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest5() {
        Grammar grammar = GrammarFactory
            .build()
                .addNonTerminal("R")
                    .addRule()
                        .addSymbol("a")
                    .endRule()
                    .addRule()
                        .addSymbol("R")
                        .addSymbol("R")
                    .endRule()
                    .addRule()
                        .addSymbol("R")
                        .addSymbol("*")
                    .endRule()
                    .addRule()
                        .addSymbol("R")
                        .addSymbol("\"")
                    .endRule()
                    .addRule()
                        .addSymbol("\"")
                        .addSymbol("R")
                    .endRule()
                    .addRule()
                        .addSymbol("(")
                        .addSymbol("R")
                        .addSymbol(")")
                    .endRule()
//                    .addRule(new Rule(Arrays.asList(new EpsilonSymbol())))
                .endNonTerminal()
            .endGrammar();
        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest6() {
        Grammar grammar = GrammarFactory
            .build()
                .addNonTerminal("A")
                    .addRule()
                        .addSymbol("B")
                    .endRule()
                    .addRule()
                        .addSymbol("a")
                    .endRule()
                .endNonTerminal()
                .addNonTerminal("B")
                    .addRule()
                        .addSymbol("C")
                    .endRule()
                    .addRule()
                        .addSymbol("b")
                    .endRule()
                .endNonTerminal()
                .addNonTerminal("C")
                    .addRule()
                        .addSymbol("dd")
                    .endRule()
                    .addRule()
                        .addSymbol("c")
                    .endRule()
                .endNonTerminal()
            .endGrammar();
        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test(timeout = 500)
    public void eliminateLeftRecursiveTest7() {
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
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test
    public void eliminateLeftRecursiveTest8() {
        Grammar grammar = GrammarFactory
                .build()
                    .addNonTerminal("S")
                        .addRule()
                            .addSymbol("T")
                            .addSymbol("b")
                        .endRule()
                        .addRule()
                            .addSymbol("a")
                        .endRule()
                    .endNonTerminal()
                    .addNonTerminal("T")
                        .addRule()
                            .addSymbol("S")
                            .addSymbol("a")
                        .endRule()
                        .addRule()
                            .addSymbol("b")
                        .endRule()
                    .endNonTerminal()
                .endGrammar();

        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @Test
    public void CGrammarTest() {
        Grammar grammar = GrammarFactory
                .build()

                .addNonTerminal("Программа")
                    .addRule()
                        .addSymbol("Команда")
                        .addSymbol("Программа")
                    .endRule()
                    .addRule(new Rule(Arrays.asList(new EpsilonSymbol())))
                .endNonTerminal()

                .addNonTerminal("Команда")
                    .addRule()
                        .addSymbol("СоставнаяКоманда")
                    .endRule()
                    .addRule()
                        .addSymbol("КомандаВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("КомандаВыбора")
                    .endRule()
                    .addRule()
                        .addSymbol("ЦиклическаяКоманда")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("СоставнаяКоманда")
                    .addRule()
                        .addSymbol("SEP")
                        .addSymbol("СписокОбъявлений")
                        .addSymbol("СписокКоманд")
                        .addSymbol("SEP")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("СписокОбъявлений")
                    .addRule()
                        .addSymbol("Объявление")
                        .addSymbol("СписокОбъявлений")
                    .endRule()
                    .addRule(new Rule(Arrays.asList(new EpsilonSymbol())))
                .endNonTerminal()

                .addNonTerminal("СписокКоманд")
                    .addRule()
                        .addSymbol("Команда")
                        .addSymbol("СписокКоманд")
                    .endRule()
                    .addRule(new Rule(Arrays.asList(new EpsilonSymbol())))
                .endNonTerminal()

                .addNonTerminal("КомандаВыражение")
                    .addRule()
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("КомандаВыбора")
                    .addRule()
                        .addSymbol("KW")
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                        .addSymbol("Команда")
                    .endRule()
                    .addRule()
                        .addSymbol("KW")
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                        .addSymbol("Команда")
                        .addSymbol("KW")
                        .addSymbol("Команда")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ЦиклическаяКоманда")
                    .addRule()
                        .addSymbol("KW")
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                        .addSymbol("Команда")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("Объявление")
                    .addRule()
                        .addSymbol("KW")
                        .addSymbol("SEP")
                        .addSymbol("СписокИницОбъявителей")
                        .addSymbol("SEP")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("СписокИницОбъявителей")
                    .addRule()
                        .addSymbol("ИницОбъявитель")
                    .endRule()
                    .addRule()
                        .addSymbol("ИницОбъявитель")
                        .addSymbol("SEP")
                        .addSymbol("СписокИницОбъявителей")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ИницОбъявитель")
                    .addRule()
                        .addSymbol("Объявитель")
                    .endRule()
                    .addRule()
                        .addSymbol("Объявитель")
                        .addSymbol("OP")
                        .addSymbol("Инициализатор")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("Объявитель")
                    .addRule()
                        .addSymbol("ID")
                    .endRule()
                    .addRule()
                        .addSymbol("OP")
                        .addSymbol("ID")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("Инициализатор")
                    .addRule()
                        .addSymbol("ВыражениеПрисваивания")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("Выражение")
                    .addRule()
                        .addSymbol("ВыражениеПрисваивания")
                    .endRule()
                    .addRule(new Rule(Arrays.asList(new EpsilonSymbol())))
                .endNonTerminal()

                .addNonTerminal("ВыражениеПрисваивания")
                    .addRule()
                        .addSymbol("УсловноеВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("УнарноеВыражение")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеПрисваивания")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("УсловноеВыражение")
                    .addRule()
                        .addSymbol("ВыражениеЛогическогоИЛИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеЛогическогоИЛИ")
                    .addRule()
                        .addSymbol("ВыражениеЛогическогоИ")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеЛогическогоИ")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеЛогическогоИЛИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеЛогическогоИ")
                    .addRule()
                        .addSymbol("ВыражениеИЛИ")
                    .endRule()
                        .addRule()
                        .addSymbol("ВыражениеИЛИ")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеЛогическогоИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеИЛИ")
                    .addRule()
                        .addSymbol("ВыражениеИсключающегоИЛИ")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеИсключающегоИЛИ")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеИЛИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеИсключающегоИЛИ")
                    .addRule()
                        .addSymbol("ВыражениеИ")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеИ")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеИсключающегоИЛИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеИ")
                    .addRule()
                        .addSymbol("ВыражениеРавенства")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеРавенства")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеИ")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеРавенства")
                    .addRule()
                        .addSymbol("ВыражениеОтношения")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеОтношения")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеРавенства")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеОтношения")
                    .addRule()
                        .addSymbol("ВыражениеСдвига")
                    .endRule()
                    .addRule()
                        .addSymbol("ВыражениеСдвига")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеОтношения")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ВыражениеСдвига")
                    .addRule()
                        .addSymbol("АддитивноеВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("АддитивноеВыражение")
                        .addSymbol("OP")
                        .addSymbol("ВыражениеСдвига")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("АддитивноеВыражение")
                    .addRule()
                        .addSymbol("МультипликативноеВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("МультипликативноеВыражение")
                        .addSymbol("OP")
                        .addSymbol("АддитивноеВыражение")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("МультипликативноеВыражение")
                    .addRule()
                        .addSymbol("УнарноеВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("УнарноеВыражение")
                        .addSymbol("OP")
                        .addSymbol("МультипликативноеВыражение")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("УнарноеВыражение")
                    .addRule()
                        .addSymbol("ПервичноеВыражение")
                    .endRule()
                    .addRule()
                        .addSymbol("ПервичноеВыражение")
                        .addSymbol("OP")
                    .endRule()
                    .addRule()
                        .addSymbol("OP")
                        .addSymbol("УнарноеВыражение")
                    .endRule()
                .endNonTerminal()

                .addNonTerminal("ПервичноеВыражение")
                    .addRule()
                        .addSymbol("ID")
                    .endRule()
                    .addRule()
                        .addSymbol("CONST")
                    .endRule()
                    .addRule()
                        .addSymbol("STR")
                    .endRule()
                    .addRule()
                        .addSymbol("SEP")
                        .addSymbol("Выражение")
                        .addSymbol("SEP")
                    .endRule()
                .endNonTerminal()

                .endGrammar();

        System.out.println(grammar);
        grammar = LeftRecursionEliminate.eliminateLeftRecursion(grammar);
        System.out.println(grammar);
    }

    @After
    public void timeStampTest() {
        //
    }
}