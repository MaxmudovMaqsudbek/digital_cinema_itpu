-- ============================================================
-- Changeset 3: Expanded production-quality seed data (5x)
-- Author: Maksudbek
-- ============================================================

-- -------------------------------------------------------
-- 5 Extra Movies with real Amazon poster URLs
-- -------------------------------------------------------
INSERT INTO base_schema."movies" ("id", "title", "description", "duration-minutes", "age-rating", "rating", "poster-url", "release-year") VALUES
(45, 'Inception',
 'A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.',
 148, 'PG_13', 8.8,
 'https://m.media-amazon.com/images/M/MV5BMzEwYTg2NTMtMTUzYy00ZjE4LThmMjEtZDUyZWM4NzE5MGE5XkEyXkFqcGc@._V1_FMjpg_UY2835_.jpg',
 2010),
(46, 'Interstellar',
 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.',
 169, 'PG_13', 8.7,
 'https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFiZWZiZTQ4MzcwXkEyXkFqcGc@._V1_FMjpg_UY2836_.jpg',
 2014),
(47, 'The Dark Knight',
 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
 152, 'PG_13', 9.0,
 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UY2048_.jpg',
 2008),
(48, 'Pulp Fiction',
 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
 154, 'R', 8.9,
 'https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGc@._V1_FMjpg_UY2953_.jpg',
 1994),
(49, 'The Shawshank Redemption',
 'Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.',
 142, 'R', 9.3,
 'https://m.media-amazon.com/images/M/MV5BMDAyY2FhYjctNDc5OS00MDNlLThiMGUtY2UxYWVkNGY2ZjljXkEyXkFqcGc@._V1_FMjpg_UY2970_.jpg',
 1994),
(50, 'The Godfather',
 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
 175, 'R', 9.2,
 'https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDkzYS00ZDNhLWI4ZmQtNGY2NjgzNzA3MDIyXkEyXkFqcGc@._V1_FMjpg_UY2953_.jpg',
 1972),
(51, 'Forrest Gump',
 'The history of the United States from the 1950s to the 70s unfolds from the perspective of an Alabama man with an below-average IQ.',
 142, 'PG_13', 8.8,
 'https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGM2NTg4XkEyXkFqcGc@._V1_FMjpg_UY2832_.jpg',
 1994),
(52, 'Gladiator',
 'A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.',
 155, 'R', 8.5,
 'https://m.media-amazon.com/images/M/MV5BMDliOTIzNmUtOTllOC00NDU3LWFiNjYtMGM0NDc1YTMxNTYxXkEyXkFqcGc@._V1_FMjpg_UY2963_.jpg',
 2000),
(53, 'Avengers: Endgame',
 'After the devastating events of Avengers: Infinity War, the universe is in ruins. The Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.',
 181, 'PG_13', 8.4,
 'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_FMjpg_UY2048_.jpg',
 2019),
(54, 'Spider-Man: No Way Home',
 'With Spider-Man identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.',
 148, 'PG_13', 8.3,
 'https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzAyXkEyXkFqcGc@._V1_FMjpg_UY2048_.jpg',
 2021),
(55, 'Titanic',
 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.',
 194, 'PG_13', 7.9,
 'https://m.media-amazon.com/images/M/MV5BNDczOTYzMzM2OV5BMl5BanBnXkFtZTgwMDc5NzM0NjM@._V1_FMjpg_UY2048_.jpg',
 1997),
(56, 'The Matrix',
 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',
 136, 'R', 8.7,
 'https://m.media-amazon.com/images/M/MV5BN2NmN2VhMTQtMDNiOS00NDlhLTliMjgtODE2ZTY0ODQyNDdjXkEyXkFqcGc@._V1_FMjpg_UY2048_.jpg',
 1999);

-- -------------------------------------------------------
-- Movie Genres for new movies
-- -------------------------------------------------------
INSERT INTO base_schema."movie_genres" ("movie_id", "genre") VALUES
(45, 'ACTION'), (45, 'THRILLER'),
(46, 'DRAMA'), (46, 'ADVENTURE'),
(47, 'ACTION'), (47, 'CRIME'),
(48, 'CRIME'), (48, 'DRAMA'),
(49, 'DRAMA'),
(50, 'CRIME'), (50, 'DRAMA'),
(51, 'DRAMA'), (51, 'ROMANCE'),
(52, 'ACTION'), (52, 'ADVENTURE'),
(53, 'ACTION'), (53, 'ADVENTURE'),
(54, 'ACTION'), (54, 'ADVENTURE'),
(55, 'DRAMA'), (55, 'ROMANCE'),
(56, 'ACTION'), (56, 'THRILLER');

