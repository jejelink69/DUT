<!DOCTYPE HTML>
<html>
	<head>
		<meta charset='utf-8' />
		<title> <?php echo "$title" ?> </title>
		<link rel='stylesheet' type='text/css' href='./Web/CSS/superstyle.css' />

	</head>
	<body>

		<header>
			<a class='menu' href='index.php'> Accueil </a>
			<a class='menu' href='index.php?action=class&type=Votes&nb=10'> Classement </a>
			<?php if (isset($_SESSION['Login'])){
									echo'<a class=menu  href="index.php?action=deconnexion">Deconnexion </a>';
									if ($_SESSION['Admin']==1){
										echo '<a class=menu href="index.php?action=ajout"> AjouterFilm </a>';
									}
					}else echo '<a class=menu href="index.php?action=connexion"> Connexion </a>';?>
			<?php if (isset($_SESSION['Login']))echo '<p id=bonjour >Bonjour,'.$_SESSION['Nom'];'</p>' ?>	
			<?php if (!isset($_SESSION['Login'])) echo '<a class=menu href="index.php?action=inscription">Inscription</a>';?>
		</header>
		<div>

			<?php echo "$contenu" ?>

		</div>

	</body>
</html>
