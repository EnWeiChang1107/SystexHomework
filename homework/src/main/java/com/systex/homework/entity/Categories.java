package com.systex.homework.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name="parent_id")
    private int parent_id;

    @Column(name="name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