-- -------------------------------------------------------
-- 2 More Cinemas
-- -------------------------------------------------------
INSERT INTO base_schema."cinemas" ("id", "name", "address", "city") VALUES
(2, 'CineStar Tashkent', 'Buyuk Ipak Yoli 12', 'Tashkent'),
(3, 'Samarkand Multiplex', 'Registon Plaza 1', 'Samarkand'),
(4, 'Namangan Cinema Palace', 'Mustaqillik Street 5', 'Namangan'),
(5, 'Bukhara Star Cinema', 'Khoja Nurabad 8', 'Bukhara');

-- -------------------------------------------------------
-- More Halls
-- -------------------------------------------------------
INSERT INTO base_schema."halls" ("id", "name", "cinema_id") VALUES
(16, 'Premier Hall (4DX)', 2),
(17, 'Classic Hall', 2),
(18, 'IMAX Hall', 3),
(19, 'VIP Hall', 3),
(20, 'Standard Hall', 4),
(21, 'Dolby Atmos Hall', 5);

-- -------------------------------------------------------
-- More Price Categories
-- -------------------------------------------------------
INSERT INTO base_schema."price_category" ("id", "type", "name", "price") VALUES
(3, 'LUXURY', 'Luxury Recliner', 95000),
(4, 'REGULAR', 'Student', 25000),
(5, 'VIP', 'Couple Suite', 120000);

-- -------------------------------------------------------
-- Seats for new halls (Hall 16-21)
-- -------------------------------------------------------
-- Hall 16 (Premier 4DX) - 4 rows x 8 seats
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "is_available", "price_category_id", "comment") VALUES
(1001,16,1,1,'ACTIVE',true,3,NULL),(1002,16,1,2,'ACTIVE',true,3,NULL),(1003,16,1,3,'ACTIVE',true,3,NULL),(1004,16,1,4,'ACTIVE',true,3,NULL),
(1005,16,1,5,'ACTIVE',true,3,NULL),(1006,16,1,6,'ACTIVE',true,3,NULL),(1007,16,1,7,'ACTIVE',true,3,NULL),(1008,16,1,8,'ACTIVE',true,3,NULL),
(1009,16,2,1,'ACTIVE',true,3,NULL),(1010,16,2,2,'ACTIVE',true,3,NULL),(1011,16,2,3,'ACTIVE',true,3,NULL),(1012,16,2,4,'ACTIVE',true,3,NULL),
(1013,16,2,5,'ACTIVE',true,3,NULL),(1014,16,2,6,'ACTIVE',true,3,NULL),(1015,16,2,7,'ACTIVE',true,3,NULL),(1016,16,2,8,'ACTIVE',true,3,NULL),
(1017,16,3,1,'ACTIVE',true,5,NULL),(1018,16,3,2,'ACTIVE',true,5,NULL),(1019,16,3,3,'ACTIVE',true,5,NULL),(1020,16,3,4,'ACTIVE',true,5,NULL),
(1021,16,3,5,'ACTIVE',true,5,NULL),(1022,16,3,6,'ACTIVE',true,5,NULL),(1023,16,3,7,'ACTIVE',true,5,NULL),(1024,16,3,8,'ACTIVE',true,5,NULL),
(1025,16,4,1,'ACTIVE',true,5,NULL),(1026,16,4,2,'ACTIVE',true,5,NULL),(1027,16,4,3,'ACTIVE',true,5,NULL),(1028,16,4,4,'DEACTIVATED',false,5,'Under repair'),
(1029,16,4,5,'ACTIVE',true,5,NULL),(1030,16,4,6,'ACTIVE',true,5,NULL),(1031,16,4,7,'ACTIVE',true,5,NULL),(1032,16,4,8,'ACTIVE',true,5,NULL);

