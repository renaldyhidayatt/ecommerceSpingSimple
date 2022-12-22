package com.sanedge.ecommercesimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends Timestamped {
    @Column(nullable = false, unique = true)
    protected String name;

    @Column(length = 50)
    private String icon;

    @Column(length = 50)
    private String color;
}
