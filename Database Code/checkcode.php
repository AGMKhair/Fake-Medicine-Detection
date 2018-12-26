<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (isset($_POST['Barcode'])){ 

    $code=$_POST['Barcode'];


       
        $data=$db->checkdata($code);
        if($data){
			 $response["datacheck"]="ok";
			 echo json_encode($response);
			 
        }else{
             $response["datacheck"]="not";
			 echo json_encode($response);

	
        }
		
		

}else{
    $response["error_msg"]="Required parameter (code) is missing";
    echo json_encode($response);
}
?>