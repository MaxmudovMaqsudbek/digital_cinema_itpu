-- Insert initial mock data to match frontend exactly
-- Add realistic trending movies (matched with mock data)
INSERT INTO base_schema."movies" ("id", "title", "description", "duration-minutes", "age-rating", "rating", "poster-url", "release-year") VALUES 
(42, 'The Lion King', 'Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.', 88, 'G', 8.5, 'https://m.media-amazon.com/images/M/MV5BZGRiZDZhZjItM2M3ZC00Y2IyLTk3Y2MtMWY5YjliNDFkZTJlXkEyXkFqcGc@._V1_FMjpg_UY2964_.jpg', 1994),
(43, 'Kill Bill', 'After waking from a four-year coma, a former assassin wreaks vengeance on the team of assassins who betrayed her.', 111, 'R', 8.2, 'https://m.media-amazon.com/images/M/MV5BNmQyZTMwNTMtM2U0Yy00YTM4LWJmZTgtZWIyYzdjODY4NGY4XkEyXkFqcGc@._V1_FMjpg_UY3000_.jpg', 2003),
(44, 'The Lord of the Rings: The Fellowship of the Ring', 'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.', 178, 'PG-13', 8.9, 'https://m.media-amazon.com/images/M/MV5BMzEwYTg2NTMtMTUzYy00ZjE4LThmMjEtZDUyZWM4NzE5MGE5XkEyXkFqcGc@._V1_FMjpg_UY2835_.jpg', 2001);

-- Add genres
INSERT INTO base_schema."movie_genres" ("movie_id", "genre") VALUES 
(42, 'DRAMA'), (42, 'ANIMATION'),
(43, 'THRILLER'), (43, 'ACTION'),
(44, 'DRAMA'), (44, 'FANTASY');

-- Add cinemas
INSERT INTO base_schema."cinemas" ("id", "name", "address", "city") VALUES 
(1, 'ITPU Grand Cinema', 'Tashkent ITPU', 'Tashkent');

-- Add halls
INSERT INTO base_schema."halls" ("id", "name", "cinema_id") VALUES 
(14, 'Main Hall (IMAX)', 1),
(15, 'Second Hall (3D)', 1);

-- Add price categories
INSERT INTO base_schema."price_category" ("id", "type", "name", "price") VALUES 
(1, 'REGULAR', 'Standard', 35000),
(2, 'VIP', 'VIP', 65000);

