<script>
export default {
    name: "HeaderComponent",
};
</script>

<template>
    <header>
        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">
                <RouterLink class="navbar-brand" :to="{ name: 'home' }"
                    ><img
                        src="../../assets/images/logo/logo.png"
                        alt="logo"
                        style="height: 35px"
                /></RouterLink>
                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'admin-dashboard' }"
                                >Livres</RouterLink
                            >
                        </li>
                        <li class="nav-item">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'manage-users' }"
                                >Users</RouterLink
                            >
                        </li>

                        <li class="nav-item">
                            <RouterLink class="nav-link" :to="{ name: 'login' }"
                                ><i class="bi bi-box-arrow-right"></i
                            ></RouterLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
</template>