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
    }
  };
  
export default frMessages;