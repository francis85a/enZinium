package edu.teamrocket.enZinium;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private final String symbol = "EZI";


    public Address(){
    }

    void setPK (PublicKey PK){
        this.PK = PK; 
    }

    PublicKey getPK(){
        return this.PK;
    }

    void setSK (PrivateKey SK){
        this.SK = SK;
    }

    PrivateKey getSK() {
        return this.SK;
    }

    void setBalance(double balance){
        this.balance = balance;
    }

    double getBalance(){
        return this.balance;
    }

    boolean isSKPresent(){
        return this.getSK() != null;
    }

    public void generateKeyPair(){
        KeyPair pair = GenSig.generateKeyPair();
        this.setPK(pair.getPublic());
        this.setSK(pair.getPrivate());

    }

    void transferEZI (double enziniums){
        this.balance += enziniums;
    }

    
}