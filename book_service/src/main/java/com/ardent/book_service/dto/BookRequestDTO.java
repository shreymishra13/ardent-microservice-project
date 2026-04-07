package com.ardent.book_service.dto;


import com.ardent.book_service.entity.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class BookRequestDTO {



    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Author name can't be blank")
    private String authorName;

    private String description;


    @NotNull(message = "Price can't be empty")
    @Positive(message = "Price must be greather than or equal to 0")
    private Double price;

    @Positive(message = "Discount must be greater than 0")
    private Double discount;

    @Positive(message = "Stock must be greather than 0")
    private Integer stock;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

}
