package com.uk.weird.media.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "drama")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    private Integer numberOfEpisodes;
    private String country;
    private String network;

    @ElementCollection
    @CollectionTable(name = "drama_genres", joinColumns = @JoinColumn(name = "drama_id"))
    @Column(name = "genre")
    private List<String> genres;

    @ElementCollection
    @CollectionTable(name = "drama_cast", joinColumns = @JoinColumn(name = "drama_id"))
    @Column(name = "actor")
    private List<String> cast;

    private String airingStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    private String externalId;
}
