package edu.teamrocket.enZinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;


public class TokenContract{ 
    private PublicKey ownerPK;
    private Address owner;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
    private Double totalTokensSold = 0d;
    private Double tokenPrice = 0d;

    private final Map<PublicKey, Double> balances = new HashMap<>();

    public TokenContract(Address owner){
        this.owner = owner;
        this.ownerPK = owner.getPK();
    }


    void setName(String name){
        this.name = name;
    }

    void setSymbol (String symbol){
        this.symbol = symbol;
    }

    void setTotalSupply(double totalSupply){
        this.totalSupply = totalSupply;
    }

    

    public Address owner(){
        return this.owner;
    }

    public String name(){
        return this.name;
    }
    public String symbol(){
        return this.symbol;
    }
    public double totalSupply(){
        return this.totalSupply;
    }
    public Map<PublicKey, Double> getBalances(){
        return this.balances;
    }

    public Double getTokenPrice() {
        return this.tokenPrice;
    }

    void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }   


    @Override
    public String toString() {
        return "TokenContract [ownerPK=" + ownerPK + ", name=" + name + ", symbol=" + symbol + ", totalSupply="
                + totalSupply + ", totalTokensSold=" + totalTokensSold + ", tokenPrice=" + tokenPrice + ", balances="
                + balances + "]";
    }

    public int numOwners(){
        return this.balances.size();
    }

    void require (Boolean holds)throws InsufficientTokensException{
        if(!holds){
            throw new InsufficientTokensException();
        }
    }

    public String owners(){
        StringBuilder owners = new StringBuilder();
        for (PublicKey pk : this.getBalances().keySet()) {
           if (this.getBalances().get(pk) > 0d){
                owners.append(pk.toString()).append("\n");
           }
        }
        return owners.toString();

    }

    public int totalTokensSold(){
        return this.getBalances().size();
    }

    void addOwner(PublicKey PK, Double units){
        this.balances.put(PK, units);
    }

    public Double balanceOf(PublicKey owner){
        return this.getBalances().getOrDefault(owner, 0d);
    }

    public void transfer (PublicKey recipient, Double units){
        try {
            require(balanceOf(ownerPK) >= units);
            this.getBalances().compute(ownerPK, (pk, tokens) -> tokens - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);

        } catch (InsufficientTokensException e) {
        }
    }

    public void transfer(PublicKey sender, PublicKey recipient, Double units){
        try {
            require(balanceOf(sender) >= units);
            this.getBalances().compute(sender, (pk, tokens) -> tokens - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (InsufficientTokensException e) {
        }

    }

    void payable (PublicKey recipient, Double enziniums){
        try {
            require(enziniums >= this.getTokenPrice());
            Double units = Math.floor(enziniums / tokenPrice);
            transfer(recipient, units);
            this.owner.transferEZI(enziniums);

        } catch (InsufficientTokensException e) {
        }
    }
}