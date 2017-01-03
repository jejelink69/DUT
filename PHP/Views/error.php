<?php
	$title='Error';

	ob_start();
	
		echo '<h1><strong> Erreur </strong> : Identifiants de film incorrects !</h1>'; 

	$contenu=ob_get_clean();
	
	require("./Views/layout.php");
?>