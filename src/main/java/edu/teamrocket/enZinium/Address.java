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

    public boolean isSKpresent(){
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

    public void send(TokenContract contract, Double enziniums){
        if(enziniums <= this.getBalance()){
            contract.payable(this.getPK(), enziniums);
            this.balance -= enziniums;
        }
    }

    @Override
    public String toString() {
        return "Address [PK=" + getPK() + ", SK=" + getSK() + ", balance=" + getBalance() +" symbol=" + symbol + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((PK == null) ? 0 : PK.hashCode());
        result = prime * result + ((SK == null) ? 0 : SK.hashCode());
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return true;
    }

   
}