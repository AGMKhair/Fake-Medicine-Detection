<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (
       isset($_POST['code'])&&
       isset($_POST['date'])
	  
	) {
		
		
	$code = $_POST['code'];
	$date =$_POST['date'];	
	
    
  


	  $result=$db->updateDate($code,$date);
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