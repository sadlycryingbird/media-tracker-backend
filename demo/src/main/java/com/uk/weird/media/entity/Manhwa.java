package com.uk.weird.media.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "manhwa")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manhwa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    private String author;
    private String artist;
    private Integer numberOfChapters;

    @ElementCollection
    @CollectionTable(name = "manhwa_genres", joinColumns = @JoinColumn(name = "manhwa_id"))
    @Column(name = "genre")
    private List<String> genres;

    private Long externalId;
    private String airingStatus;
    private LocalDate startDate;
    private LocalDate endDate;

}
