package guru.qa.country.service;

import guru.qa.country.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    Country createCountry(Country country);

    Country updateCountryNameById(String name, String iso);
}
