<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (
       isset($_POST['code'])&&
       isset($_POST['name'])&&
	   isset($_POST['elemet'])&&
	   isset($_POST['empdate'])&&
	   isset($_POST['expdate'])&&
	   isset($_POST['productstatus'])
	  
	) {
		
		
	$code = $_POST['code'];
	$name =$_POST['name'];	
	$elemet =$_POST['elemet'];	
	$empdate =$_POST['empdate'];	
	$expdate =$_POST['expdate'];	
	$productstatus =$_POST['productstatus'];

    
  


	  $result=$db->insertData($code,$name,$elemet,$empdate,$expdate,$productstatus);
	  if($result){
		$response["insert"]="Insert";
		echo json_encode($response);
	  }else{
		$response["insert"]="faile";
		echo json_encode($response);
	  }
	  
  
	
    
} else {
    $response["error_msg"]="Required parameter (data) is missing";
    echo json_encode($response);
}
?>