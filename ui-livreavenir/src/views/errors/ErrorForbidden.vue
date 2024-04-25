<template>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 text-center">
				<h1 class="m-5">403 Forbidden</h1>
				<h2 class="m-5">
					Désolé, vous n'avez pas le droit d'accéder aux ressources demandées.
				</h2>
				<router-link :to="{ name: 'home' }" class="btn btn-primary m-5"
					>Retour à la page d'accueil</router-link
				>
			</div>
		</div>
	</div>
</template>
