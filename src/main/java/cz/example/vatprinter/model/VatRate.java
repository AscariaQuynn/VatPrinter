package cz.example.vatprinter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class VatRate {

    private String name;

    private String code;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("periods")
    private List<VatPeriod> periodList;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setPeriodList(List<VatPeriod> periodList) {
        this.periodList = periodList;
    }

    public List<VatPeriod> getPeriodList() {
        return periodList;
    }

    @JsonIgnore
    public VatPeriod getLatestPeriod() throws UnsupportedOperationException {
        if(periodList.size() <= 0) {
            throw new UnsupportedOperationException("Cannot obtain latest period without any period specified.");
        }
        VatPeriod actualPeriod = periodList.get(0);
        for(VatPeriod period : periodList) {
            if(period.getEffectiveFrom().compareTo(actualPeriod.getEffectiveFrom()) > 0) {
                    actualPeriod = period;
                }
            }
        return actualPeriod;
    }

    @JsonIgnore
    public BigDecimal getStandardRate() throws UnsupportedOperationException {
        return getLatestPeriod().getStandardRate();
    }
}
