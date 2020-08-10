package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Store {

    @Id
    @NotNull(message = "El campo no debe ser nulo")
    private UUID id;

    @NotBlank(message = "El campo no debe ir vacío")
    private String name;

    @NotBlank(message = "El campo no debe ir vacío")
    private String address;

    @Min(value = 0, message = "La hora debe ser mayor o igual a 0")
    @Max(value = 23, message = "La hora debe ser menor o igual a 23")
    private int openingHour;

    @Min(value = 0, message = "La hora debe ser mayor o igual a 0")
    @Max(value = 23, message = "La hora debe ser menor o igual a 23")
    private int closingHour;

    public Store() { }

    public Store(@JsonProperty("id") UUID id,
                 @JsonProperty("name") String name,
                 @JsonProperty("address") String address,
                 @JsonProperty("openingHour") int openingHour,
                 @JsonProperty("closingHour") int closingHour) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public int getOpeningHour() { return openingHour; }

    public int getClosingHour() { return closingHour; }
}
