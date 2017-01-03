<?php

	$title="Détail du film";

	ob_start();

		echo '<h1>Détail du film :'. "$resultFilm[Titre]".'</h1>

					<h2>Information sur le film : </h2>';


						echo '<ul> <li>année de tournage : '.$resultFilm['Année'].'</li>';
						echo '<li>Score du film: '.$resultFilm['Score'].'</li>';
						echo '<li> Nombre de Votes : '.$resultFilm['Votes'].'</li> </ul>';

						if(isset($_SESSION['Login'])){				//Affichage du bouton vote ou messages 
							if($dejaVote){
								echo '<p>Vous avez deja voté pour ce film </p>';
							}else{
								echo '<form Method=Post  Action="./index.php?action=vote ">
										<input type=hidden name=movieID value='.$_GET['ID'].'>';
								echo	'<button> Voter pour ce film </button>
										</form>';
							}
						}else echo '<p>Vous devez vous connecter pour voter</p>';

		echo '<h2>Casting du film : </h2>

				<table>
					<tr>
						<th></th>
						<th>Nom</th>
					</tr>';


		foreach($resultCast as $ligne){
			echo '<tr><td>'.$ligne['Ordinal'].'</td><td>'.$ligne['Nom'].'</td></tr>';
		}

		echo '</table>';

	$contenu=ob_get_clean();

	require("./Views/layout.php");
?>
