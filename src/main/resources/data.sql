SET @user_donna = 'donna';
SET @user_paul = 'pdrake';

INSERT INTO account (username, password)
VALUES (@user_donna, 'dell1150'),
       (@user_paul, 'Rewind0614');

SELECT @paul_id := a.id
FROM account a
WHERE username = @user_paul;

SELECT @donna_id := a.id
FROM account a
WHERE username = @user_donna;

INSERT INTO role (account_id, authority)
VALUES (@paul_id, 'ROLE_ADMIN');

INSERT INTO role (account_id, authority)
VALUES (@donna_id, 'ROLE_USER');

INSERT INTO genre (name)
values ('horror'),
       ('thriller'),
       ('action'),
       ('adventure');

INSERT INTO movie (name, description)
VALUES ('saw', 'torture porn'),
       ('aquaman', 'deep sea adventures'),
       ('mission impossible', 'spy thriller'),
       ('hunger games', 'fight to the death'),
       ('night swim', 'wishing well'),
       ('the beekeeper', 'unstoppable badass');

INSERT INTO cast (name, age)
values ('Tobin Bell', 81),
       ('Shawnee Smith', 55),
       ('Costas Mandylor', 58),
       ('Jason Momoa', 44),
       ('Amber Heard', 37),
       ('Yahya Abdul-Mateen', 37),
       ('Tom Cruise', 61),
       ('Ving Rhames', 64),
       ('Rebecca Ferguson', 40),
       ('Jennifer Lawrence', 33),
       ('Joshua Hutcherson', 31),
       ('Liam Hemsworth', 34),
       ('Wyatt Russell', 37),
       ('Kerry Condon', 41),
       ('Jodi Long', 70),
       ('Jason Statham', 56),
       ('Jeremy Irons', 75);

SELECT @saw_id := m.id
FROM movie m
WHERE name = 'saw';

SELECT @aquaman_id := m.id
FROM movie m
WHERE name = 'aquaman';

SELECT @mission_impossible_id := m.id
FROM movie m
WHERE name = 'mission impossible';

SELECT @hunger_games_id := m.id
FROM movie m
WHERE name = 'hunger games';

SELECT @night_swim_id := m.id
FROM movie m
WHERE name = 'night swim';

SELECT @beekeeper_id := m.id
FROM movie m
WHERE name = 'the beekeeper';

SELECT @tobin_id := c.id
FROM cast c
WHERE name = 'Tobin Bell';

SELECT @shawnee_id := c.id
FROM cast c
WHERE name = 'Shawnee Smith';

SELECT @costas_id := c.id
FROM cast c
WHERE name = 'Costas Mandylor';

SELECT @jason_m_id := c.id
FROM cast c
WHERE name = 'Jason Momoa';

SELECT @amber_id := c.id
FROM cast c
WHERE name = 'Amber Heard';

SELECT @yahya_id := c.id
FROM cast c
WHERE name = 'Yahya Abdul-Mateen';

SELECT @tom_id := c.id
FROM cast c
WHERE name = 'Tom Cruise';

SELECT @ving_id := c.id
FROM cast c
WHERE name = 'Ving Rhames';

SELECT @rebecca_id := c.id
FROM cast c
WHERE name = 'Rebecca Ferguson';

SELECT @jennifer_id := c.id
FROM cast c
WHERE name = 'Jennifer Lawrence';

SELECT @joshua_id := c.id
FROM cast c
WHERE name = 'Joshua Hutcherson';

SELECT @liam_id := c.id
FROM cast c
WHERE name = 'Liam Hemsworth';

SELECT @wyatt_id := c.id
FROM cast c
WHERE name = 'Wyatt Russell';

SELECT @kerry_id := c.id
FROM cast c
WHERE name = 'Kerry Condon';

SELECT @jodi_id := c.id
FROM cast c
WHERE name = 'Jodi Long';

SELECT @jason_s_id := c.id
FROM cast c
WHERE name = 'Jason Statham';

SELECT @jerremy_id := c.id
FROM cast c
WHERE name = 'Jeremy Irons';

INSERT INTO movie_cast (movie_id, cast_id)
VALUES (@saw_id, @tobin_id),
       (@saw_id, @shawnee_id),
       (@saw_id, @costas_id),
       (@aquaman_id, @jason_m_id),
       (@aquaman_id, @amber_id),
       (@aquaman_id, @yahya_id),
       (@mission_impossible_id, @tom_id),
       (@mission_impossible_id, @ving_id),
       (@mission_impossible_id, @rebecca_id),
       (@hunger_games_id, @jennifer_id),
       (@hunger_games_id, @joshua_id),
       (@hunger_games_id, @liam_id),
       (@night_swim_id, @wyatt_id),
       (@night_swim_id, @kerry_id),
       (@night_swim_id, @jodi_id),
       (@beekeeper_id, @jason_s_id),
       (@beekeeper_id, @jerremy_id),
       (@beekeeper_id, @joshua_id);

SELECT @horror_id := g.id
FROM genre g
WHERE g.name = 'horror';

SELECT @thriller_id := g.id
FROM genre g
WHERE g.name = 'thriller';

SELECT @action_id := g.id
FROM genre g
WHERE g.name = 'action';

SELECT @adventure_id := g.id
FROM genre g
WHERE g.name = 'adventure';

INSERT INTO movie_genres (genre_id, movie_id)
VALUES (@horror_id, @saw_id),
       (@action_id, @aquaman_id),
       (@action_id, @mission_impossible_id),
       (@adventure_id, @hunger_games_id),
       (@thriller_id, @night_swim_id),
       (@action_id, @beekeeper_id);
