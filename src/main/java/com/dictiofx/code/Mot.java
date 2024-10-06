package com.dictiofx.code;

public class Mot {
    private String mot;
    private String def;

    public Mot(String mot, String def){
        this.mot = mot;
        this.def = def;
    }

    public Mot(){
        this.def = "";
        this.mot = "";
    }

    public String getMot(){
        return this.mot;
    }

    public String getDef(){
        return this.def;
    }

    public void setDef(String def){
        this.def = def;
    }

    public void setMot(String mot){
        this.mot = mot;
    }

    public String toTextAreaString(){
        return this.mot + "\n\n" + this.def;
    }

    @Override
    public String toString(){
        return "%%" + this.mot.concat(" @@ ").concat(def);
    }

}
