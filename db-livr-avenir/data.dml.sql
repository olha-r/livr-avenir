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
	(name, isbn, author, year, edition, image, description, point, created_at, updated_at, format_id, language_id, category_id, condition_id)
	VALUES
    ('Déjeunons sur lherbe', '978-2-38292-066-4','Guillaume Durand', '2022-04-04', 'Essai', 'dejeunons-sur-l-herbe.jpg', 'Description', '3',
	now(), null, 1, 1, 1, 1) ;
