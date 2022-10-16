package com.teraninja.guard.Model;

import java.util.ArrayList;

public class ModelguardAcceptOffer {
    public int guardsCount;
    public ArrayList<ModelguardAccept> guards;

    public int getGuardsCount() {
        return guardsCount;
    }

    public void setGuardsCount(int guardsCount) {
        this.guardsCount = guardsCount;
    }

    public ArrayList<ModelguardAccept> getGuards() {
        return guards;
    }

    public void setGuards(ArrayList<ModelguardAccept> guards) {
        this.guards = guards;
    }
}
