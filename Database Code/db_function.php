<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
class  db_function{
    private  $conn;

    function  __construct()
    {

        require_once  'db_connection.php';

        $db=new db_connection();

        $this->conn=$db->connect();
    }

    function __destruct(){

    }

   
	public function insertData($Barcode,$ProductName,$Manufacturedate,$Expdate,$Description,$CountryName,$CompanyName,$ProductStatus,$SellDate){
		
		//$stmt=$this->conn->prepare(") VALUES(?,?,?,?,?,?)");
		
	/*	$stmt=$this->conn->prepare("INSERT INTO user (Phone,Name,Birthday,Address) VALUES (?,?,?,?)");
        $stmt->bind_param("ssss",$phone,$name,$birthday,$address);
        $result=$stmt->execute();
        $stmt->close();*/
	
		$stmt=$this->conn->prepare("INSERT INTO CompanyInfo(Barcode,ProductName,Manufacturedate,Expdate,Description,CountryName,CompanyName,ProductStatus,SellDate) VALUES(?,?,?,?,?,?,?,?,?)");
		$stmt->bind_param("sssssssss",$Barcode,$ProductName,$Manufacturedate,$Expdate,$Description,$CountryName,$CompanyName,$ProductStatus,$SellDate);
	    $result=$stmt->execute();
		$stmt->close();
		
	
		if ($result) {
            return true;
        } else {
            return false;
        }
		
	}
	
	
	
	public function retivedata($code){
		
		
		$qury=$this->conn->query("SELECT * FROM CompanyInfo WHERE  Barcode='".$code."'");
			
		return $qury->fetch_assoc();
		
	}
	
	public function retriveLastSellDate($code){
		
		
		$qury=$this->conn->query("SELECT ProductName,SellDate FROM CompanyInfo WHERE 	ProductStatus=1 AND Barcode='".$code."'");
			
		return $qury->fetch_assoc();
		
	}
	
	
	public function  checkdata($code){
		$qury=$this->conn->query("SELECT * FROM CompanyInfo WHERE Barcode='".$code."'");
		
		if($qury->fetch_assoc()>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public function updateStatus($code){
		$qury=$this->conn->query("UPDATE CompanyInfo SET ProductStatus = '1' WHERE Barcode = '".$code."'");
		
		if($qury){
			return true;
		}else{
			return false;
		}
		
	}
	
	public function updateDate($code,$date){
		$qury=$this->conn->query("UPDATE CompanyInfo SET SellDate ='".$date."' WHERE Barcode = '".$code."'");
		
		if($qury){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	
	//SELECT * FROM productinfo WHERE ProductStatus='0' AND QRcode='572247730700';
	
	
	
	

	
	
}

	

?>