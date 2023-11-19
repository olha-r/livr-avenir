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

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'admin-dashboard' }"
                                >Livres</RouterLink
                            >
                        </li>
                        <li>
                            <!-- <li class="nav-item">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'manage-users' }"
                                >Users</RouterLink
                            >
                        </li> -->
                        </li>
                    </ul>
                </div>
                <div class="navbar-header navbar-left pull-left">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
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