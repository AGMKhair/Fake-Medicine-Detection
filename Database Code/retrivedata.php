<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (isset($_POST['barcode'])){ 

    $code=$_POST['barcode'];
	
	 //--//$qury=$this->conn->query("SELECT * FROM productinfo WHERE ProductStatus='0' AND QRcode='".$code."'");
	 
	 
	    
      
        $getData=$db->checkdata($code);
		
		if($getData){
        $data=$db->retivedata($code);
		
	
        if ($data){
            $response["Barcode"]=$data["Barcode"];
            $response["ProductName"]=$data["ProductName"];
            $response["Manufacturedate"]=$data["Manufacturedate"];
            $response["Expdate"]=$data["Expdate"];
			$response["Description"]=$data["Description"];
			$response["CountryName"]=$data["CountryName"];
			$response["CompanyName"]=$data["CompanyName"];
			$response["ProductStatus"]=$data["ProductStatus"];
			$response["SellDate"]=$data["SellDate"];
			
			$response["error_msg"]="have";
            echo json_encode($response);
			
			$update=$db->updateStatus($code);
			if($update){
				echo json_encode("Change status");
			}else{
				echo json_encode("could not Change status");
			}
			
        }else{
            $response["error_msg"]="Already sell";
            echo json_encode($response);
        }
	}else{
		 $response["error_msg"]="nodata";
         echo json_encode($response);
	}
		


}else{
    $response["error_msg"]="Required parameter (code) is missing";
    echo json_encode($response);
}
?>