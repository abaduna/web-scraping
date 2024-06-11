package com.serch.webscapers.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="webpage")
public class WebPages {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 private String domain;
 @Column(unique = true)
 private  String url;
 private String title;
 @Column(columnDefinition = "TEXT")
 private String description;
 private String picture;
 private  Integer ranck;
}

