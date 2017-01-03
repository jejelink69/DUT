<?php
	require('Model/Model.php');

	class VIPManager extends Model{
		
		public function verify_Connexion($id,$mdp){
			$co=$this->executerRequete("Select id,mdp from Membre_staff where id=? and mdp=?",array($id,$mdp));
			return $co->fetch (PDO :: FETCH_ASSOC);
		
		}
		public function getListVip(){
			$vip=$this->executerRequete("Select v.num_VIP, v.Prenom, v.Nom, v.Nationalite, v.Metier, i.CoefImportance from VIP v join Invite i 
										on v.num_VIP=i.num_VIP where Metier!='journaliste'");
			return $vip->fetchAll( PDO :: FETCH_ASSOC);
		}
		public function getJournaliste(){
			$journ=$this->executerRequete("Select v.num_VIP, v.Prenom, v.Nom, v.Nationalite, v.Metier, j.NiveauAccred from VIP v join Journaliste j 
										on v.num_VIP=j.num_VIP where Metier='journaliste'");
			return $journ->fetchAll( PDO :: FETCH_ASSOC);
		}
		public function getVip($id){
			 $vip=$this->executerRequete("select * from VIP v join Invite i on v.num_VIP=i.num_VIP where v.num_VIP=?",array($id));
			$vip=$vip->Fetch (PDO :: FETCH_ASSOC);
			$photo=$this->executerRequete("Select NomFichier, chemin from VIP v join Photo p on v.Photo=p.num_Photo where v.num_VIP=?",array($id));
			$photo=$photo->FETCH ( PDO :: FETCH_ASSOC);
			$vip[]=$photo['Nom'];
			$vip[]=$photo['Chemin'];
			return $vip;
		}
	}
?>
