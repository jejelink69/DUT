<?php

	$title='Inscription';

	ob_start();
		echo '<form method=Post Action="index.php?action=inscription"><h1> Inscription </h1>
					<label> Nom <Input name=nom required> </label> <br>
					<label> Mail <Input name=mail required type=email> </label> <br>
					<label> Login <Input name=login required> </label> <br>
					<label> PassWord <Input name=mdp type=password required ><br> </label>
					<button> Inscription </button>
					</form>';
		if(isset($_GET['error'])){
			echo "Erreur login déjà existant ! \n Veuillez en choisir un autre";
		}


	$contenu=ob_get_clean();

	require("Views/layout.php");
?>
