<script>
export default {
    name: 'HeaderComponent',
}
</script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <RouterLink class="navbar-brand" :to="{ name: 'home' }">Livr'avenir</RouterLink>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                      <RouterLink class="nav-link" :to="{ name: 'home' }">Accueil</RouterLink>
                    </li>
                    <li class="nav-item">
                      <RouterLink class="nav-link" :to="{ name: 'add-book' }">Ajouter un livre</RouterLink>
                    </li>
                    <li class="nav-item">
                      <RouterLink class="nav-link" :to="{ name: 'add-book-final' }">Ajouter un livre 2</RouterLink>
                    </li>
                    
                    <li class="nav-item">
                        <RouterLink class="nav-link" :to="{ name: 'register' }">S'inscrire</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink class="nav-link" :to="{ name: 'login' }">Se connecter</RouterLink>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
</template>