package com.example.taskthree.Models;

public class Ability {
    public AbilitiesItem ability;
    public String is_hidden;
    public int slot;

    public AbilitiesItem getAbility() {
        return ability;
    }

    public void setAbility( AbilitiesItem ability ) {
        this.ability = ability;
    }

    public String getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden( String is_hidden ) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot( int slot ) {
        this.slot = slot;
    }
}
