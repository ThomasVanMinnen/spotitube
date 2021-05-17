--
-- Drop tables
--
DROP TABLE IF EXISTS kt_playlist_track;
DROP TABLE IF EXISTS playlists;
DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS track;
DROP TABLE IF EXISTS users;

--
-- Create tables
--
CREATE TABLE kt_playlist_track (
    playlistid          int(11)     NOT NULL,
    trackid             int(11)     NOT NULL,
    offlineAvailable    tinyint(1)  NOT NULL
);

CREATE TABLE playlists (
    id     int(11)         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name   varchar(500)    NOT NULL,
    owner  int(11)         NOT NULL
);

CREATE TABLE tokens (
    userid  int(11)     NOT NULL,
    token   varchar(36) NOT NULL UNIQUE
);

CREATE TABLE track (
    id              int(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title           varchar(500) NOT NULL,
    performer       varchar(500) NOT NULL,
    duration        int(11)      NOT NULL,
    album           varchar(500) NOT NULL,
    playcount       int(11)      NOT NULL DEFAULT 0,
    publicationDate date         NOT NULL,
    description     varchar(500) NOT NULL
);

CREATE TABLE users (
    id          int(11)         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username    varchar(500)    NOT NULL UNIQUE,
    password    varchar(500)    NOT NULL
);

--
-- Insert values
--
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(4, 2, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(6, 2, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(6, 1, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(6, 3, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(6, 4, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(2, 2, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(2, 1, 1);
INSERT INTO kt_playlist_track (playlistid, trackid, offlineAvailable) VALUES(2, 4, 1);

INSERT INTO playlists (id, name, owner) VALUES(2, 'Vieze vieze vieze slechte muziek jonge', 1);
INSERT INTO playlists (id, name, owner) VALUES(4, 'Rustige pokoes', 3);
INSERT INTO playlists (id, name, owner) VALUES(6, 'PLS make Java stop', 1);

INSERT INTO tokens (userid, token) VALUES(1, '05960607-17e5-4231-aff3-c41d0bdc639d');
INSERT INTO tokens (userid, token) VALUES(1, '266ec3f3-2960-4327-9efc-03fa1ddb1e6f');
INSERT INTO tokens (userid, token) VALUES(1, '4796e720-280e-4825-b1c7-24cbf3c5b6a9');
INSERT INTO tokens (userid, token) VALUES(1, 'f02fdca2-27a3-4120-a67e-a97094704913');

INSERT INTO track (id, title, performer, duration, album, playcount, publicationDate, description) VALUES(1, 'Thinking Out Loud', 'Ed Sheeran', 296, 'x', 0, '2014-01-01', 'Taken from the studio album X (multiply) released in 2014, which featured the hit singles \'Sing\', \'Don\'t\', \'Thinking Out Loud\', \'Bloodstream\', & \'Photograph\'. ');
INSERT INTO track (id, title, performer, duration, album, playcount, publicationDate, description) VALUES(2, 'Norton Commander', 'Men I Trust', 187, '(Garage session)', 0, '2020-07-10', 'Mellow, Indie');
INSERT INTO track (id, title, performer, duration, album, playcount, publicationDate, description) VALUES(3, 'Seven', 'Men I Trust', 255, '(Garage session)', 0, '2020-07-10', 'Mellow, Indie');
INSERT INTO track (id, title, performer, duration, album, playcount, publicationDate, description) VALUES(4, 'Numb', 'Men I Trust', 228, '(Garage session)', 0, '2020-07-10', 'Mellow, Indie');

INSERT INTO users (id, username, password) VALUES(1, 'thomas', 'thomas');
INSERT INTO users (id, username, password) VALUES(3, 'Jan', 'Jan');