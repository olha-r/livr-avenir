TRUNCATE TABLE book_items, book_authors, books, conditions, categories, publishers, languages, authors, orders, order_status,verification_tokens, users, roles, addresses, cities;

INSERT INTO roles
	(code_role, role_name)
	VALUES
    ('ROLE_ADMIN', 'ADMIN'),  ('ROLE_USER', 'USER'), ('FAKE_ROLE', 'FAKE');

INSERT INTO languages 
	(language_name, iso_code, logical_order)
	VALUES
	('Autre', 'N/a', 1),
    ('Français', 'fr', 2),
    ('Anglais', 'en', 3),
    ('Arabe', 'ar', 4),
    ('Bulgar', 'bg', 5),
    ('Tchèque', 'cs', 8), 
    ('Danois', 'da', 9), 
    ('Allemand', 'de', 10), 
    ('Grec', 'el', 11), 
    ('Espagnol', 'es', 12), 
    ('Estonien', 'et', 13),
    ('Basque', 'eu', 14),
    ('Finlandais', 'fi', 15),
    ('Hongrois', 'hu', 16),
    ('Arménien', 'hy', 17),
    ('Italien', 'it', 18),
    ('Japonais', 'ja', 19),
    ('Géorgien', 'ka', 20),
    ('Coréen', 'ko', 21),
    ('Latin', 'la', 22),
    ('Lituanien', 'lt', 23),
    ('Letton', 'lv', 24),
    ('Norvégien', 'no', 25),
    ('Polonais', 'pl', 26),
    ('Portugais', 'pt', 27),
    ('Slovaque', 'sk', 28),
    ('Slovène', 'sl', 29),
    ('Albanais', 'sq', 30),
    ('Serbe', 'sr', 31),
    ('Suédois', 'sv', 32),
    ('Turc', 'tr', 33),
    ('Ukrainien', 'uk', 34),
    ('Vietnamien', 'vi', 35),
    ('Chinois', 'zh', 36);

INSERT INTO categories 
	(category_code, category_name)
	VALUES 
	('literature', 'Littérature'), ('children-teens', 'Jeunesse'), ('comics', 'Bandes dessinées'), ('leisure-nature-travel','Loisir - Nature - Voyage'), ('art-music-cinema','Art - Musique - Cinéma'),('self-help', 'Développement personnel'), ('it','Informatique'),('other','Autre');

INSERT INTO publishers 
	(publisher_code, publisher_name)
	VALUES 
	('essai', 'Essai'),
    ('dargaud', 'Dargaud'),
    ('gallimard', 'Gallimard'),
    ('gallimard-jeunesse', 'Gallimard Jeunesse'),
    ('lgf', 'Lgf'),
    ('pocket', 'Pocket'),
    ('folio', 'FOLIO'),
    ('j-ai-lu', 'J''AI LU'),
    ('un-monde-different', 'Un monde different'),
    ('addison-wesley-professional', 'Addison-Wesley Professional'),
    ('albin-michel','Albin Michel'),
    ('flammarion','Flammarion'),
    ('spinelle','Spinelle'),
    ('actes-sud','Actes Sud'),
    ('seuil','Seuil'),
    ('calmann-levy','Calmann Levy'),
    ('stock','Stock'),
    ('robert-laffont','Robert Laffont'),
    ('denoël','Denoël'),
    ('jc-lattes','JC Lattès'),
    ('p-o-l','P.O.L'),
    ('mercure-de-france','Mercure de France'),
    ('les-editions-de-minuit','Les Éditions de Minuit'),
    ('arthaud','Arthaud'),
    ('dunod','Dunod'),
    ('solar','Solar'),
    ('publiwiz','Publiwiz');


INSERT INTO authors 
	(author_code, first_name, last_name)
	VALUES 
	('guillaume-durand', 'Guillaume', 'Durand'),
    ('albert-camus', 'Albert', 'Camus'),
    ('alexandre-dumas', 'Alexandre', 'Dumas'),
    ('edgar-allan-poe', 'Edgar Allan', 'Poe'),
    ('victor-hugo', 'Victor', 'Hugo'),
    ('virginie-grimaldi', 'Virginie', 'Grimaldi'),
    ('marion-montaigne', 'Marion', 'Montaigne'),
    ('muriel-barbery', 'Muriel', 'Barbery'),
    ('j-k-rowling', 'J.K.', 'Rowling'),
    ('sylvain-tesson', 'Sylvain', 'Tesson'),
    ('gustave-flaubert', 'Gustave', 'Flaubert'),
    ('danielle-steel', 'Danielle', 'Steel'),
    ('joshua-bloch', 'Joshua', 'Bloch'),
    ('robert-kiyosaki', 'Robert', 'Kiyosaki'),
    ('anna-stuart', 'Anna', 'Stuart'),
    ('leila-slimani', 'Leïla', 'Slimani'),
    ('hal-elrod', 'Hal', 'Elrod');
    

INSERT INTO conditions 
	(condition_code, condition_name, logical_order)
	VALUES
	('as_new', 'Comme neuf', 1), ('very_good', 'Très bon', 2), ('good', 'Bon', 3), ('satisfying', 'Satisfaisant', 4);
