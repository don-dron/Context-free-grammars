package com.zvoa.maths.tfl.grammar;

import java.util.*;

public class RemoveUselessSymbols {
    public static Grammar removeUnreachableSymbols(Grammar grammar) {
        Set<Terminal> terminals = new LinkedHashSet<>();
        Set<NonTerminal> newNonTerminals = new LinkedHashSet<>();

        Stack<NonTerminal> nonTerminalStack = new Stack<>();
        nonTerminalStack.push(grammar.getNonTerminals().iterator().next());

        while (!nonTerminalStack.isEmpty()) {
            NonTerminal current = nonTerminalStack.pop();

            if (newNonTerminals.contains(current)) {
                continue;
            }

            newNonTerminals.add(current);

            for (Rule rule : current.getRules()) {
                for (Symbol symbol : rule.getSymbols()) {
                    if (symbol instanceof NonTerminal) {
                        nonTerminalStack.push((NonTerminal) symbol);
                    } else if (symbol instanceof Terminal) {
                        terminals.add((Terminal) symbol);
                    } else {
                        throw new IllegalArgumentException("Unknown symbol type");
                    }
                }
            }
        }

        return new Grammar(terminals, newNonTerminals);
    }

    public static Grammar removeNonGeneratorSymbols(Grammar grammar) {
        try {
            Set<Symbol> checked = new LinkedHashSet<>();
            checked.addAll(grammar.getTerminals());

            while (true) {
                boolean flag = true;

                for (NonTerminal current : grammar.getNonTerminals()) {
                    if (checked.contains(current)) {
                        continue;
                    }

                    for (Rule rule : current.getRules()) {
                        for (Symbol symbol : rule.getSymbols()) {
                            if (checked.contains(symbol)) {
                                checked.add(current);
                                flag = false;
                                break;
                            }
                        }
                    }
                }

                if (flag) {
                    break;
                }
            }

            Grammar copy = grammar.clone();

            Set<Terminal> terminals = new LinkedHashSet<>(copy.getTerminals());
            Set<NonTerminal> newNonTerminals = new LinkedHashSet<>(copy.getNonTerminals());

            newNonTerminals.removeIf(symbol -> !checked.contains(symbol));

            newNonTerminals
                    .forEach(nonTerminal -> nonTerminal
                            .getRules()
                            .removeIf(rule -> rule.getSymbols().stream()
                                    .anyMatch(symbol -> !checked.contains(symbol))));

            return new Grammar(terminals, newNonTerminals);
        } catch (CloneNotSupportedException exception) {
            throw new IllegalArgumentException("Cannot clone object");
        }
    }
}
