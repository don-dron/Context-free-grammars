package com.zvoa.maths.tfl.grammar;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private final List<Symbol> symbols;
    private final NonTerminalBuilder parentBuilder;

    public RuleBuilder(NonTerminalBuilder parentBuilder) {
        this.parentBuilder = parentBuilder;
        this.symbols = new ArrayList<>();
    }

    public RuleBuilder addSymbol(String name) {
        symbols.add(new Symbol(name));
        return this;
    }

    public NonTerminalBuilder endRule() {
        parentBuilder.addRule(new Rule(symbols));
        return parentBuilder;
    }
}
