package com.example.dcl.qrcodesellerapp.Model;

import java.util.List;

public class QR {
    private String ProductName;
    private String Material;
    private String EmpDate;
    private String ExpDate;
    private String ProductStatus;
    private String error_msg;
    private String SellDate;

    public String getSellDate() {
        return SellDate;
    }

    public void setSellDate(String sellDate) {
        SellDate = sellDate;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }


    public String getProductName() {
        return ProductName;
    }


    public void setProductName(String productName) {
        ProductName = productName;
    }


    public String getEmpDate() {
        return EmpDate;
    }

    public void setEmpDate(String empDate) {
        EmpDate = empDate;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String productStatus) {
        ProductStatus = productStatus;
    }
}
