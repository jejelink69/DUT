<?php
	$title='Classement des films';

	ob_start();
		echo '<h1>Voici le classement des films par : '.$_GET ['type'].'</h1><br>';
		echo '<table id=casting>
					<tr>
						<th class=listefilm>Rang</th>
						<th class=listefilm>Titre</th>
						<th class=listefilm><a href="./index.php?action=class&type=Année&nb='.$_GET['nb'].'"> Année</a></th>
						<th class=listefilm> <a href="./index.php?action=class&type=Score&nb='.$_GET['nb'].'"> Score</a></th>
						<th class=listefilm> <a href="./index.php?action=class&type=Votes&nb='.$_GET['nb'].'"> Votes</a></th>
						<th class=listefilm>Détails</th>
					</tr>';
		for( $i = 0 ; $i < $_GET['nb'] ; $i++){									//Affichage du classement en fonction du nombre de films choisi
			echo '<tr>';
			echo '<td>'.($i+1).'</td>';
			echo '<td>'.$classFilms[$i]['Titre'].'</td>';
			echo '<td>'.$classFilms[$i]['Année'].'</td>';
			echo '<td>'.$classFilms[$i]['Score'].'</td>';
			echo '<td>'.$classFilms[$i]['Votes'].'</td>';
			echo '<td> <a class=detail href="index.php?ID='.$classFilms[$i]['MovieID'].'"> détail </a>' ;
			echo '</tr>';
		}
		echo'</table>';
		echo' Afficher :
			<a href="./index.php?action=class&type='.$_GET ['type'].'&nb=10"> 10 Films >>  </a>
			  <a href="./index.php?action=class&type='.$_GET ['type'].'&nb=20"> 20 Films >> </a>
			  <a href="./index.php?action=class&type='.$_GET ['type'].'&nb=50"> 50 Films >> </a>
			  <a href="./index.php?action=class&type='.$_GET ['type'].'&nb='.count($classFilms).'">  Tous les films</a>';

	$contenu=ob_get_clean();
	require("./Views/layout.php");


?>
