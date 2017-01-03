<?php
session_name('p1505974');
session_start();

require("Model/VIPManager.php");
$vm= new VIPManager();
if(!isset($_GET['action'])){
	require("./Views/connexion.php");	
}else if($_GET['action']=='connexion'){
	if( isset($_POST['login']) && isset($_POST['mdp'])){
		$id=$_POST['login'];
		$resCo=$vm->verify_Connexion($id,$_POST['mdp']);
		echo "$resCo";
		if($resCo==false){
			require("./Views/connexion.php");
		}else{
			$_SESSION['Login']=$resCo['id'];
			header("Location: index.php?action=accueil");
			exit(0);
		}	
	}
}else if($_GET['action']=='accueil'){
	$listVip=$vm->getListVip();
	$journ=$vm->getJournaliste();
	require("./Views/accueil.php");
}else if($_GET['action']=='details'){
	if(isset($_GET['id'])){
		$vip=$vm->getVip($_GET['id']);
		require('./Views/detailVip.php');
	}
}
	

	

?>
