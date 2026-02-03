package guru.qa.country.controller;

import guru.qa.country.domain.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country){
        if (country.name().isBlank() || country.iso().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country name and code cannot be blank");
        }
        return countryService.createCountry(country);
    }

    @PatchMapping("/{iso}")
    public Country updateCountryById(@RequestBody Country country, @PathVariable String iso){
        if (country.name().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country name cannot be blank");
        }
        return countryService.updateCountryNameById(country.name(), iso);
    }
}
