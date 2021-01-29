package com.wks;

import java.util.Objects;

public class Character {

    public static final Character whitespace = new Character(' ');
    public static final Character zero = new Character('0');

    private final String character;

    public Character(char character) {
        this.character = String.valueOf(character);
    }

    public String repeat(int count) {
        return new String(new char[count]).replace("\0", this.character);
    }

    @Override
    public String toString() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character1 = (Character) o;
        return Objects.equals(character, character1.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }
}
