package com.zvoa.maths.tfl.grammar;

import java.util.*;
import java.util.stream.Collectors;

public class NonTerminal extends Symbol {
    public static final String NAME_DELIMITER = " -> ";
    public static final String RULES_DELIMITER = " | ";
    private final Set<Rule> rules;

    public NonTerminal(String name, Set<Rule> rules) {
        super(name);
        this.rules = new LinkedHashSet<>(rules);
    }

    public Set<Rule> getRules() {
        return rules;
    }

    @Override
    public NonTerminal clone() throws CloneNotSupportedException {
        Set<Rule> rules = new LinkedHashSet<>();
        this.getRules().forEach(rule -> {
            try {
                rules.add(rule.clone());
            } catch (CloneNotSupportedException e) {
                throw new NullPointerException("Cannot build copy");
            }
        });
        return new NonTerminal(getName().repeat(1),rules);
    }

    public String toString() {
        return super.getName() + NAME_DELIMITER + rules.stream().map(Objects::toString).collect(Collectors.joining(RULES_DELIMITER));
    }
}
