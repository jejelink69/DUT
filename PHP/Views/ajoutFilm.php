<?php

	$title='Ajout Film';

	ob_start();

			echo '<h1> Entrer un nouveau film : </h1>';

			echo '<form method=Post Action=index.php?action=ajout>
						<label> Titre du film <Input name=titre required> </label> <br>
						<label> Année de sortie <Input name=an required > </label> <br>
						<label> Score <Input name=score required> </label> <br>
						<button> Ajouter </button>
						</form>';

			if (isset($_GET['error'])){
							echo'Erreur ce film existe déjà';
			}


	$contenu=ob_get_clean();

	require('layout.php');

 ?>
