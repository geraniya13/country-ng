package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.domain.Country;
import guru.qa.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryEntity -> {
                            return new Country(
                                    countryEntity.getName(),
                                    countryEntity.getIso(),
                                    countryEntity.getCoordinates() != null ? new String(countryEntity.getCoordinates()) : ""
                            );
                        }
                ).toList();
    }

    @Override
    public Country createCountry(Country country) {
        if (countryRepository.findByIso(country.iso()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country with ISO %s already exists".formatted(country.iso()));
        }

        return countryRepository.save(new CountryEntity(country)).toDto();
    }

    @Override
    public Country updateCountryNameById(String name, String iso) {
        CountryEntity country = countryRepository.findByIso(iso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country with ISO %s not found".formatted(iso)));

        country.setName(name);

        CountryEntity saved = countryRepository.save(country);

        return saved.toDto();
    }
}
