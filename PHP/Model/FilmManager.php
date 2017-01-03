<?php
	require('Model/Model.php');

	class FilmManager extends Model{
		public function  getFilms(){
			$films=$this->executerRequete("SELECT * from Movie Order by Titre");

			return $films -> fetchAll ( PDO :: FETCH_ASSOC );
		}

		public function  getCasting($movieID){
			$reqCast=$this->executerRequete('Select Nom , Ordinal from Actor a join Casting c on a.ActorID=c.ActorID where MovieID= ? Order by Ordinal',array($movieID));

			return $reqCast-> fetchAll ( PDO :: FETCH_ASSOC );
		}

		public function getFilmDetails($movieID){
			$reqFilm=$this->executerRequete('Select * from Movie where MovieID= ? ',array($movieID));

			return $reqFilm-> fetch( PDO :: FETCH_ASSOC );
		}

		public function verifyConnexion($login,$mdp){
			$Connexion=$this->executerRequete("Select Login,Pass,Nom from Utilisateur where Login= ? and Pass= ? ", array($login,$mdp));

			return $Connexion->fetch( PDO :: FETCH_ASSOC);
		}
		public function verifyAdmin($login){
				$admin=$this->executerRequete("Select admin from Utilisateur where Login= ?", array($login));
				$admin=$admin->fetch( PDO::FETCH_ASSOC);
				if($admin['admin']==1)return true;
				else return False;
		}

		public function dejaVote($userID,$filmID){
			$liste=$this->executerRequete("Select MovieID from Vote where UserID= (select UserID from Utilisateur where login= ?) ", array($userID));
			$res=$liste->fetchall( PDO :: FETCH_ASSOC);
			if(isset($res)){
				foreach ($res as $ligne){
					if($ligne['MovieID']==$filmID){
						return True;
					}
				}
			}
			return False;
		}

		public function NewVote($userName,$filmID){
			$userID=$this->executerRequete("Select UserID from Utilisateur where Login=?", array($userName));
			$userID=$userID->fetch( PDO :: FETCH_ASSOC);
			$ajout=$this->executerRequete("INSERT INTO Vote VALUES (?,?)", array($filmID,$userID['UserID']));
			$majNbVote=$this->executerRequete("Update Movie set Votes =Votes+1 where MovieID=? ",array($filmID));
		}

		public function getClassement($order){
			$films=$this->executerRequete('Select * from Movie Order by '.htmlspecialchars($order).' desc');

			return $films->fetchAll( PDO :: FETCH_ASSOC) ;
		}
		public function inscription($nom,$mail,$login,$mdp){
			$sql=$this->executerRequete("select MAX(UserID)+1 id from Utilisateur");
			$sql=$sql->fetch( PDO :: FETCH_ASSOC);
			$id=$sql['id'];
			$id=(int) $id;
			$inscription=$this->executerRequete("insert into Utilisateur (UserID,Login,Pass,Nom,Mail,Admin) values (?,?,?,?,?,?) ", array($id,$login,$mdp,$nom,$mail,0));

		}
		public function verifyLogin($login){
			$login=$this->executerRequete("select count(UserID) log from Utilisateur where login=?",array($login)); //On vérifie que le login n'est pas deja existant,non-sensible à la casse
			$login=$login->fetch( PDO :: FETCH_ASSOC);

			return $login['log'];
		}
		public function ajoutFilm($titre,$annee,$score){
			$sql=$this->executerRequete("select MAX(MovieID)+1 id from Movie");
			$sql=$sql->fetch( PDO :: FETCH_ASSOC);
			$id=$sql['id'];
			$id=(int) $id;
			$film=$this->executerRequete("insert into Movie (MovieID,Titre,Année,Score,Votes) values (?,?,?,?,?) ", array($id,ucfirst($titre),$annee,$score,0));

		}
		public function verifyFilm($titre){
			$sql=$this->executerRequete('select count(movieID) titre from Movie where Titre=?',array(ucfirst($titre))); //Ne fonctionne que pour les Films ajouter à la base par un admin
			$sql=$sql->fetch(PDO::FETCH_ASSOC);

			return $sql['titre'];
		}

	}
?>
