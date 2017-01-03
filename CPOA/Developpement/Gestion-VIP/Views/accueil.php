<?php

	$title="Accueil-Gestion VIP";
	
	ob_start();
	echo '<h1>Accueil Gestion de VIP </h1>
			<h2>Liste de VIP déjà existant</h2>';
			echo'<table> <th> Numéro</th> <th> Prenom</th> <th> Nom</th> <th>Métier </th> <th> Nationalité </th> <th>Coefficient d\'importance </th> <th> </th> <th> </th>';
			foreach($listVip as $ligne){
				echo '<tr>';
				echo '<td>'.$ligne['num_VIP'].'</td>';
				echo '<td>'.$ligne['Prenom'].'</td>';
				echo '<td>'.$ligne['Nom'].'</td>';
				echo '<td>'.$ligne['Metier'].'</td>';
				echo '<td>'.$ligne['Nationalite'].'</td>';
				echo '<td>'.$ligne['CoefImportance'].'</td>';
				echo '<td> <a href="">Détails</a></td>';
				echo '<td> <a href=index.php?action=details&id='.$ligne['num_VIP'].'>Modifier/Supprimer</a></td>';
				echo '</tr>';
			}
			echo '</table>';
	echo('	<h2>Liste de journalistes déjà existant</h2>');
			echo'<table> <th> Numéro</th> <th> Prenom</th> <th> Nom</th> <th> Nationalité</th> <th>Accreditation</th> <th> </th> <th> </th>';
			foreach($journ as $ligne){
				echo '<tr>';
				echo '<td>'.$ligne['num_VIP'].'</td>';
				echo '<td>'.$ligne['Prenom'].'</td>';
				echo '<td>'.$ligne['Nom'].'</td>';
				echo '<td>'.$ligne['Nationalite'].'</td>';
				echo '<td>'.$ligne['NiveauAccred'].'</td>';
				echo '<td> <a href=index.php?action=details&id='.$ligne['num_VIP'].'>Détails</a></td>';
				echo '<td> <a href="">Modifier/Supprimer</a></td>';
				echo '</tr>';
			}
	$contenu=ob_get_clean();
	
	require("layout.php");







?>