-- Hall 17 (Classic) - 3 rows x 6 seats
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "is_available", "price_category_id", "comment") VALUES
(1101,17,1,1,'ACTIVE',true,4,NULL),(1102,17,1,2,'ACTIVE',true,4,NULL),(1103,17,1,3,'ACTIVE',true,4,NULL),(1104,17,1,4,'ACTIVE',true,4,NULL),(1105,17,1,5,'ACTIVE',true,4,NULL),(1106,17,1,6,'ACTIVE',true,4,NULL),
(1107,17,2,1,'ACTIVE',true,1,NULL),(1108,17,2,2,'ACTIVE',true,1,NULL),(1109,17,2,3,'ACTIVE',true,1,NULL),(1110,17,2,4,'ACTIVE',true,1,NULL),(1111,17,2,5,'ACTIVE',true,1,NULL),(1112,17,2,6,'ACTIVE',true,1,NULL),
(1113,17,3,1,'ACTIVE',true,2,NULL),(1114,17,3,2,'ACTIVE',true,2,NULL),(1115,17,3,3,'ACTIVE',true,2,NULL),(1116,17,3,4,'ACTIVE',true,2,NULL),(1117,17,3,5,'ACTIVE',true,2,NULL),(1118,17,3,6,'ACTIVE',true,2,NULL);

-- Hall 18 (IMAX) - 4 rows x 10 seats
INSERT INTO base_schema."seats" ("id", "hall_id", "row", "number", "status", "is_available", "price_category_id", "comment") VALUES
(1201,18,1,1,'ACTIVE',true,1,NULL),(1202,18,1,2,'ACTIVE',true,1,NULL),(1203,18,1,3,'ACTIVE',true,1,NULL),(1204,18,1,4,'ACTIVE',true,1,NULL),(1205,18,1,5,'ACTIVE',true,1,NULL),
(1206,18,1,6,'ACTIVE',true,1,NULL),(1207,18,1,7,'ACTIVE',true,1,NULL),(1208,18,1,8,'ACTIVE',true,1,NULL),(1209,18,1,9,'ACTIVE',true,1,NULL),(1210,18,1,10,'ACTIVE',true,1,NULL),
(1211,18,2,1,'ACTIVE',true,2,NULL),(1212,18,2,2,'ACTIVE',true,2,NULL),(1213,18,2,3,'ACTIVE',true,2,NULL),(1214,18,2,4,'ACTIVE',true,2,NULL),(1215,18,2,5,'ACTIVE',true,2,NULL),
(1216,18,2,6,'ACTIVE',true,2,NULL),(1217,18,2,7,'ACTIVE',true,2,NULL),(1218,18,2,8,'ACTIVE',true,2,NULL),(1219,18,2,9,'ACTIVE',true,2,NULL),(1220,18,2,10,'ACTIVE',true,2,NULL),
(1221,18,3,1,'ACTIVE',true,3,NULL),(1222,18,3,2,'ACTIVE',true,3,NULL),(1223,18,3,3,'ACTIVE',true,3,NULL),(1224,18,3,4,'ACTIVE',true,3,NULL),(1225,18,3,5,'ACTIVE',true,3,NULL),
(1226,18,3,6,'ACTIVE',true,3,NULL),(1227,18,3,7,'ACTIVE',true,3,NULL),(1228,18,3,8,'ACTIVE',true,3,NULL),(1229,18,3,9,'ACTIVE',true,3,NULL),(1230,18,3,10,'ACTIVE',true,3,NULL),
(1231,18,4,1,'ACTIVE',true,5,NULL),(1232,18,4,2,'ACTIVE',true,5,NULL),(1233,18,4,3,'ACTIVE',true,5,NULL),(1234,18,4,4,'ACTIVE',true,5,NULL),(1235,18,4,5,'ACTIVE',true,5,NULL),
(1236,18,4,6,'ACTIVE',true,5,NULL),(1237,18,4,7,'ACTIVE',true,5,NULL),(1238,18,4,8,'ACTIVE',true,5,NULL),(1239,18,4,9,'ACTIVE',true,5,NULL),(1240,18,4,10,'ACTIVE',true,5,NULL);

