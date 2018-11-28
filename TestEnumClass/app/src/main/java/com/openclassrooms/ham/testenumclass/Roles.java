package com.openclassrooms.ham.testenumclass;

public abstract class Roles {
    protected String name;
    static final int MUTANT_BASE=0;
    static final int MEDECIN=1;

    public static Class getClass(int i){
        switch (i){
            case MUTANT_BASE:
                return MutantBase.class;
            case MEDECIN:
                return Medecin.class;
            default:
                return Roles.class;
        }
    }

    public String getName(){
        return this.name;
    }
}
