package com.example.tysgrocery;

public class ProductModel {

        //    String Product N;
        String Product;
        String Price,Description,Quantity;

        ProductModel(){}

        ProductModel(String Product, String Price, String Quantity, String Description){
            this.Product=Product;
            this.Price = Price;
            this.Quantity = Quantity;
            this.Description = Description;
        }

        public String getProduct(){
            return  Product;
        }

        public void setProduct(String Product){
            this.Product=Product;
        }

    public String getPrice(){
        return  Price;
    }

    public void setPrice(String Price){
        this.Price=Price;
    }

    public String getQuantity(){
        return  Quantity;
    }

    public void setQuantity(String Quantity){
        this.Quantity=Quantity;
    }

    public String getDescription(){
        return  Description;
    }

    public void setDescription(String Description){
        this.Description=Description;
    }


}
