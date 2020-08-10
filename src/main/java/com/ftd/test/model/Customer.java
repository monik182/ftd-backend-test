package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ftd.test.anotations.ValidEmail;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    @NotNull(message = "El campo no debe ser nulo")
    private UUID id;

    @NotEmpty(message = "El campo no debe ir vacío")
    private String fullName;

    @NotEmpty(message = "El campo no debe ir vacío")
    private String birthDate;

    @Min(value = 1, message = "El número de documento debe ser mayor o igual a 0")
    private long documentId;

    @ValidEmail
    @NotEmpty(message = "El campo no debe ir vacío")
    private String email;

    @NotEmpty(message = "El campo no debe ir vacío")
    private String userName;

    @NotEmpty(message = "El campo no debe ir vacío")
    private String password;

    public Customer() { }

    public Customer(@JsonProperty("id") UUID id,
                    @JsonProperty("fullName") String fullName,
                    @JsonProperty("birthDate") String birthDate,
                    @JsonProperty("documentId") long documentId,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password,
                    @JsonProperty("userName") String userName) {
        this.id = id != null ? id : UUID.randomUUID();
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.documentId = documentId;
        this.email = email.toLowerCase();
        this.password = password;
        this.userName = userName.toLowerCase();
    }

    public UUID getId() { return id; }

    public long getDocumentId() { return documentId; }

    public String getBirthDate() { return birthDate; }

    public String getFullName() { return fullName; }

    public String getEmail() { return email; }

    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    public int getAge() {
        LocalDate birthDate = LocalDate.parse(this.getBirthDate());
        System.out.println("birthDate ==>> " + birthDate);
        return calculateAge(birthDate, LocalDate.now());
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
