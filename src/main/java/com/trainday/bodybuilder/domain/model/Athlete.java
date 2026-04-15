package com.trainday.bodybuilder.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Document(collection = "athlete")
public class Athlete {

    @Id
    private String id;
    private String cpf;
    private String name;
    private String email;
    private Long age;
    private Double height;
    private Double weight;
    @Field("percentagefat")
    private Long percentageFat;
    private String userId;

    public Athlete(){}

    public Athlete(
        String id, String cpf, String name, String email, Long age, Double height, Double weight, Long percentageFat, String userId
    ){
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.percentageFat = percentageFat;
        this.userId = userId;
    }


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getCPF(){
        return cpf;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

      public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight){
        this.weight = weight;
    }

    public Long getpercentageFat(){
        return percentageFat;
    }

    public void setpercentageFat(Long percentageFat){
        this.percentageFat = percentageFat;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}
