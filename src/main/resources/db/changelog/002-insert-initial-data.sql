-- Add realistic trending movies
-- Add realistic trending movies
INSERT INTO base_schema."movies" ("id", "title", "description", "duration-minutes", "age-rating", "poster-url", "release-year") VALUES 
(101, 'Dune: Part Two', 'Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family.', 166, 'PG-13', 'https://example.com/dune2.jpg', 2024),
(102, 'Deadpool & Wolverine', 'Deadpool teams up with Wolverine in this action-packed sequel.', 127, 'R', 'https://example.com/dpw.jpg', 2024),
(103, 'Inside Out 2', 'Riley is officially a teenager, and Headquarters undergoes a sudden demolition to make room for something entirely unexpected: new Emotions!', 96, 'PG', 'https://example.com/io2.jpg', 2024);

-- Add genres
INSERT INTO base_schema."movie_genres" ("movie_id", "genre") VALUES 
(101, 'ACTION'), (101, 'SCI_FI'), (101, 'DRAMA'),
(102, 'ACTION'), (102, 'COMEDY'),
(103, 'ANIMATION'), (103, 'COMEDY'), (103, 'FAMILY');

-- Add cinemas
INSERT INTO base_schema."cinemas" ("id", "name", "address", "city") VALUES 
(101, 'Grand Cinema', '145 Hakanari Street', 'Tokyo'),
(102, 'IMAX Central', '100 Main St', 'New York');

-- Add halls
INSERT INTO base_schema."halls" ("id", "name", "cinema_id") VALUES 
(101, 'Hall 1 (Silver)', 101),
(102, 'Hall 2 (Gold)', 101),
(103, 'IMAX Screen 1', 102);

-- Add price categories
INSERT INTO base_schema."price_category" ("id", "type", "name", "price") VALUES 
(101, 'REGULAR', 'Economy', 10.00),
(102, 'REGULAR', 'Regular', 15.00),
(103, 'VIP', 'Luxury', 25.00);

-- Add seats for Hall 1 (with null gaps represented by missing numbers)
-- Row 1 (Economy)
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "price_category_id") VALUES 
(1001, 101, 1, 1, 'ACTIVE', 101),
(1002, 101, 1, 2, 'ACTIVE', 101),
(1003, 101, 1, 4, 'ACTIVE', 101),
(1004, 101, 1, 5, 'ACTIVE', 101);

-- Row 2 (Regular)
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "price_category_id") VALUES 
(1005, 101, 2, 1, 'ACTIVE', 102),
(1006, 101, 2, 2, 'ACTIVE', 102),
(1007, 101, 2, 3, 'ACTIVE', 102),
(1008, 101, 2, 4, 'ACTIVE', 102),
(1009, 101, 2, 5, 'ACTIVE', 102),
(1010, 101, 2, 6, 'ACTIVE', 102);

-- Row 3 (Luxury) with gaps
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "price_category_id") VALUES 
(1011, 101, 3, 2, 'ACTIVE', 103),
(1012, 101, 3, 4, 'ACTIVE', 103);

-- Add Sessions
INSERT INTO base_schema."sessions" ("id", "movie_id", "hall_id", "date", "time", "language", "format") VALUES 
(101, 101, 101, '2026-07-20', '18:00:00', 'ENG', 'IMAX'),
(102, 102, 101, '2026-07-21', '20:00:00', 'ENG', 'DIGITAL_3D'),
(103, 103, 102, '2026-07-20', '10:00:00', 'ENG', 'DIGITAL_2D');

-- Add Session Seats (Booking status)
INSERT INTO base_schema."session_seats" ("id", "session_id", "seat_id", "is_available", "status") VALUES 
(1001, 101, 1001, 'true', 'ACTIVE'),
(1002, 101, 1002, 'false', 'ACTIVE'),
(1003, 101, 1003, 'true', 'ACTIVE'),
(1004, 101, 1004, 'true', 'ACTIVE');
