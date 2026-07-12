package com.itpu.internship2.digital_cinema.fixture;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.entity.*;
import com.itpu.internship2.digital_cinema.util.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Centralized factory for test data — prevents duplication across test classes.
 */
public final class TestDataFactory {

    private TestDataFactory() {}

    // ─── Movie ────────────────────────────────────────────────────────────────

    public static SaveMovieDTO saveMovieDTO() {
        return SaveMovieDTO.builder()
                .title("The Matrix")
                .durationMinutes(136)
                .ageRating(AgeRating.R)
                .rating(8.7f)
                .posterUrl("https://example.com/matrix.jpg")
                .description("A sci-fi classic.")
                .releaseYear(1999)
                .build();
    }

    public static GetMovieDTO getMovieDTO() {
        return GetMovieDTO.builder()
                .id(1L)
                .title("The Matrix")
                .durationMinutes(136)
                .ageRating(AgeRating.R)
                .rating(8.7f)
                .posterUrl("https://example.com/matrix.jpg")
                .description("A sci-fi classic.")
                .releaseYear(1999)
                .build();
    }

    public static MovieEntity movieEntity() {
        return MovieEntity.builder()
                .id(1L)
                .title("The Matrix")
                .durationMinutes(136)
                .ageRating(AgeRating.R)
                .rating(8.7f)
                .posterUrl("https://example.com/matrix.jpg")
                .description("A sci-fi classic.")
                .releaseYear(1999)
                .build();
    }

    // ─── Hall & Cinema ───────────────────────────────────────────────────────

    public static CinemaEntity cinemaEntity() {
        return CinemaEntity.builder()
                .id(1L)
                .name("CinemaCity")
                .address("123 Main St")
                .city("Tashkent")
                .build();
    }

    public static HallEntity hallEntity() {
        return HallEntity.builder()
                .id(1L)
                .cinema(cinemaEntity())
                .name("Hall A")
                .build();
    }

    // ─── PriceCategory ────────────────────────────────────────────────────────

    public static PriceCategoryEntity priceCategoryEntity() {
        return PriceCategoryEntity.builder()
                .id(1L)
                .type(PriceCategory.REGULAR)
                .name("Regular")
                .price(10.0f)
                .build();
    }

    // ─── Seat ─────────────────────────────────────────────────────────────────

    public static SaveSeatDTO saveSeatDTO() {
        return SaveSeatDTO.builder()
                .hallId(1L)
                .priceCategoryId(1L)
                .row(5)
                .number(12)
                .status(SeatStatus.ACTIVE)
                .isAvailable(true)
                .comment("VIP Seat")
                .build();
    }

    public static GetSeatDTO getSeatDTO() {
        return GetSeatDTO.builder()
                .id(1L)
                .hallId(1L)
                .priceCategoryId(1L)
                .row(5)
                .number(12)
                .status(SeatStatus.ACTIVE)
                .isAvailable(true)
                .comment("VIP Seat")
                .build();
    }

    public static SeatEntity seatEntity() {
        return SeatEntity.builder()
                .id(1L)
                .hall(hallEntity())
                .priceCategory(priceCategoryEntity())
                .row(5)
                .number(12)
                .status(SeatStatus.ACTIVE)
                .isAvailable(true)
                .comment("VIP Seat")
                .build();
    }

    // ─── Session ──────────────────────────────────────────────────────────────

    public static SaveSessionDTO saveSessionDTO() {
        return SaveSessionDTO.builder()
                .movieId(1L)
                .hallId(1L)
                .title("Evening Premiere")
                .date(LocalDate.of(2026, 7, 20))
                .time(LocalTime.of(19, 0))
                .language(MovieLang.ENGLISH)
                .format(MovieFormat.TWO_D)
                .build();
    }

    public static GetSessionDTO getSessionDTO() {
        return GetSessionDTO.builder()
                .id(1L)
                .movieId(1L)
                .hallId(1L)
                .title("Evening Premiere")
                .date(LocalDate.of(2026, 7, 20))
                .time(LocalTime.of(19, 0))
                .language(MovieLang.ENGLISH)
                .format(MovieFormat.TWO_D)
                .build();
    }

    public static SessionEntity sessionEntity() {
        return SessionEntity.builder()
                .id(1L)
                .movie(movieEntity())
                .hall(hallEntity())
                .title("Evening Premiere")
                .date(LocalDate.of(2026, 7, 20))
                .time(LocalTime.of(19, 0))
                .language(MovieLang.ENGLISH)
                .format(MovieFormat.TWO_D)
                .build();
    }

    // ─── SessionSeat ──────────────────────────────────────────────────────────

    public static SaveSessionSeatDTO saveSessionSeatDTO() {
        return SaveSessionSeatDTO.builder()
                .sessionId(1L)
                .seatId(1L)
                .status(SeatStatus.ACTIVE)
                .isAvailable("YES")
                .customerName("John Doe")
                .contact("+998901234567")
                .build();
    }

    public static GetSessionSeatDTO getSessionSeatDTO() {
        return GetSessionSeatDTO.builder()
                .id(1L)
                .sessionId(1L)
                .seatId(1L)
                .status(SeatStatus.ACTIVE)
                .isAvailable("YES")
                .customerName("John Doe")
                .contact("+998901234567")
                .build();
    }

    public static SessionSeatEntity sessionSeatEntity() {
        return SessionSeatEntity.builder()
                .id(1L)
                .session(sessionEntity())
                .seat(seatEntity())
                .status(SeatStatus.ACTIVE)
                .isAvailable("YES")
                .customerName("John Doe")
                .contact("+998901234567")
                .build();
    }
}
