package com.zvoa.maths.tfl.grammar;

import java.util.Objects;

public class Symbol implements Cloneable {
    private String name;

    public Symbol(String name) {
        assert (name != null);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Symbol symbol = (Symbol) o;
        return name.equals(symbol.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString() {
        return name;
    }
}
