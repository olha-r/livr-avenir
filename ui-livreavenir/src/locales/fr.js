const frMessages = {
    homepage: {
      mainTitle: "Livr'avenir",
      title: "Explorez nos livres"

    },
    navbar: {
        homePageItem: "Accueil",
        adminItem: "Administrateur",
        signUp: "S'inscrire",
        login: "Se connecter"
    },
    navbarAdmin: {
        bookItem: "Livres",
        userItem: "Utilisateurs",
        hello: "Bonjour",
    },
    admin: {
        bookForm: {
            titleCreate: "Ajouter un livre",
            titleUpdate: "Modifier",
            isbn: "ISBN",
            bookTitle: "Titre du livre",
            author: {
                label: "Auteur(s)",
                option: "Sélectionner un auteur"
            },
            publisher: {
                label: "Éditeur",
                option: "Sélectionner un éditeur"
            },
            publicationYear: "Année de publication",
            pageCount: "Nombre de pages",
            language: {
                label: "Langue",
                option: "Sélectionner une langue"
            },
            category: {
                label: "Catégorie",
                option: "Sélectionner une catégorie"
            },
            summary: "Description",
            imageCreate: "Ajouter la couverture du livre",
            imageUpdate: "Nouvelle image",
            buttonCreate: "Ajouter",
            buttonUpdate: "Modifier",

        },
        dashboard: {
            title: "Livres disponibles",
            addBookBtn: "Ajouter un livre",
            table: {
                isbn: "ISBN",
                bookTitle: "Titre",
                author: "Auteur(s)",
                publisher: "Éditeur",
                category: "Catégorie",
                image: "Image",
                actions: "Actions"
            },
            bookInfo: {
                isbn: "ISBN :",
                author: "Auteur(s) :",
                publisher: "Éditeur :",
                publicationYear: "Année de publication :",
                pageCount: "Nombre de pages :",
                language: "Langue :",
                category: "Catégorie :",
                summary: "Description :",
                image: "Image :"
            }
        },
        validationMessages: {
            required: "Veuillez renseigner ce champ.",
            isISBNValid:"Veuillez saisir 10 ou 13 caractères.",
            numeric: "Veuillez saisir seulement des nombres.",
            minLengthTitle: "Veuillez saisir au moins 2 caractères.",
            maxLengthTitle: "Veuillez saisir moins de 200 caractères.",
            minMaxLengthPublicationYear: "Veuillez saisir 4 caractères.",
            maxLengthPageCount: "Veuillez saisir moins de 5 caractères.",
            minLengthSummary: "Veuillez saisir au moins 10 caractères.",
            maxLengthSummary: "Veuillez saisir moins de 1000 caractères.",
            coverImageUrl: "Veuillez ajouter une image.",
            email: "Veuillez saisir votre adresse e-mail au format votrenom{'@'}example.com",
            minLengthFirstName: "Veuillez saisir au moins 2 caractères.",
            maxLengthFirstName: "Veuillez saisir moins de 30 caractères.",
            minLengthLastName: "Veuillez saisir au moins 2 caractères.",
            maxLengthLastName: "Veuillez saisir moins de 100 caractères.",
            validPassword: "Mot de passe non valide.",
            sameAsPassword:"Veuillez saisir les mots de passe identiques."
        }
    },
    signUpForm: {
            title: "S'inscrire",
            firstName: "Prénom",
            lastName: "Nom",
            email: {
                label:"Email",
                helpText: "ex. : prenom.nom{'@'}domain.com"
            },
            password: 
            {
                label: "Mot de passe",
                helpText: "Au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi {'!@#%&*?'}"
            },
            confirmPassword: "Confirmer le mot de passe",
            button: "S'inscrire"
    },
    signIn: {
            title: "Se connecter",
            email: "Email",
            password:  "Mot de passe",
            button: "Se connecter"
    },
    client: {
        newItemForm: {
            titleCreate: "Ajouter un livre pour échange",
            description: {
                label: "Description", 
            },
            condition: {
                label: "Quelle est état du livre?",
                option: "Sélectionner un état du livre"
            },
            pointsPrice: {
                label: "Points d'échange",
                option: "Sélectionner un état du livre"
            },
            buttonCreate: "Ajouter",
        },
        validationMessages: {
            minLengthDescription: "Veuillez saisir au moins 10 caractères.",
            maxLengthDescription: "Veuillez saisir moins de 1000 caractères.",
        }
    }
  };
  
export default frMessages;