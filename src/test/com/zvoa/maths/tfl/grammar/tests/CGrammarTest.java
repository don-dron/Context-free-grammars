package com.zvoa.maths.tfl.grammar.tests;

import com.zvoa.maths.tfl.grammar.Rule;
import com.zvoa.maths.tfl.grammar.*;
import org.junit.*;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static com.zvoa.maths.tfl.grammar.GreibachConverter.convertToGreibachNF;

public class CGrammarTest {

    public static final String DECLARATION_SPECIFIERS = "declaration_specifiers";
    public static final String DECLARATOR = "declarator";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String EXTERNAL_DECLARATION = "external_declaration";
    public static final String TRANSLATION_UNIT = "translation_unit";
    public static final String DECLARATION = "declaration";
    public static final String JUMP_STATEMENT = "jump_statement";
    public static final String GOTO = "goto";
    public static final String IDENTIFIER = "identifier";
    public static final String SEMICOLON = ";";
    public static final String CONTINUE = "continue";
    public static final String BREAK = "break";
    public static final String RETURN = "return";
    public static final String ITERATION_STATEMENT = "iteration_statement";
    public static final String WHILE = "while";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String EXPRESSION = "expression";
    public static final String STATEMENT = "statement";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String FOR = "for";
    public static final String DO = "do";
    public static final String IF = "if";
    public static final String ELSE = "else";
    public static final String SWITCH = "switch";
    public static final String STATEMENT_LIST = "statement_list";
    public static final String LEFT_FIGURE_BRACKET = "{";
    public static final String RIGHT_FIGURE_BRACKET = "}";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String COLON = ":";
    public static final String CASE = "case";
    public static final String CONSTANT_EXPRESSION = "constant_expression";
    public static final String DEFAULT = "default";
    public static final String SELECTION_STATEMENT = "selection_statement";
    public static final String INITIALIZER_LIST = "initializer_list";
    public static final String INITIALIZER = "initializer";
    public static final String COMMA = ",";
    public static final String ASSIGMENT_EXPRESSION = "assigment_expression";
    public static final String DIRECT_ABSTRACT_DECLARATOR = "direct_abstract_declarator";
    public static final String ABSTRACT_DECLARATOR = "abstract_declarator";
    public static final String LEFT_SQUARE_BRACKET = "[";
    public static final String RIGHT_SQUARE_BRACKET = "]";
    public static final String PARAMETER_TYPE_LIST = "parameter_type_list";
    public static final String POINTER = "pointer";
    public static final String TYPE_NAME = "type_name";
    public static final String SPECIFIER_QUALIFIER_LIST = "specifier_qualifier_list";
    public static final String IDENTIFIER_LIST = "identifier_list";
    public static final String PARAMETER_DECLARATION = "parameter_declaration";
    public static final String PARAMETER_LIST = "parameter_list";
    public static final String ELLIPSIS = "...";
    public static final String TYPE_QUALIFIER_LIST = "type_qualifier_list";
    public static final String TYPE_QUALIFIER = "type_qualifier";

