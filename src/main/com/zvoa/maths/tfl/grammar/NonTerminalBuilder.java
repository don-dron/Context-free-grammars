package com.zvoa.maths.tfl.grammar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NonTerminalBuilder {
    private final String name;
    private final Set<Rule> rules;
    private final GrammarBuilder parentBuilder;

    public NonTerminalBuilder(String name, GrammarBuilder parentBuilder) {
        this.parentBuilder = parentBuilder;
        this.name = name;
        this.rules = new LinkedHashSet<>();
    }

    public RuleBuilder addRule() {
        RuleBuilder ruleBuilder = new RuleBuilder(this);
        return ruleBuilder;
    }

    public NonTerminalBuilder addRule(Rule rule) {
        rules.add(rule);
        return this;
    }

    public GrammarBuilder endNonTerminal() {
        parentBuilder.addNonTerminal(new NonTerminal(name, rules));
        return parentBuilder;
    }
}
