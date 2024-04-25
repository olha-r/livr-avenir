<template>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 text-center">
				<h1 class="m-5">404</h1>
				<h2 class="m-5">Oups, voilà qui n'était pas prévu !</h2>

				<p class="m-5">Désolé mais ce lien est introuvable !</p>
				<router-link :to="{ name: 'home' }" class="btn btn-primary m-5"
					>Retour à la page d'accueil</router-link
				>
			</div>
		</div>
	</div>
</template>
