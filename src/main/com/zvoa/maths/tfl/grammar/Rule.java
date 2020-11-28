package com.zvoa.maths.tfl.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Rule implements Cloneable {
    public static final String SYMBOLS_DELIMITER = "";
    private final List<Symbol> symbols;

    public Rule(List<Symbol> symbols) {
        this.symbols = new ArrayList<>(symbols);
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public Rule clone() throws CloneNotSupportedException {
        List<Symbol> symbols = new ArrayList<>();
        this.symbols.forEach(symbol -> {
            symbols.add(new Symbol(symbol.getName()));
        });
        return new Rule(symbols);
    }

    public String toString() {
        return symbols.stream().map(symbol -> symbol.getName()).collect(Collectors.joining(SYMBOLS_DELIMITER));
    }
}
