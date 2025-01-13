package com.temp.up_v3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "image_details")
public class ContestImageDetails {

    @Id
    @Column(name = "contest_id", nullable = false)
    private Long contestId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public ContestImageDetails(Contest contest, String imagePath) {
        this.contest = contest;
        this.imagePath = imagePath;
        this.createdAt = LocalDateTime.now();

    }
}