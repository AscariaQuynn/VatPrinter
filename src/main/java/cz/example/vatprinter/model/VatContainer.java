package cz.example.vatprinter.model;

import java.util.List;

public class VatContainer {

    private String details;

    private String version;

    private List<VatRate> rates;

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setRates(List<VatRate> rates) {
        this.rates = rates;
    }

    public List<VatRate> getRates() {
        return rates;
    }
}
