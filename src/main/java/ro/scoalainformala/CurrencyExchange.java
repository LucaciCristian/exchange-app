package ro.scoalainformala;


public class CurrencyExchange {
    private String currencyId;
    private double currencyRate;



    public CurrencyExchange(String currencyId, double currencyRate) {
        this.currencyId = currencyId;
        this.currencyRate = currencyRate;
    }


    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }


    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "currencyId='" + currencyId + '\'' +
                ", currencyRate=" + currencyRate +
                '}';
    }

}