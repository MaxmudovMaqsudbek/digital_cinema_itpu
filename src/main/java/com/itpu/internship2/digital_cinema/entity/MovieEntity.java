package com.itpu.internship2.digital_cinema.entity;

import com.itpu.internship2.digital_cinema.util.AgeRating;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(name = "movies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "\"duration-minutes\"")
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"age-rating\"", length = 50)
    private AgeRating ageRating;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "\"poster-url\"", length = 1000)
    private String posterUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "\"release-year\"")
    private Integer releaseYear;

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private Set<MovieGenreEntity> movieGenres = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private List<SessionEntity> sessions = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        MovieEntity that = (MovieEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
