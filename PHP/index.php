<?php
session_name('p1505974');
session_start();

require("Model/FilmManager.php");
$fm= new FilmManager();

if(!isset($_GET['ID']) && !isset($_GET['action'])){
	$result=$fm->getFilms();
	$count=count($result);
	require("Views/films.php");

}elseif(isset($_GET['ID']) && $_GET['ID']>0 ){
	$resultCast=$fm->getCasting($_GET['ID']);
	$resultFilm=$fm->getFilmDetails($_GET['ID']);
	if (isset($_SESSION['Login'])){
		$dejaVote=$fm->dejaVote($_SESSION['Login'],$_GET['ID']);
	}
	require("Views/detailsFilm.php");

}elseif($_GET['action']=='connexion'){
	if (isset($_POST['login']) && isset($_POST['mdp'])){
		$resultCo=$fm->verifyConnexion($_POST['login'],$_POST['mdp']);
		if ($resultCo==False){
			require("./Views/connexion.php");
		}else{
			$_SESSION['Nom']=$resultCo['Nom'];
			$_SESSION['Login']=$resultCo['Login'];
			$admin=$fm->verifyAdmin($_SESSION['Login']);
			$_SESSION['Admin']=$admin;
			header('Location: index.php');
			exit (0);
		}
	}else require("./Views/connexion.php");

}elseif($_GET['action']=='deconnexion'){
	$_SESSION=array();
	session_destroy();
	header('Location: index.php');
	exit (0);

}elseif($_GET['action']=='vote'){
	$vote=$fm->NewVote($_SESSION['Login'],$_POST['movieID']);
	header ('Location: index.php?ID='.$_POST['movieID']);

}elseif($_GET['action']=='class' && isset($_GET['type'])){
	$classFilms=$fm->getClassement($_GET['type']);
	require("Views/classement.php");

}elseif($_GET['action']=='ajout'){
	if(isset ($_POST['titre']) && isset($_POST['an']) && isset($_POST['score'])){
		$titre=$fm->verifyFilm($_POST['titre']);
		if($titre==0){
			$fm->ajoutFilm($_POST['titre'],$_POST['an'],$_POST['score']);
			header('Location: index.php');
		}else{
			header('Location: index.php?action=ajout&error=titre');
		}
	}else{
		require('/Views/ajoutFilm.php');
	}

}elseif($_GET['action']=='inscription'){
	if(isset ($_POST['nom']) && isset($_POST['mail']) && isset($_POST['login']) && isset($_POST['mdp']) ){
		$login=$fm->verifyLogin($_POST['login']);
		if($login==0){
			$fm->inscription($_POST['nom'],$_POST['mail'],$_POST['login'],$_POST['mdp']);
			header('Location: index.php');
		}else {
			header('Location: index.php?action=inscription&error=login');
		}
	}
	require("./Views/inscription.php");

}else{
require("./Views/error.php");
}
?>
