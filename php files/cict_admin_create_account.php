<?php
	require_once 'include/cict_db_users_functions.php';
	$response = array();
	

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		if(isset($_POST['username']) and isset($_POST['password']) and isset($_POST['id']) and isset($_POST['role'])
			and isset($_POST['name']) and isset($_POST['phone']) and isset($_POST['date_created'])
		    and isset($_POST['date_expire'])){
				
			$db = new cict_db_users_functions();
			
			//INITIALIZE
			$username = $_POST['username'];
			$password = $_POST['password'];
			$id = $_POST['id'];
			$role = $_POST['role'];
			$name = $_POST['name'];
			$phone = $_POST['phone'];
			$date_created = $_POST['date_created'];
			$date_expire = $_POST['date_expire'];
			
			//check username if existing
			if($db->getAccount($username)){
				$response["message"] = "Username exists. Account creation denied.";
			}else{
				$db->createUser($id, $username, $password, $date_created, $date_expire, $name, $phone, $role);
				$response["message"] = "Account creation complete. Have a nice day :D.";
			}
		}	
    }		
	echo json_encode($response);
	
?>