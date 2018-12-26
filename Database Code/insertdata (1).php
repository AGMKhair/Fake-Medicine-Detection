<?php
require_once 'db_function.php';
$db=new db_function();

$response=array();
if (
       isset($_POST['code'])&&
       isset($_POST['name'])&&
	   isset($_POST['description'])&&
	   isset($_POST['manufacturedate'])&&
	   isset($_POST['expdate'])&&
	   isset($_POST['productstatus'])&&
	   isset($_POST['companyName'])&&
	   isset($_POST['countryName'])&&
	    isset($_POST['sellDate']
	   
	   )
	  
	) {
		
		//Description
		//Manufacturedate
	$code = $_POST['code'];
	$name =$_POST['name'];	
	$description =$_POST['description'];	
	$manufacturedate =$_POST['manufacturedate'];	
	$expdate =$_POST['expdate'];	
	$productstatus =$_POST['productstatus'];
	$companyName =$_POST['companyName'];
	$countryName =$_POST['countryName'];
	$sellDate =$_POST['sellDate'];

    
  
     //Barcode,$ProductName,$Manufacturedate,$Expdate,$Description,$CountryName,$CompanyName,$ProductStatus,$SellDate

	  $result=$db->insertData($code,$name,$manufacturedate,$expdate,$description,$countryName,$companyName,$productstatus,$sellDate);
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