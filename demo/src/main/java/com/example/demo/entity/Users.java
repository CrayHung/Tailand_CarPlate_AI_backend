package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lprtable")
public class Users{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cameraid")
    private String cameraid;

    @Column(name = "recognitiontimestr")
    private String recognitiontimestr;

    @Column(name = "platenumber")
    private String platenumber;

    @Column(name = "imagepath")
    private String imagepath;

    //Constructor
    public Users(){
        super();
    }


    public Users(int id,String cameraid,String recognitiontimestr,String platenumber,String imagepath){
        super();
        this.id=id;
        this.cameraid=cameraid;
        this.recognitiontimestr=recognitiontimestr;
        this.platenumber=platenumber;
        this.imagepath=imagepath;
    }


    public int getid(){
        return id;
    }
    public void setid(int id){
        this.id=id;
    }

    public String getcameraId(){
        return cameraid;
    }

    public void setcameraId(String cameraid){
        this.cameraid=cameraid;
    }

    public String getrecognitiontimestr(){
        return recognitiontimestr;
    }

    public void setrecognitiontimestr(String recognitiontimestr){
        this.recognitiontimestr=recognitiontimestr;
    }

    public String getplatenumber(){
        return platenumber;
    }

    public void setplatenumber(String platenumber){
        this.platenumber=platenumber;
    }

    public String getimagepath(){
        return imagepath;
    }

    public void setimagepath(String imagepath){
        this.imagepath=imagepath;
    }


    @Override
    public String toString() {
      return "{" +
        " id='" + getid() + "'" +
        ", plateNumber='" + getplatenumber() + "'" +
        ", recognitionTimeStr='" + getrecognitiontimestr() + "'" +
        ", imagePath='" + getimagepath() + "'" +
        ", cameraId='" + getcameraId() + "'" +
        "}";
    }
}