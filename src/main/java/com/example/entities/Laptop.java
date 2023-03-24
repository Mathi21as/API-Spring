package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private Integer weight;
    private Double inches;
    private String processorManufacturer;
    private Integer ramAmount;

    public Laptop() {}

    public Laptop(Long id, String manufacturer, String model, Integer weight, Double inches, String processorManufacturer, Integer ramAmount) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.weight = weight;
        this.inches = inches;
        this.processorManufacturer = processorManufacturer;
        this.ramAmount = ramAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getInches() {
        return inches;
    }

    public void setInches(Double inches) {
        this.inches = inches;
    }

    public String getProcessorManufacturer() {
        return processorManufacturer;
    }

    public void setProcessorManufacturer(String processorManufacturer) {
        this.processorManufacturer = processorManufacturer;
    }

    public Integer getRamAmount() {
        return ramAmount;
    }

    public void setRamAmount(Integer ramAmount) {
        this.ramAmount = ramAmount;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                ", inches=" + inches +
                ", processorManufacturer='" + processorManufacturer + '\'' +
                ", ramAmount=" + ramAmount +
                '}';
    }
}
