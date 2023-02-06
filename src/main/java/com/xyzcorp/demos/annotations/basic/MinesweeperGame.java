package com.xyzcorp.demos.annotations.basic;

@AuthoredBy(firstName = "Sarah", lastName = "Connelly")
public class MinesweeperGame {
    private Dimension dimension;

    public MinesweeperGame(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
