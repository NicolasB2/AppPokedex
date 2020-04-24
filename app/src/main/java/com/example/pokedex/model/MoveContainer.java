package com.example.pokedex.model;

public class MoveContainer {

    private Move move;
    //private Prop version_group_details


    public MoveContainer(Move move) {
        this.move = move;
    }

    public MoveContainer() {
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
