CREATE TABLE base_schema.cinemas (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(500),
    city VARCHAR(255)
);

CREATE TABLE base_schema.halls (
    id BIGSERIAL PRIMARY KEY,
    cinema_id BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT fk_halls_cinema FOREIGN KEY (cinema_id)
        REFERENCES base_schema.cinemas(id) ON DELETE CASCADE
);

CREATE TABLE base_schema.movies (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(500),
    "duration-minutes" INTEGER,
    "age-rating" VARCHAR(50),
    rating REAL,
    "poster-url" VARCHAR(1000),
    description TEXT,
    "release-year" INTEGER
);

CREATE TABLE base_schema.movie_genres (
    movie_id BIGINT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    CONSTRAINT fk_movie_genres_movie FOREIGN KEY (movie_id)
        REFERENCES base_schema.movies(id) ON DELETE CASCADE
);

CREATE TABLE base_schema.price_category (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50),
    name VARCHAR(255),
    price REAL
);

CREATE TABLE base_schema.seats (
    id BIGSERIAL PRIMARY KEY,
    hall_id BIGINT NOT NULL,
    price_category_id BIGINT NOT NULL,
    "row" INTEGER,
    "number" INTEGER,
    status VARCHAR(50),
    is_available BOOLEAN,
    comment VARCHAR(500),
    CONSTRAINT fk_seats_hall FOREIGN KEY (hall_id)
        REFERENCES base_schema.halls(id) ON DELETE CASCADE,
    CONSTRAINT fk_seats_price_category FOREIGN KEY (price_category_id)
        REFERENCES base_schema.price_category(id)
);

CREATE TABLE base_schema.sessions (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    title VARCHAR(500),
    "date" DATE,
    "time" TIME,
    language VARCHAR(50),
    format VARCHAR(50),
    CONSTRAINT fk_sessions_movie FOREIGN KEY (movie_id)
        REFERENCES base_schema.movies(id) ON DELETE CASCADE,
    CONSTRAINT fk_sessions_hall FOREIGN KEY (hall_id)
        REFERENCES base_schema.halls(id) ON DELETE CASCADE
);

CREATE TABLE base_schema.session_seats (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    status VARCHAR(50),
    is_available VARCHAR(50),
    customer_name VARCHAR(255),
    contact VARCHAR(255),
    CONSTRAINT fk_session_seats_session FOREIGN KEY (session_id)
        REFERENCES base_schema.sessions(id) ON DELETE CASCADE,
    CONSTRAINT fk_session_seats_seat FOREIGN KEY (seat_id)
        REFERENCES base_schema.seats(id) ON DELETE CASCADE
);

CREATE INDEX idx_halls_cinema_id ON base_schema.halls(cinema_id);
CREATE INDEX idx_movie_genres_movie_id ON base_schema.movie_genres(movie_id);
CREATE INDEX idx_seats_hall_id ON base_schema.seats(hall_id);
CREATE INDEX idx_seats_price_category_id ON base_schema.seats(price_category_id);
CREATE INDEX idx_sessions_movie_id ON base_schema.sessions(movie_id);
CREATE INDEX idx_sessions_hall_id ON base_schema.sessions(hall_id);
CREATE INDEX idx_session_seats_session_id ON base_schema.session_seats(session_id);
CREATE INDEX idx_session_seats_seat_id ON base_schema.session_seats(seat_id);