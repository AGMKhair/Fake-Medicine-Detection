<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (isset($_POST['code'])){ 

    $code=$_POST['code'];
        $getData=$db->retriveLastSellDate($code);	
		if($getData){    
            $response["ProductName"]=$getData["ProductName"];
			$response["SellDate"]=$getData["SellDate"];
            echo json_encode($response);			
        }
	else{
		 $response["error_msg"]="nodata";
         echo json_encode($response);
	}
}else{
    $response["error_msg"]="Required parameter (code) is missing";
    echo json_encode($response);
}
?>