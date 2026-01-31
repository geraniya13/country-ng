package guru.qa.country.data;

import guru.qa.country.domain.Country;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "country_entity")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_entity_id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "country_entity_name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String iso;

    @Column(name = "coordinates", columnDefinition = "TEXT")
    private String coordinates;

    public CountryEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(iso, that.iso) && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, iso, coordinates);
    }

    public CountryEntity(Country dto) {
        this.name = dto.name();
        this.iso = dto.iso();
        this.coordinates = dto.coordinates();
    }

    public Country toDto() {
        return new Country(name, iso, coordinates);
    }

}