-- Add seats for Hall 14
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "is_available", "price_category_id", "comment") VALUES
(101, 14, 1, 1, 'ACTIVE', true, 1, NULL),
(102, 14, 1, 2, 'ACTIVE', true, 1, NULL),
(103, 14, 1, 3, 'ACTIVE', true, 1, NULL),
(104, 14, 1, 4, 'ACTIVE', true, 1, NULL),
(105, 14, 1, 5, 'ACTIVE', true, 1, NULL),
(106, 14, 1, 6, 'ACTIVE', true, 1, NULL),
(201, 14, 2, 1, 'ACTIVE', true, 1, NULL),
(202, 14, 2, 2, 'ACTIVE', true, 1, NULL),
(203, 14, 2, 3, 'ACTIVE', true, 1, NULL),
(204, 14, 2, 4, 'ACTIVE', true, 1, NULL),
(205, 14, 2, 5, 'ACTIVE', true, 1, NULL),
(206, 14, 2, 6, 'ACTIVE', true, 1, NULL),
(207, 14, 2, 7, 'ACTIVE', true, 1, NULL),
(208, 14, 2, 8, 'ACTIVE', true, 1, NULL),
(301, 14, 3, 1, 'ACTIVE', true, 1, NULL),
(302, 14, 3, 2, 'ACTIVE', true, 1, NULL),
(303, 14, 3, 3, 'ACTIVE', true, 1, NULL),
(304, 14, 3, 4, 'ACTIVE', true, 1, NULL),
(305, 14, 3, 5, 'ACTIVE', true, 1, NULL),
(306, 14, 3, 6, 'ACTIVE', true, 1, NULL),
(307, 14, 3, 7, 'DEACTIVATED', false, 1, 'Broken seat'),
(308, 14, 3, 8, 'ACTIVE', true, 1, NULL),
(309, 14, 3, 9, 'ACTIVE', true, 1, NULL),
(310, 14, 3, 10, 'ACTIVE', true, 1, NULL),
(401, 14, 4, 1, 'ACTIVE', true, 2, NULL),
(402, 14, 4, 2, 'ACTIVE', true, 2, NULL),
(403, 14, 4, 3, 'ACTIVE', true, 2, NULL),
(404, 14, 4, 4, 'ACTIVE', true, 2, NULL),
(405, 14, 4, 5, 'ACTIVE', true, 2, NULL),
(406, 14, 4, 6, 'ACTIVE', true, 2, NULL),
(407, 14, 4, 7, 'ACTIVE', true, 2, NULL),
(408, 14, 4, 8, 'ACTIVE', true, 2, NULL),
(409, 14, 4, 9, 'ACTIVE', true, 2, NULL),
(410, 14, 4, 10, 'ACTIVE', true, 2, NULL),
(411, 14, 4, 11, 'ACTIVE', true, 2, NULL),
(412, 14, 4, 12, 'ACTIVE', true, 2, NULL),
(501, 14, 5, 1, 'ACTIVE', true, 2, NULL),
(502, 14, 5, 2, 'ACTIVE', true, 2, NULL),
(503, 14, 5, 3, 'ACTIVE', true, 2, NULL),
(504, 14, 5, 4, 'ACTIVE', true, 2, NULL),
(505, 14, 5, 5, 'ACTIVE', true, 2, NULL),
(506, 14, 5, 6, 'ACTIVE', true, 2, NULL),
(507, 14, 5, 7, 'ACTIVE', true, 2, NULL),
(508, 14, 5, 8, 'ACTIVE', true, 2, NULL),
(509, 14, 5, 9, 'ACTIVE', true, 2, NULL),
(510, 14, 5, 10, 'ACTIVE', true, 2, NULL),
(511, 14, 5, 11, 'ACTIVE', true, 2, NULL),
(512, 14, 5, 12, 'ACTIVE', true, 2, NULL),
(601, 14, 6, 1, 'ACTIVE', true, 2, NULL),
(602, 14, 6, 2, 'ACTIVE', true, 2, NULL),
(603, 14, 6, 3, 'ACTIVE', true, 2, NULL),
(604, 14, 6, 4, 'ACTIVE', true, 2, NULL),
(605, 14, 6, 5, 'ACTIVE', true, 2, NULL),
(606, 14, 6, 6, 'ACTIVE', true, 2, NULL),
(607, 14, 6, 7, 'ACTIVE', true, 2, NULL),
(608, 14, 6, 8, 'ACTIVE', true, 2, NULL),
(609, 14, 6, 9, 'ACTIVE', true, 2, NULL),
(610, 14, 6, 10, 'ACTIVE', true, 2, NULL),
(611, 14, 6, 11, 'ACTIVE', true, 2, NULL),
(612, 14, 6, 12, 'ACTIVE', true, 2, NULL),
(701, 14, 7, 1, 'ACTIVE', true, 1, NULL),
(702, 14, 7, 2, 'ACTIVE', true, 1, NULL),
(703, 14, 7, 3, 'ACTIVE', true, 1, NULL),
(704, 14, 7, 4, 'ACTIVE', true, 1, NULL),
(705, 14, 7, 5, 'ACTIVE', true, 1, NULL),
(706, 14, 7, 6, 'ACTIVE', true, 1, NULL),
(707, 14, 7, 7, 'ACTIVE', true, 1, NULL),
(708, 14, 7, 8, 'ACTIVE', true, 1, NULL),
(709, 14, 7, 9, 'ACTIVE', true, 1, NULL),
(710, 14, 7, 10, 'ACTIVE', true, 1, NULL),
(711, 14, 7, 11, 'ACTIVE', true, 1, NULL),
(712, 14, 7, 12, 'ACTIVE', true, 1, NULL),
(801, 14, 8, 1, 'ACTIVE', true, 1, NULL),
(802, 14, 8, 2, 'ACTIVE', true, 1, NULL),
(803, 14, 8, 3, 'ACTIVE', true, 1, NULL),
(804, 14, 8, 4, 'ACTIVE', true, 1, NULL),
(805, 14, 8, 5, 'ACTIVE', true, 1, NULL),
(806, 14, 8, 6, 'ACTIVE', true, 1, NULL),
(807, 14, 8, 7, 'ACTIVE', true, 1, NULL),
(808, 14, 8, 8, 'ACTIVE', true, 1, NULL),
(809, 14, 8, 9, 'ACTIVE', true, 1, NULL),
(810, 14, 8, 10, 'DEACTIVATED', false, 1, 'Broken seat');

-- Add Sessions
INSERT INTO base_schema."sessions" ("id", "movie_id", "hall_id", "title", "date", "time", "language", "format") VALUES 
(1, 42, 14, 'The Lion King - Evening Show', '2026-07-15', '19:30:00', 'ENG', 'DIGITAL_2D'),
(2, 43, 14, 'Kill Bill - Night Show', '2026-07-15', '22:10:00', 'RUS', 'IMAX'),
(3, 44, 15, 'The Lord of the Rings: The Fellowship of the Ring - Matinee', '2026-07-16', '11:00:00', 'ENG', 'DIGITAL_3D');

-- Add Session Seats (Booking status)
INSERT INTO base_schema."session_seats" ("id", "session_id", "seat_id", "status", "is_available", "customer_name", "contact") VALUES 
(501, 1, 103, 'ACTIVE', 'true', NULL, NULL),
(502, 1, 205, 'ACTIVE', 'false', 'John Doe', '+998901234567'),
(503, 1, 405, 'ACTIVE', 'false', 'Maria Garcia', '+998902222222'),
(504, 1, 506, 'ACTIVE', 'false', 'Alice Smith', '+998907654321'),
(505, 1, 507, 'ACTIVE', 'false', 'Bob Johnson', '+998901111111'),
(506, 2, 305, 'ACTIVE', 'false', 'David Lee', '+998903333333'),
(507, 2, 502, 'ACTIVE', 'true', NULL, NULL);
