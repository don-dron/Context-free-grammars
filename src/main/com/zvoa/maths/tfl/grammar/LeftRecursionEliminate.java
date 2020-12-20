package com.zvoa.maths.tfl.grammar;

import io.vavr.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

public class LeftRecursionEliminate {
    public static String getNewName(Grammar grammar, NonTerminal nonTerminal) {
        int count = 0;

        while (true) {
            int finalCount = count;
            if (!grammar.getNonTerminals().stream()
                    .anyMatch(nonTerm -> nonTerm.getName().equals(nonTerminal.getName() + finalCount))) {
                break;
            }
            count++;
        }
        return nonTerminal.getName() + count;
    }

    public static Set<NonTerminal> findEpsNonTerminals(Grammar grammar) {
        Set<NonTerminal> result = new LinkedHashSet<>();

        for (NonTerminal nonTerminal : grammar.getNonTerminals()) {
            for (Rule rule : nonTerminal.getRules()) {
                if (rule.getSymbols().isEmpty() ||
                        rule.getSymbols().size() == 1 && rule.getSymbols().get(0) instanceof EpsilonSymbol) {
                    result.add(nonTerminal);
                }
            }
        }

        while (true) {
            boolean stop = true;
            for (NonTerminal nonTerminal : grammar.getNonTerminals()) {
                for (Rule rule : nonTerminal.getRules()) {
                    if (rule.getSymbols().stream().allMatch(symbol -> result.contains(symbol))) {
                        if(result.add(nonTerminal)) {
                            stop = false;
                        }
                    }
                }
            }

            if (stop) {
                break;
            }
        }

        return result;
    }

    public static Grammar removeEpsRules(Grammar grammar) {
        try {
            Grammar copy = grammar.clone();
            List<Terminal> terminals = new ArrayList<>(copy.getTerminals());
            List<NonTerminal> nonTerminals = new ArrayList<>(copy.getNonTerminals());
            List<NonTerminal> newTerminals = new ArrayList<>();
            Set<NonTerminal> epsNonTerminals = findEpsNonTerminals(grammar);

            Grammar withoutEpsilonRules = new Grammar(new LinkedHashSet<>(terminals), new LinkedHashSet<>(nonTerminals));
            return withoutEpsilonRules;
        } catch (CloneNotSupportedException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    public static Grammar eliminateLeftRecursion(Grammar grammar) {
        try {
            Grammar copy = grammar.clone();
            List<Terminal> terminals = new ArrayList<>(copy.getTerminals());
            List<NonTerminal> nonTerminals = new ArrayList<>(copy.getNonTerminals());
            List<NonTerminal> newTerminals = new ArrayList<>();

            for (int i = 0; i < nonTerminals.size(); i++) {
                for (int j = 0; j < i; j++) {
                    Iterator<Rule> ruleIterator = nonTerminals.get(i).getRules().iterator();
                    List<Rule> forAdd = new ArrayList<>();
                    while (ruleIterator.hasNext()) {
                        Rule rule = ruleIterator.next();
                        if (rule.getSymbols().get(0).equals(nonTerminals.get(j))) {
                            ruleIterator.remove();
                            List<Symbol> tail = rule.getSymbols().stream().skip(1).collect(Collectors.toList());

                            for (Rule innerRule : nonTerminals.get(j).getRules()) {
                                List<Symbol> symbols = new ArrayList<>();

                                symbols.addAll(innerRule.getSymbols());
                                symbols.addAll(tail);

                                forAdd.add(new Rule(symbols));
                            }
                        }
                    }
                    nonTerminals.get(i).getRules().addAll(forAdd);
                }

                NonTerminal nonTerminal = nonTerminals.get(i);
                Iterator<Rule> ruleIterator = nonTerminal.getRules().iterator();

                List<Rule> alphaRule = new ArrayList<>();
                List<Rule> betaRule = new ArrayList<>();

                while (ruleIterator.hasNext()) {
                    Rule rule = ruleIterator.next();
                    if (rule.getSymbols().size() >= 2 && rule.getSymbols().get(0).equals(nonTerminal)) {
                        alphaRule.add(new Rule(rule.getSymbols().stream().skip(1).collect(Collectors.toList())));
                        ruleIterator.remove();
                    } else if(rule.getSymbols().size() >= 1 && !rule.getSymbols().get(0).equals(nonTerminal)) {
                        betaRule.add(rule);
                    }
                }

                if(alphaRule.isEmpty()) {
                    continue;
                }

                List<Rule> forNewNonTerminal = new ArrayList<>();

                String newName = getNewName(grammar, nonTerminal);
                alphaRule.forEach(rule -> forNewNonTerminal.add(rule));
                alphaRule.forEach(rule -> {
                    List<Symbol> symbols = new ArrayList<>(rule.getSymbols());
                    symbols.add(new Symbol(newName));
                    forNewNonTerminal.add(new Rule(symbols));
                });

                betaRule.forEach(rule -> {
                    List<Symbol> symbols = new ArrayList<>(rule.getSymbols());
                    symbols.add(new Symbol(newName));
                    nonTerminal.getRules().add(new Rule(symbols));
                });

                NonTerminal newNonTerminal = new NonTerminal(newName, new LinkedHashSet<>(forNewNonTerminal));
                newTerminals.add(newNonTerminal);
            }

            nonTerminals.addAll(newTerminals);

            Grammar withoutLeftRecursion = new Grammar(new LinkedHashSet<>(terminals), new LinkedHashSet<>(nonTerminals));
            return withoutLeftRecursion;
        } catch (CloneNotSupportedException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
