-- ============================================================
-- Changeset 5: Fix The Godfather poster URL (wrong TMDB path)
-- Author: Maksudbek
-- ============================================================

-- The Godfather (ID 50) - correct TMDB poster path
UPDATE base_schema."movies"
SET "poster-url" = 'https://image.tmdb.org/t/p/w500/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg'
WHERE "id" = 50;
