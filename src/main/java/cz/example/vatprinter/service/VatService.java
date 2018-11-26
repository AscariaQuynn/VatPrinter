package cz.example.vatprinter.service;

import cz.example.vatprinter.enums.LexicographicOrder;
import cz.example.vatprinter.enums.VatRateOrder;
import cz.example.vatprinter.model.VatContainer;
import cz.example.vatprinter.model.VatRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@Service
public class VatService {

    public static final String URL = "http://jsonvat.com";

    @Autowired
    private RestTemplate restTemplate;

    public List<VatRate> findRates() {
        ResponseEntity<VatContainer> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<VatContainer>() { });
        VatContainer vatContainer = responseEntity.getBody();
        return vatContainer.getRates();
    }

    public List<VatRate> findRates(int resultsCount, VatRateOrder vatRateOrder, LexicographicOrder countryNameOrder) {
        List<VatRate> rates = findRates();
        rates.sort(createComparator(vatRateOrder, countryNameOrder));
        return rates.subList(0, resultsCount);
    }

    private Comparator<VatRate> createComparator(VatRateOrder vatRateOrder, LexicographicOrder countryNameOrder) {
        // Basic comparator
        Comparator<VatRate> comparator = Comparator.comparing(VatRate::getStandardRate);
        // Highest or lowest VAT first, lowest is default so highest is reversed
        if(vatRateOrder == VatRateOrder.HIGHEST_FIRST) {
            comparator = comparator.reversed();
        }
        // Basic country name comparator
        comparator = comparator.thenComparing(VatRate::getName);
        // Alphabetical or reverse order, natural is default, so only change for reversed
        if(countryNameOrder == LexicographicOrder.REVERSE_ALPHABETICAL) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
