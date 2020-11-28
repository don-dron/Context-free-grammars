package com.zvoa.maths.tfl.grammar;

public class Terminal extends Symbol {
    public Terminal(String name) {
        super(name);
    }

    public Terminal(Terminal terminal) {
        super(terminal.getName());
    }
}
