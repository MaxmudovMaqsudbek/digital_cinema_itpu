-- ============================================================
-- Changeset 6: Fix invalid enum values in initial seed data
-- Author: Maksudbek
-- ============================================================

-- Fix Movie Age Ratings
UPDATE base_schema."movies" SET "age-rating" = 'PG_13' WHERE "age-rating" = 'PG-13';

-- Fix Session Languages
UPDATE base_schema."sessions" SET "language" = 'ENGLISH' WHERE "language" = 'ENG';
UPDATE base_schema."sessions" SET "language" = 'RUSSIAN' WHERE "language" = 'RUS';

-- Fix Session Formats
UPDATE base_schema."sessions" SET "format" = 'TWO_D' WHERE "format" = 'DIGITAL_2D';
UPDATE base_schema."sessions" SET "format" = 'THREE_D' WHERE "format" = 'DIGITAL_3D';
