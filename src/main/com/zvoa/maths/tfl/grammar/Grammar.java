package com.zvoa.maths.tfl.grammar;

import java.util.*;
import java.util.stream.Collectors;

public class Grammar implements Cloneable {
    public static final String TERMINALS_DELIMITER = ",";
    public static final String NON_TERMINALS_DELIMITER = "\n";
    private final Set<Terminal> terminals;
    private final Set<NonTerminal> nonTerminals;

    public Grammar(Set<Terminal> terminals, Set<NonTerminal> nonTerminals) {
        this.terminals = new LinkedHashSet<>(terminals);
        this.nonTerminals = new LinkedHashSet<>(nonTerminals);

        findDependencies();
    }

    public Set<NonTerminal> getNonTerminals() {
        return nonTerminals;
    }

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public String toString() {
        String terminalsString = terminals.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(TERMINALS_DELIMITER));

        String nonTerminalsString = nonTerminals.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(NON_TERMINALS_DELIMITER));

        return "Terminals:\n" + terminalsString + "\nNonTerminals:\n" + nonTerminalsString;
    }

    private void findDependencies() {
        for (NonTerminal nonTerminal : nonTerminals) {
            for (Rule rule : nonTerminal.getRules()) {
                rule.getSymbols().replaceAll(symbol -> {
                    Symbol found = terminals.stream()
                            .map(terminal -> (Symbol) terminal)
                            .filter(terminal -> terminal.equals(symbol))
                            .findFirst()
                            .orElse(nonTerminals.stream()
                                    .filter(basicNonTerminal -> basicNonTerminal.equals(symbol))
                                    .findFirst()
                                    .orElse(null));
                    if (found == null) {
                        found = new Terminal(symbol.getName());
                        terminals.add((Terminal) found);
                    }
                    return found;
                });

                if(rule.getSymbols().contains(new EpsilonSymbol()) && rule.getSymbols().size() > 1) {
                    rule.getSymbols().remove(new EpsilonSymbol());
                }
            }
        }
    }

    @Override
    public Grammar clone() throws CloneNotSupportedException {
        Set<Terminal> terminals = new LinkedHashSet<>();
        Set<NonTerminal> nonTerminals = new LinkedHashSet<>();

        this.terminals.forEach(terminal -> {
            terminals.add(new Terminal(terminal));
        });

        this.nonTerminals.forEach(nonTerminal -> {
            try {
                nonTerminals.add(nonTerminal.clone());
            } catch (CloneNotSupportedException e) {
                throw new NullPointerException("Cannot build copy");
            }
        });

        return new Grammar(terminals, nonTerminals);
    }
}
