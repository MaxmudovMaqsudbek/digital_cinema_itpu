-- ============================================================
-- Changeset 4: Fix broken Amazon CDN poster URLs
-- Replace with stable TMDB CDN poster images (image.tmdb.org)
-- Author: Maksudbek
-- ============================================================

-- Interstellar (ID 46)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg'
WHERE "id" = 46;

-- Pulp Fiction (ID 48)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg'
WHERE "id" = 48;

-- The Shawshank Redemption (ID 49)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/lyQBXzOQSuE59IsHyhrp0qIiPAz.jpg'
WHERE "id" = 49;

-- The Godfather (ID 50)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsLegHzgMd9Am.jpg'
WHERE "id" = 50;

-- Forrest Gump (ID 51)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg'
WHERE "id" = 51;

-- Gladiator (ID 52)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg'
WHERE "id" = 52;

-- Spider-Man: No Way Home (ID 54)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg'
WHERE "id" = 54;

-- Titanic (ID 55)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg'
WHERE "id" = 55;

-- The Matrix (ID 56)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg'
WHERE "id" = 56;

-- Also fix existing movies from changeset 2 that may have issues
-- The Lord of the Rings: Fellowship (ID 44)
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg'
WHERE "id" = 44;

-- The Dark Knight (ID 47) - also switch to TMDB for consistency
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg'
WHERE "id" = 47;

-- Avengers: Endgame (ID 53) - also switch to TMDB for consistency
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg'
WHERE "id" = 53;

-- Inception (ID 45) - also switch to TMDB for consistency
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg'
WHERE "id" = 45;

-- Kill Bill (ID 43) - also switch to TMDB for consistency
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/v7TaX8kXMXs5yFFGR41guUDNcnB.jpg'
WHERE "id" = 43;

-- The Lion King (ID 42) - also switch to TMDB for consistency
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg'
WHERE "id" = 42;
