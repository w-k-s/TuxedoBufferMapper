package com.wks;

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
}
