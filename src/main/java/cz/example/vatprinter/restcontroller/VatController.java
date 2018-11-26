package cz.example.vatprinter.restcontroller;

import cz.example.vatprinter.enums.LexicographicOrder;
import cz.example.vatprinter.enums.VatRateOrder;
import cz.example.vatprinter.model.VatRate;
import cz.example.vatprinter.service.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VatController {

    private static final String MAPPING_BASE = "/rest/vat";

    private static final int RESULTS_COUNT = 3;

    @Autowired
    private VatService vatService;

    @RequestMapping(value = MAPPING_BASE + "/lowest-rates", method = RequestMethod.GET)
    public List<VatRate> findLowestRates() {
        return vatService.findRates(RESULTS_COUNT, VatRateOrder.LOWEST_FIRST, LexicographicOrder.ALPHABETICAL);
    }

    @RequestMapping(value = MAPPING_BASE + "/highest-rates", method = RequestMethod.GET)
    public List<VatRate> findHighestRates() {
        return vatService.findRates(RESULTS_COUNT, VatRateOrder.HIGHEST_FIRST, LexicographicOrder.ALPHABETICAL);
    }
}
