<?php

	$title='Connexion';

	ob_start();
		echo '<form method=Post Action="index.php?action=connexion"><h1> Connexion </h1>
				<label> Login <Input name=login > </label> <br>
				<label> PassWord <Input name=mdp type=password > </label>
				<button> Se Connecter </button>
				</form>';
		if(isset($resultCo) and $resultCo==False){
						echo '<p> Erreur de mot de passe pour '.$_POST['login'] ;
		} 
			

	$contenu=ob_get_clean();

	require("Views/layout.php");
?>
