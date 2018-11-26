package cz.example.vatprinter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.HashMap;

public class VatPeriod {

    @JsonProperty("effective_from")
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate effectiveFrom;

    @JsonProperty("rates")
    private HashMap<String, BigDecimal> rateList;

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setRateList(HashMap<String, BigDecimal> rateList) {
        this.rateList = rateList;
    }

    public HashMap<String, BigDecimal> getRateList() {
        return rateList;
    }

    public BigDecimal getStandardRate() {
        BigDecimal standardRate = rateList.get("standard");
        if(null == standardRate) {
            throw new IllegalStateException("Standard Rate is missing!");
        }
        return standardRate;
    }
}
