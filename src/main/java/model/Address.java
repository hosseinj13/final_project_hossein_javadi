package model;


import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "addresses")

public class Address extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "City must not be blank")
    String city;

    @NotBlank(message = "State must not be blank")
    String state;

    @NotBlank(message = "Street must not be blank")
    String addressLine;


    @NotBlank(message = "postalCode must not be blank")
    @Pattern(regexp = "\\d{10}", message = "postalCode must be a 10 digit number")
    String postalCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
     Set<Order> orders;
}
