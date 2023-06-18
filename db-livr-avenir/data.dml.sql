DELETE FROM users;
DELETE FROM roles;
DELETE FROM books;
DELETE FROM categories;
DELETE FROM conditions;
DELETE FROM formats;
DELETE FROM languages;

INSERT INTO categories 
	(name)
	VALUES 
	('Littérature'), ('Jeunesse'), ('Bandes dessinées'), ('Loisir - Nature - Voyage'), ('Art - Musique - Cinéma'), ('Autre');

INSERT INTO conditions 
	(name, logical_order)
	VALUES
	('Excellent', 1), ('Très bon', 2), ('Bon', 3), ('Satisfaisant', '4');

INSERT INTO languages 
	(name, logical_order)
	VALUES
	('Français', 1), ('Anglais', 2), ('Autre', 3);

INSERT INTO formats 
	(name)
	VALUES 
	('Broché'), ('Relié');

INSERT INTO books
	(title, isbn, author, publication_year, edition, image, description, point, created_at, updated_at, format_id, language_id, category_id, condition_id)
	VALUES
    ('Déjeunons sur lherbe', '978-2-38292-066-4','Guillaume Durand','2010',  'Essai', 'dejeunons-sur-l-herbe.jpg', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.', '3',
	now(), null, (SELECT f.id FROM formats f WHERE f.name = 'Broché'), (SELECT l.id FROM languages l WHERE l.name = 'Français'), (SELECT c.id FROM categories c WHERE c.name = 'Littérature'), (SELECT c.id FROM conditions c WHERE c.name = 'Très bon')) ;

INSERT INTO roles
	(name)
	VALUES
    ('ROLE_ADMIN'),  ('ROLE_USER');

INSERT INTO users
	(first_name, last_name, email, password, role_id)
	VALUES
    ('Olha', 'Raulet', 'raulet.olha@gmail.com', '$2a$11$mTbgXh6GL/hWlZgzYMB55OrswkrWOC0squWLwJjSVBMp1Lmfbqdk6', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN')),
	('Diane', 'Mallet', 'diane.mallet@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER')),
	('Nathalie', 'Le Boyer', 'natalie.le.boyer@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER')),
	('Édouard', 'Francois', 'edouard.francois@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER')),
	('Joseph', 'Bazin', 'joseph.bazin@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER')),
	('Michelle', 'Gautier', 'michelle.gautier@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER')),
	('Tom', 'Cat', 'tom.cat@gmail.com', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', (SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER'));