-- -------------------------------------------------------
-- 12 New Sessions across halls and movies
-- -------------------------------------------------------
INSERT INTO base_schema."sessions" ("id", "movie_id", "hall_id", "title", "date", "time", "language", "format") VALUES
(4,  45, 14,  'Inception - Morning Show',                '2026-08-01', '10:00:00', 'ENGLISH', 'TWO_D'),
(5,  46, 16,  'Interstellar - 4DX Experience',           '2026-08-01', '14:00:00', 'ENGLISH', 'THREE_D'),
(6,  47, 18,  'The Dark Knight - IMAX',                  '2026-08-01', '19:00:00', 'ENGLISH', 'IMAX'),
(7,  48, 17,  'Pulp Fiction - Classic Screening',        '2026-08-02', '20:00:00', 'ENGLISH', 'TWO_D'),
(8,  49, 14,  'Shawshank Redemption - Evening',          '2026-08-02', '18:30:00', 'ENGLISH', 'TWO_D'),
(9,  50, 16,  'The Godfather - Premium',                 '2026-08-03', '19:30:00', 'ENGLISH', 'THREE_D'),
(10, 51, 17,  'Forrest Gump - Matinee',                  '2026-08-03', '11:00:00', 'ENGLISH', 'TWO_D'),
(11, 52, 18,  'Gladiator - IMAX',                        '2026-08-04', '21:00:00', 'ENGLISH', 'IMAX'),
(12, 53, 14,  'Avengers: Endgame - Evening',             '2026-08-04', '18:00:00', 'ENGLISH', 'TWO_D'),
(13, 54, 16,  'Spider-Man No Way Home - 4DX',            '2026-08-05', '15:00:00', 'ENGLISH', 'THREE_D'),
(14, 55, 17,  'Titanic - Romantic Night Show',           '2026-08-05', '20:30:00', 'ENGLISH', 'TWO_D'),
(15, 56, 18,  'The Matrix - IMAX Reloaded',              '2026-08-06', '22:00:00', 'ENGLISH', 'IMAX');

-- -------------------------------------------------------
-- Session Seats for new sessions (realistic bookings)
-- -------------------------------------------------------
INSERT INTO base_schema."session_seats" ("id", "session_id", "seat_id", "status", "is_available", "customer_name", "contact") VALUES
-- Session 4: Inception
(601, 4, 101, 'ACTIVE', 'true',  NULL,            NULL),
(602, 4, 102, 'ACTIVE', 'false', 'Ahmed Karimov', '+998901001001'),
(603, 4, 103, 'ACTIVE', 'false', 'Nilufar Yusupova', '+998902002002'),
(604, 4, 201, 'ACTIVE', 'true',  NULL,            NULL),
(605, 4, 401, 'ACTIVE', 'false', 'Jasur Toshmatov', '+998903003003'),

-- Session 5: Interstellar
(606, 5, 1001, 'ACTIVE', 'false', 'Elena Smirnova', '+998904004004'),
(607, 5, 1002, 'ACTIVE', 'false', 'Rustam Nazarov', '+998905005005'),
(608, 5, 1003, 'ACTIVE', 'true',  NULL, NULL),
(609, 5, 1009, 'ACTIVE', 'true',  NULL, NULL),

-- Session 6: The Dark Knight
(610, 6, 1201, 'ACTIVE', 'false', 'Sofia Chen', '+998906006006'),
(611, 6, 1202, 'ACTIVE', 'false', 'Carlos Rivera', '+998907007007'),
(612, 6, 1211, 'ACTIVE', 'false', 'Aisha Mahmudova', '+998908008008'),
(613, 6, 1221, 'ACTIVE', 'true',  NULL, NULL),

-- Session 7: Pulp Fiction
(614, 7, 1101, 'ACTIVE', 'false', 'James Wilson', '+998909009009'),
(615, 7, 1102, 'ACTIVE', 'false', 'Pham Van Duc', '+998910010010'),
(616, 7, 1107, 'ACTIVE', 'true',  NULL, NULL),

-- Session 8: Shawshank Redemption
(617, 8, 301, 'ACTIVE', 'false', 'Kamola Ergasheva', '+998911011011'),
(618, 8, 302, 'ACTIVE', 'false', 'Timur Alimov', '+998912012012'),
(619, 8, 501, 'ACTIVE', 'true',  NULL, NULL),

-- Session 9: The Godfather
(620, 9, 1017, 'ACTIVE', 'false', 'Marco Ferrari', '+998913013013'),
(621, 9, 1018, 'ACTIVE', 'false', 'Yuki Tanaka', '+998914014014'),
(622, 9, 1025, 'ACTIVE', 'true',  NULL, NULL),

-- Session 12: Avengers
(623, 12, 401, 'ACTIVE', 'false', 'Kevin Park', '+998915015015'),
(624, 12, 402, 'ACTIVE', 'false', 'Sara Johansson', '+998916016016'),
(625, 12, 501, 'ACTIVE', 'true',  NULL, NULL),
(626, 12, 601, 'ACTIVE', 'false', 'Omar Abdullaev', '+998917017017');
