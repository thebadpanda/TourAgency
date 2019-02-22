package com.ss.touragency.entity;

import java.util.Objects;

public class Country {

    private Long idCountry;
    private String countryName;

    public Country(Long idCountry, String countryName) {
        this.idCountry = idCountry;
        this.countryName = countryName;
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country() {
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "idCountry=" + idCountry +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return idCountry.equals(country.idCountry) &&
                countryName.equals(country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, countryName);
    }
}
