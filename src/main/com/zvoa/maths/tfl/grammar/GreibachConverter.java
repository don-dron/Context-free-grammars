package com.zvoa.maths.tfl.grammar;

import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.util.stream.Collectors;

public class GreibachConverter {
    public static Grammar convertToGreibachNF(Grammar grammar) {
        List<Terminal> terminals = new ArrayList<>(grammar.getTerminals());
        List<NonTerminal> nonTerminals = new ArrayList<>(grammar.getNonTerminals());

        while (true) {
            boolean stoped = true;

            for (int i = nonTerminals.size() - 1; i >= 0; i--) {
                for (int j = 0; j < nonTerminals.size(); j++) {
                    if(i == j) {
                        continue;
                    }

                    List<Rule> Aj = new ArrayList<>(nonTerminals.get(j).getRules());
                    Iterator<Rule> iterator = nonTerminals.get(i).getRules().iterator();
                    List<Rule> newRules = new ArrayList<>();

                    while (iterator.hasNext()) {
                        Rule rule = iterator.next();
                        if (rule.getSymbols().size() >= 1
                                && rule.getSymbols().get(0).equals(nonTerminals.get(j))) {
                            for (int k = 0; k < Aj.size(); k++) {
                                Rule deltaRule = Aj.get(k);
                                List<Symbol> symbols = new ArrayList<>();
                                symbols.addAll(deltaRule.getSymbols());
                                symbols.addAll(rule.getSymbols().stream().skip(1).collect(Collectors.toList()));
                                newRules.add(new Rule(symbols));
                            }
                            iterator.remove();
                            stoped = false;
                        }
                    }

                    nonTerminals.get(i).getRules().addAll(newRules);
                }
            }

            if (stoped) {
                break;
            }
        }

        grammar = new Grammar(new LinkedHashSet<>(terminals), new LinkedHashSet<>(nonTerminals));
        return grammar;
    }
}
