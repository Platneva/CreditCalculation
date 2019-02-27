package model;

import java.util.stream.Stream;

/**
 * @author Yar
 */
public enum CreditType {

    ANNUITY(1, "Аннуитет"),
    DIFFERENTIATED(2,"Дифференцированный");

    private int id;
    private String name;

    CreditType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static CreditType ofID(int id){
        return Stream.of(CreditType.values()).filter(val -> val.getId() == id).findFirst().orElse(null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }}