    @Test
    public void CFullGrammarTest() {
        Grammar grammar = GrammarFactory
                .build()

                .addNonTerminal(FUNCTION_DEFINITION)
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(DECLARATOR)
                        .addSymbol(DECLARATION_LIST)
                        .addSymbol(COMPOUND_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(DECLARATOR)
                        .addSymbol(COMPOUND_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(DECLARATION_LIST)
                        .addSymbol(COMPOUND_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(COMPOUND_STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(EXTERNAL_DECLARATION)
                    .addRule()
                        .addSymbol(FUNCTION_DEFINITION)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(TRANSLATION_UNIT)
                    .addRule()
                        .addSymbol(EXTERNAL_DECLARATION)
                    .endRule()
                    .addRule()
                        .addSymbol(TRANSLATION_UNIT)
                        .addSymbol(EXTERNAL_DECLARATION)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(JUMP_STATEMENT)
                    .addRule()
                        .addSymbol(GOTO)
                        .addSymbol(IDENTIFIER)
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(CONTINUE)
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(BREAK)
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(RETURN)
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(RETURN)
                        .addSymbol(IDENTIFIER)
                        .addSymbol(SEMICOLON)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(ITERATION_STATEMENT)
                    .addRule()
                        .addSymbol(WHILE)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(DO)
                        .addSymbol(STATEMENT)
                        .addSymbol(WHILE)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(FOR)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION_STATEMENT)
                        .addSymbol(EXPRESSION_STATEMENT)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(FOR)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION_STATEMENT)
                        .addSymbol(EXPRESSION_STATEMENT)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(SELECTION_STATEMENT)
                    .addRule()
                        .addSymbol(IF)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(IF)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                        .addSymbol(ELSE)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(SWITCH)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(EXPRESSION)
                        .addSymbol(RIGHT_BRACKET)
                        .addSymbol(STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(EXPRESSION_STATEMENT)
                    .addRule()
                        .addSymbol(SEMICOLON)
                    .endRule()
                    .addRule()
                        .addSymbol(EXPRESSION)
                    .addSymbol(SEMICOLON)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(STATEMENT_LIST)
                    .addRule()
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(STATEMENT_LIST)
                        .addSymbol(STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(DECLARATION_LIST)
                    .addRule()
                        .addSymbol(DECLARATION)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_LIST)
                        .addSymbol(DECLARATION)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(COMPOUND_STATEMENT)
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(STATEMENT_LIST)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(DECLARATION_LIST)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(DECLARATION_LIST)
                        .addSymbol(STATEMENT_LIST)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(LABELED_STATEMENT)
                    .addRule()
                        .addSymbol(IDENTIFIER)
                        .addSymbol(COLON)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(CASE)
                        .addSymbol(CONSTANT_EXPRESSION)
                        .addSymbol(COLON)
                        .addSymbol(STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(DEFAULT)
                        .addSymbol(COLON)
                        .addSymbol(STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(STATEMENT)
                    .addRule()
                        .addSymbol(LABELED_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(COMPOUND_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(EXPRESSION_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(SELECTION_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(ITERATION_STATEMENT)
                    .endRule()
                    .addRule()
                        .addSymbol(JUMP_STATEMENT)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(INITIALIZER_LIST)
                    .addRule()
                        .addSymbol(INITIALIZER)
                    .endRule()
                    .addRule()
                        .addSymbol(INITIALIZER_LIST)
                        .addSymbol(COMMA)
                        .addSymbol(INITIALIZER)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(INITIALIZER)
                    .addRule()
                        .addSymbol(ASSIGMENT_EXPRESSION)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(INITIALIZER_LIST)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_FIGURE_BRACKET)
                        .addSymbol(INITIALIZER_LIST)
                        .addSymbol(COMMA)
                        .addSymbol(RIGHT_FIGURE_BRACKET)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(DIRECT_ABSTRACT_DECLARATOR)
                    .addRule()
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(RIGHT_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(ABSTRACT_DECLARATOR)
                        .addSymbol(RIGHT_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(PARAMETER_TYPE_LIST)
                        .addSymbol(RIGHT_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(RIGHT_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                        .addSymbol(LEFT_BRACKET)
                        .addSymbol(PARAMETER_TYPE_LIST)
                        .addSymbol(RIGHT_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_SQUARE_BRACKET)
                        .addSymbol(RIGHT_SQUARE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(LEFT_SQUARE_BRACKET)
                        .addSymbol(CONSTANT_EXPRESSION)
                        .addSymbol(RIGHT_SQUARE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                        .addSymbol(LEFT_SQUARE_BRACKET)
                        .addSymbol(RIGHT_SQUARE_BRACKET)
                    .endRule()
                    .addRule()
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                        .addSymbol(LEFT_SQUARE_BRACKET)
                        .addSymbol(CONSTANT_EXPRESSION)
                        .addSymbol(RIGHT_SQUARE_BRACKET)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(ABSTRACT_DECLARATOR)
                    .addRule()
                        .addSymbol(POINTER)
                    .endRule()
                    .addRule()
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                    .endRule()
                    .addRule()
                        .addSymbol(POINTER)
                        .addSymbol(DIRECT_ABSTRACT_DECLARATOR)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(TYPE_NAME)
                    .addRule()
                        .addSymbol(SPECIFIER_QUALIFIER_LIST)
                    .endRule()
                    .addRule()
                        .addSymbol(SPECIFIER_QUALIFIER_LIST)
                        .addSymbol(ABSTRACT_DECLARATOR)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(IDENTIFIER_LIST)
                    .addRule()
                        .addSymbol(IDENTIFIER)
                    .endRule()
                    .addRule()
                        .addSymbol(IDENTIFIER_LIST)
                        .addSymbol(COMMA)
                        .addSymbol(IDENTIFIER)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(PARAMETER_DECLARATION)
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(DECLARATOR)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                        .addSymbol(ABSTRACT_DECLARATOR)
                    .endRule()
                    .addRule()
                        .addSymbol(DECLARATION_SPECIFIERS)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(PARAMETER_LIST)
                    .addRule()
                        .addSymbol(PARAMETER_DECLARATION)
                    .endRule()
                    .addRule()
                        .addSymbol(PARAMETER_LIST)
                        .addSymbol(COMMA)
                        .addSymbol(PARAMETER_DECLARATION)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(PARAMETER_TYPE_LIST)
                    .addRule()
                        .addSymbol(PARAMETER_LIST)
                    .endRule()
                    .addRule()
                        .addSymbol(PARAMETER_LIST)
                        .addSymbol(COMMA)
                        .addSymbol(ELLIPSIS)
                    .endRule()
                .endNonTerminal()

                .addNonTerminal(TYPE_QUALIFIER_LIST)
                    .addRule()
                        .addSymbol(TYPE_QUALIFIER)
                    .endRule()
                    .addRule()
                        .addSymbol(TYPE_QUALIFIER_LIST)
                        .addSymbol(TYPE_QUALIFIER)
                    .endRule()
                .endNonTerminal()

                .endGrammar();

        System.out.println(grammar);
    }

    @After
    public void timeStampTest() {
        //
    }
}