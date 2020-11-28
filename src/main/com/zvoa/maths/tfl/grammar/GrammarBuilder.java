package com.zvoa.maths.tfl.grammar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GrammarBuilder {
    private final Set<Terminal> terminals;
    private final Set<NonTerminal> nonTerminals;

    public GrammarBuilder() {
        this.terminals = new LinkedHashSet<>();
        this.nonTerminals = new LinkedHashSet<>();
    }

    public GrammarBuilder addTerminal(String name) {
        terminals.add(new Terminal(name));
        return this;
    }

    public GrammarBuilder addTerminal(Terminal terminal) {
        terminals.add(terminal);
        return this;
    }

    public GrammarBuilder addNonTerminal(NonTerminal nonTerminal) {
        nonTerminals.add(nonTerminal);
        return this;
    }

    public NonTerminalBuilder addNonTerminal(String name) {
        return new NonTerminalBuilder(name,this);
    }

    public Grammar endGrammar() {
        return new Grammar(terminals, nonTerminals);
    }
}
