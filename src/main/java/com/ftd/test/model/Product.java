package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @NotNull(message = "El campo no debe ser nulo")
    private UUID id;

    @NotBlank(message = "El campo no debe ir vacío")
    private String name;

    private String description;

    private String image;

    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private int stock;

    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private int price;

    @Min(value = 0, message = "El código de barras debe ser mayor o igual a 0")
    private long barcode;

    public Product() { }

    public Product(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") int price,
                   @JsonProperty("stock") int stock,
                   @JsonProperty("barcode") long barcode) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock > 0 ? stock : 100;
        this.barcode = barcode;
        this.image = "https://source.unsplash.com/random";
    }

    public UUID getId() { return id; }

    public String getName() { return name; }

    public long getBarcode() { return barcode; }

    public int getPrice() { return price; }

    public int getStock() { return stock; }

    public String getDescription() { return description; }

    public String getImage() { return image; }
}
