package com.example.tysgrocery;

public class ProductModel {

        //    String Product N;
        String Product;

        ProductModel(){}

        ProductModel(String Product){
            this.Product=Product;
        }

        public String getProduct(){
            return  Product;
        }

        public void setProduct(String Product){
            this.Product=Product;
        }


}
