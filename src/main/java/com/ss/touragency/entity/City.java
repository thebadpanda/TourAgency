package com.ss.touragency.entity;

import java.util.Objects;

public class City {

    private Long cityId;
    private String cityName;
    private Country country;

    public City() {
    }

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }

    public City(Long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public City(Long cityId, String cityName, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId.equals(city.cityId) &&
                cityName.equals(city.cityName) &&
                country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName, country);
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
