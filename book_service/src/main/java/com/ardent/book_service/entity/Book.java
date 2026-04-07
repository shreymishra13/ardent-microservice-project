package com.ardent.book_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "books",
        indexes = {
                @Index(name = "idx_book", columnList = "name")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String authorName;

    private String description;

    @Column(nullable = false)
    private Double price;


    private Double discount;
    private Integer stock;


    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    private LocalDateTime approvedAt;
    private String approvedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Status status;

    //add image later

    @PrePersist
    public void prePersist(){

        if(this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
        if(this.updatedAt == null)
        this.updatedAt = LocalDateTime.now();

        this.approvedAt = null;
        this.approvedBy = null;
    }

    @PreUpdate
    public void preUpdate(){
        if(this.updatedAt == null)
        this.updatedAt = LocalDateTime.now();
    }




}
