package com.zvoa.maths.tfl.grammar;

import io.vavr.Tuple2;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public static Grammar removeChainRules(Grammar grammar) {
        try {
            Grammar copy = grammar.clone();
            Set<Terminal> terminals = new LinkedHashSet<>(copy.getTerminals());
            Set<NonTerminal> newNonTerminals = new LinkedHashSet<>(copy.getNonTerminals());

            Set<Tuple2<NonTerminal,NonTerminal>> basis = new LinkedHashSet<>();

            for(NonTerminal nonTerminal : newNonTerminals) {
                basis.add(new Tuple2<>(nonTerminal,nonTerminal));
            }

            while(true) {
                int size = basis.size();
                for(NonTerminal nonTerminal : newNonTerminals) {
                    for(Rule rule : nonTerminal.getRules()) {
                        if(rule.getSymbols().size() == 1 && rule.getSymbols().get(0) instanceof NonTerminal) {
                            // A -> B, it's B
                            NonTerminal rightNonTerminal = (NonTerminal)rule.getSymbols().get(0);

                            // A,A
                            Set<Tuple2<NonTerminal, NonTerminal>> newPairs;
                            do {
                                 newPairs = basis.stream()
                                        .filter(item -> item._2.equals(nonTerminal))
                                        .map(item -> new Tuple2<>(item._1, rightNonTerminal))
                                        .filter(item -> !basis.contains(item))
                                        .collect(Collectors.toSet());
                                 basis.addAll(newPairs);
                            } while (!newPairs.isEmpty());
                        }
                    }
                }

                if(size == basis.size()) {
                    break;
                }
            }

            for(Tuple2<NonTerminal,NonTerminal> pair : basis) {
                NonTerminal left = pair._1;
                NonTerminal right = pair._2;

                left.getRules().addAll(right.getRules().stream().filter(rule ->
                        !(rule.getSymbols().size() == 1 && rule.getSymbols().get(0) instanceof NonTerminal)).collect(Collectors.toList()));
            }

            newNonTerminals.forEach(nonTerminal -> nonTerminal.getRules().removeIf(rule-> rule.getSymbols().size() == 1 &&
                    rule.getSymbols().get(0) instanceof NonTerminal));

            return new Grammar(terminals, newNonTerminals);
        } catch (CloneNotSupportedException exception) {
            throw new IllegalArgumentException("Cannot clone object");
        }
    }
}
