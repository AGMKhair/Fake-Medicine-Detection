package com.example.dcl.qrcodescanner.Model;
import java.util.List;

public class QR {


    /* $response["Barcode"]=$data["Barcode"];
            $response["ProductName"]=$data["ProductName"];
            $response["Manufacturedate"]=$data["Manufacturedate"];
            $response["Expdate"]=$data["Expdate"];
			$response["Description"]=$data["Description"];
			$response["CountryName"]=$data["CountryName"];
			$response["CompanyName"]=$data["CompanyName"];
			$response["ProductStatus"]=$data["ProductStatus"];
			$response["SellDate"]=$data["SellDate"];

			$response["error_msg"]="have";*/
    private String Barcode;
    private String ProductName;
    private String Manufacturedate;
    private String Expdate;
    private String Description;
    private String CountryName;
    private String CompanyName;
    private String ProductStatus;
    private String SellDate;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    private String error_msg;



    public QR(String barcode, String productName, String manufacturedate, String expdate, String description, String countryName, String companyName, String productStatus, String sellDate) {
        Barcode = barcode;
        ProductName = productName;
        Manufacturedate = manufacturedate;
        Expdate = expdate;
        Description = description;
        CountryName = countryName;
        CompanyName = companyName;
        ProductStatus = productStatus;
        SellDate = sellDate;
    }

    public QR() {
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getManufacturedate() {
        return Manufacturedate;
    }

    public void setManufacturedate(String manufacturedate) {
        Manufacturedate = manufacturedate;
    }

    public String getExpdate() {
        return Expdate;
    }

    public void setExpdate(String expdate) {
        Expdate = expdate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String productStatus) {
        ProductStatus = productStatus;
    }

    public String getSellDate() {
        return SellDate;
    }

    public void setSellDate(String sellDate) {
        SellDate = sellDate;
    }
}
