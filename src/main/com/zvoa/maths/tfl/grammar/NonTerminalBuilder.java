package com.zvoa.maths.tfl.grammar;

import java.util.ArrayList;
import java.util.List;

public class GrammarBuilder {
    private final List<Terminal> terminals;
    private final List<NonTerminal> nonTerminals;

    public GrammarBuilder() {
        this.terminals = new ArrayList<>();
        this.nonTerminals = new ArrayList<>();
    }

    public GrammarBuilder addTerminal(Terminal terminal) {
        terminals.add(terminal);
        return this;
    }

    public GrammarBuilder addNonTerminal(NonTerminal nonTerminal) {
        nonTerminals.add(nonTerminal);
        return this;
    }

    public Grammar get() {
        return new Grammar(terminals, nonTerminals);
    }
}
