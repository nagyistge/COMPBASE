package com.kompetenzen.unipotsdam.kompetenzenapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 07/07/2015.
 */
public class Competence implements Serializable{

    private String name;
    private Boolean isCompulsory;
    private Boolean upper_competence;
    private Boolean fulfilled;
    private Boolean readyToDo;
    private List<int[]> sons= new ArrayList<>();
    private List<int[]> parents= new ArrayList<>();
    private ArrayList<String> comments= new ArrayList<>();

    public Competence(){
        this.name= "";
        this.isCompulsory= false;
        this.upper_competence= false;
        this.fulfilled= false;
        this.readyToDo= false;
    }

    public void setName(String n){
        this.name= n;
    }

    public void setIsCompulsory(Boolean isC){
        this.isCompulsory= isC;
    }

    public void setUpper_competence(Boolean up_comp){
        this.upper_competence= up_comp;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getIsCompulsory(){
        return this.isCompulsory;
    }

    public Boolean getUpper_competence(){
        return this.upper_competence;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public void setReadyToDo(Boolean readyToDo) {
        this.readyToDo = readyToDo;
    }

    public void addParent(int[] parent) {
        parents.add(parent);
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public Boolean getReadyToDo() {
        return readyToDo;
    }

    public List<int[]> getSons() {
        return sons;
    }

    public void addSon (int[] sonKey){
        sons.add(sonKey);
    }

    public List<int[]> getParents() {
        return parents;
    }

    public void addComment(String comment){
        comments.add(comment);
    }

    public ArrayList<String> getComments() {return comments;}
}
