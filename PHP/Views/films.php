<?php 
	$title="Liste des films";
	
	
	
	ob_start();
		echo '<h1>Liste des films </h1>
				
				<p>'.$count.' film(s) trouvé(s) dans la base de données </p>
				
				<table id=casting>
					<tr>
						<th class=listefilm>Titre</th> 
						<th class=listefilm>Année</th> 
						<th class=listefilm>Score</th> 
						<th class=listefilm>Votes</th>
						<th class=listefilm>Détails</th>
					</tr>';				
		foreach ($result as $ligne){
			echo '<tr>';
			echo '<td>'.$ligne['Titre'].'</td>';
			echo '<td>'.$ligne['Année'].'</td>';
			echo '<td>'.$ligne['Score'].'</td>';
			echo '<td>'.$ligne['Votes'].'</td>';
			echo '<td> <a class=detail href="index.php?ID='.$ligne['MovieID'].'"> détail </a>' ; 
			echo '</tr>';
		}
					
		echo '</table>';
	$contenu=ob_get_clean();
	require("Views/layout.php");
?>