package org.example.demo2.dto;

import javafx.scene.control.TitledPane;

public class Studies {
    private int id;
    private String description;
    private String title;

    public Studies (){

    }
    public Studies(int id,String description,String title){
        this.id=id;
        this.description=description;
        this.title=title;
    }
    public int getId(){
    return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
}



