const frMessages = {
	appTitle: "Livr'avenir",
	homepage: {
		mainTitle: "Livr'avenir",
		title: 'Explorez nos livres'
	},
	navbar: {
		homePageItem: 'Accueil',
		adminItem: 'Administrateur',
		signUp: "S'inscrire",
		login: 'Se connecter'
	},
	navbarAdmin: {
		bookItem: 'Livres',
		userItem: 'Utilisateurs',
		hello: 'Bonjour'
	},
	admin: {
		bookForm: {
			titleCreate: 'Ajouter un livre',
			titleUpdate: 'Modifier',
			isbn: 'ISBN',
			bookTitle: 'Titre du livre',
			author: {
				label: 'Auteur(s)',
				option: 'Sélectionner un auteur'
			},
			publisher: {
				label: 'Éditeur',
				option: 'Sélectionner un éditeur'
			},
			publicationYear: 'Année de publication',
			pageCount: 'Nombre de pages',
			language: {
				label: 'Langue',
				option: 'Sélectionner une langue'
			},
			category: {
				label: 'Catégorie',
				option: 'Sélectionner une catégorie'
			},
			summary: 'Description',
			imageCreate: 'Ajouter la couverture du livre',
			imageUpdate: 'Nouvelle image',
			buttonCreate: 'Ajouter',
			buttonUpdate: 'Modifier',
			successMessage: 'Livre a été créé avec succès.',
			errorMessage: "Nous n'avons pas pu créer le livre."
		},
		addAuthorForm: {
			titleCreate: 'Ajouter un nouveau auteur',
			firstName: 'Prénom',
			lastName: 'Nom',
			buttonCreate: 'Ajouter',
			successMessage: 'Auteur a été ajouté avec succès.',
			errorMessage: "Nous n'avons pas pu ajouté cet auteur."
		},
		dashboard: {
			title: 'Livres disponibles',
			addBookBtn: 'Ajouter un livre',
			table: {
				isbn: 'ISBN',
				bookTitle: 'Titre',
				author: 'Auteur(s)',
				publisher: 'Éditeur',
				category: 'Catégorie',
				image: 'Image',
				actions: 'Actions'
			},
			bookInfo: {
				isbn: 'ISBN :',
				author: 'Auteur(s) :',
				publisher: 'Éditeur :',
				publicationYear: 'Année de publication :',
				pageCount: 'Nombre de pages :',
				language: 'Langue :',
				category: 'Catégorie :',
				summary: 'Description :',
				image: 'Image :',
				closeModal: 'Fermer'
			},
			deleteBook: {
				successMessage: 'Livre a été supprimé avec succès.',
				errorMessage: "Nous n'avons pas pu supprimer le livre."
			},
			updateBook: {
				successMessage: 'Livre a été mis à jour avec succès.',
				errorMessage: "Nous n'avons pas pu mettre à jour le livre."
			}
		},
		validationMessages: {
			required: 'Veuillez renseigner ce champ.',
			isISBNValid: 'Veuillez saisir 10 ou 13 caractères.',
			numeric: 'Veuillez saisir seulement des nombres.',
			minLengthTitle: 'Veuillez saisir au moins 2 caractères.',
			maxLengthTitle: 'Veuillez saisir moins de 200 caractères.',
			minMaxLengthPublicationYear: 'Veuillez saisir 4 caractères.',
			maxLengthPageCount: 'Veuillez saisir moins de 5 caractères.',
			minLengthSummary: 'Veuillez saisir au moins 10 caractères.',
			maxLengthSummary: 'Veuillez saisir moins de 1000 caractères.',
			coverImageUrl: 'Veuillez ajouter une image.',
			email:
				"Veuillez saisir votre adresse e-mail au format votrenom{'@'}example.com",
			minLengthFirstName: 'Veuillez saisir au moins 2 caractères.',
			maxLengthFirstName: 'Veuillez saisir moins de 30 caractères.',
			minLengthLastName: 'Veuillez saisir au moins 2 caractères.',
			maxLengthLastName: 'Veuillez saisir moins de 100 caractères.',
			validPassword: 'Mot de passe non valide.',
			sameAsPassword: 'Veuillez saisir les mots de passe identiques.'
		}
	},
	signUpForm: {
		title: "S'inscrire",
		firstName: 'Prénom',
		lastName: 'Nom',
		email: {
			label: 'Email',
			helpText: "ex. : prenom.nom{'@'}domain.com"
		},
		password: {
			label: 'Mot de passe',
			helpText:
				"Au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi {'!@#%&*?'}"
		},
		confirmPassword: 'Confirmer le mot de passe',
		button: "S'inscrire",
		confirmValidationMessage1: 'Merci cliquer sur le lien envoyé à',
		confirmValidationMessage2: 'pour valider votre inscription.'
	},
	signIn: {
		title: 'Se connecter',
		email: 'Email',
		password: 'Mot de passe',
		button: 'Se connecter',
		success1: 'Utilisateur ',
		success2: 'est connecté',
		error: "Le nom d'utilisateur ou le mot de passe est incorrect"
	},
	client: {
		profile: {
			section: {
				userInfo: {
					label: 'User info',
					email: 'Email',
					fullName: 'Nom complet',
					registrationDate: 'Inscrit depuis:'
				},
				offeredBooks: {
					label: 'Livres proposées',
					title: 'Livres à echanger',
					successMessage: 'Votre exemplaire a été supprimé avec succès.',
					errorMessage: "Nous n'avons pas pu supprimer votre exemplaire."
				},
				pointsNumber: {
					label: 'Points number'
				}
			}
		},
		newItemForm: {
			titleCreate: 'Ajouter un livre pour échange',
			description: {
				label: 'Description'
			},
			condition: {
				label: 'Quelle est état du livre?',
				option: 'Sélectionner un état du livre'
			},
			pointsPrice: {
				label: "Points d'échange",
				option: 'Sélectionner le prix du livre'
			},
			buttonCreate: 'Ajouter'
		},
		validationMessages: {
			minLengthDescription: 'Veuillez saisir au moins 10 caractères.',
			maxLengthDescription: 'Veuillez saisir moins de 150 caractères.'
		},
		updateItem: {
			successMessage: "L'exemplaire a été mis à jour avec succès.",
			errorMessage: "Nous n'avons pas pu mettre à jour l'exemplaire."
		},
		createItem: {
			successMessage: 'Votre livre a été ajoutée avec succès.',
			errorMessage: "Nous n'avons pas pu ajoutée le livre."
		},

		bookDetailPage: {
			bookDetail: {
				author: ' (Auteur(s))',
				description: 'Résumé:',
				info: 'Caractéristiques:',
				isbn: 'ISBN: ',
				publicationYear: " L'année de parution: ",
				publisher: 'Edition ',
				pageCount: ' Nombres de pages: ',
				language: 'Langue: ',
				addItemBtn: ' Ajouter un exemplaire'
			},
			bookItemDetail: {
				noItems: 'Aucun exemplaire disponible pour cet échange pour le moment.',
				date: 'Ajouté par ',
				pointsPrice: 'points',
				condition: 'État: ',
				description: 'Mon commentaire: ',
				btnModify: 'Modifier',
				btnDelete: 'Supprimer',
				btnCancel: 'Annuler',
				addToCartBtn: 'Ajouter au panier',
				owner: "C'est votre livre"
			}
		}
	},
	activationAccountPage: {
		activationSuccess:
			'Félicitation, votre account est activer. Vous pouvez vous connecter.',
		activationFailed: "Désolé, votre account n'était pas activer. Réessayer."
	},
	mainLayout: {
		copyright: " Copyright 2024 Livr'avenir"
	},
	errorPages: {
		redirectToHomepage: "Retour à la page d'accueil",
		e401: {
			label: '401 Unauthorized',
			message: 'Désolé, vous n’êtes pas autorisé à accéder à cette page !'
		},
		e403: {
			label: '403 Forbidden',
			message:
				"Désolé, vous n'avez pas le droitnd'accéder aux ressources demandées."
		},
		e404: {
			codeError: '404',
			label: "Oups, voilà qui n'était pas prévu !",
			message: 'Désolé mais cette page est introuvable!'
		}
	},
	errors: {
		fieldErrors: {
			isbn: {
				UniqueIsbn: 'Cet ISBN existe déjà.',
				Length: "L'ISBN doit contenir 10 ou 13 caractères.",
				NotBlank: 'Le champ ISBN ne doit pas être vide.',
				typeMismatch: 'Le champ ISBN ne doit pas être vide.'
			},
			title: {
				typeMismatch: 'Le champ titre ne doit pas être vide.',
				NotBlank: 'Le champ titre ne doit pas être vide.',
				Size: 'Le titre doit comporter entre 2 et 200 caractères.'
			},
			publicationYear: {
				typeMismatch: 'Le champ année de publication ne doit pas être vide.',
				NotBlank: 'Le champ année de publication ne doit pas être vide.'
			},
			pageCount: {
				typeMismatch: 'Le champ nombre de pages ne doit pas être vide.',
				NotBlank: 'Le champ nombre de pages ne doit pas être vide.',
				Size: 'Le nombre de pages ne peut pas dépasser 5 caractères.'
			},
			summary: {
				typeMismatch: 'Le champ description ne doit pas être vide.',
				NotBlank: 'Le champ description ne doit pas être vide.',
				Size: 'La description doit comporter entre 10 et 1000 caractères.'
			},
			coverImageUrl: {
				typeMismatch: "L'image ne doit pas être vide.",
				NotNull: "L'image ne doit pas être vide.",
				ImageSize: "La taille de l'image est trop grande.",
				ImageType: "Type de l'image incorrect."
			},
			publisher: {
				typeMismatch: 'Le champ éditeur ne doit pas être vide.',
				NotNull: 'Le champ éditeur ne doit pas être vide.',
				Positive: "Veuillez saisir seulement des nombres pour l'éditeur."
			},
			categoryId: {
				typeMismatch: 'Le champ catégorie ne doit pas être vide.',
				NotNull: 'Le champ catégorie ne doit pas être vide.',
				Positive: 'Veuillez saisir seulement des nombres pour la catégorie.'
			},
			languageId: {
				typeMismatch: 'Le champ langue ne doit pas être vide.',
				NotNull: 'Le champ langue ne doit pas être vide.',
				Positive: 'Veuillez saisir seulement des nombres pour la langue.'
			},
			authorList: {
				typeMismatch: 'Le champ auteur ne doit pas être vide.',
				NotNull: 'Le champ auteur ne doit pas être vide.'
			},
			description: {
				typeMismatch: 'Le champ description ne doit pas être vide.',
				NotBlank: 'Le champ description ne doit pas être vide.',
				Size: 'La description doit comporter entre 10 et 150 caractères.'
			},
			bookId: {
				typeMismatch: "Livre n'est pas reconnu.",
				NotNull: "Livre n'est pas reconnu.",
				Positive: "Livre n'est pas reconnu."
			},
			pointsPrice: {
				typeMismatch: 'Le champ nombre de points ne doit pas être vide.',
				NotNull: 'Le champ nombre de points ne doit pas être vide.',
				Min: 'Le champ nombre de points doit être au moins de 1.',
				Max: 'Le champ nombre de points ne doit pas dépasser 5.'
			},
			conditionId: {
				typeMismatch: 'Le champ état du livre ne doit pas être vide.',
				NotNull: 'Le champ état du livre ne doit pas être vide.',
				Positive: 'Veuillez saisir seulement des nombres pour état du livre.'
			},
			firstName: {
				typeMismatch: 'Le champ prénom ne doit pas être vide.',
				NotBlank: 'Le champ prénom ne doit pas être vide.',
				Size: 'Le prénom doit comporter entre 2 et 30 caractères.'
			},
			lastName: {
				typeMismatch: 'Le champ nom de famille ne doit pas être vide.',
				NotBlank: 'Le champ nom de famille ne doit pas être vide.',
				Size: 'Le nom de famille doit comporter entre 2 et 100 caractères.'
			},
			email: {
				typeMismatch: 'Le champ email ne doit pas être vide.',
				NotBlank: 'Le champ email ne doit pas être vide.',
				Email: 'Veuillez saisir un email valide.'
			},
			password: {
				typeMismatch: 'Le champ mot de passe ne doit pas être vide.',
				NotBlank: 'Le champ mot de passe ne doit pas être vide.',
				Pattern:
					'Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre, un caractère spécial et doit être compris entre 8 et 42 caractères.'
			}
		}
	}
};

export default frMessages;
