-- ============================================================
-- Changeset 7: Reset PostgreSQL sequences after manual inserts
-- Author: Maksudbek
-- ============================================================

SELECT setval('base_schema.movies_id_seq', (SELECT MAX(id) FROM base_schema.movies));
SELECT setval('base_schema.cinemas_id_seq', (SELECT MAX(id) FROM base_schema.cinemas));
SELECT setval('base_schema.halls_id_seq', (SELECT MAX(id) FROM base_schema.halls));
SELECT setval('base_schema.price_category_id_seq', (SELECT MAX(id) FROM base_schema.price_category));
SELECT setval('base_schema.seats_id_seq', (SELECT MAX(id) FROM base_schema.seats));
SELECT setval('base_schema.sessions_id_seq', (SELECT MAX(id) FROM base_schema.sessions));
SELECT setval('base_schema.session_seats_id_seq', (SELECT MAX(id) FROM base_schema.session_seats));
