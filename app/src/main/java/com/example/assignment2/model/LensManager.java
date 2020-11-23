package com.example.assignment2.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LensManager {
    private ArrayList<Lens> lens;

    private static LensManager instance;

    private LensManager(){
        lens = new ArrayList<>();
        lens.add(new Lens("Canon", 1.8, 50));
        lens.add(new Lens("Tamron", 2.8, 90));
        lens.add(new Lens("Sigma", 2.8, 200));
        lens.add(new Lens("Nikon", 4, 200));
    }

    public static LensManager getInstance(){
        if(instance == null){
            instance = new LensManager();
        }
        return instance;
    }


    public void add(Lens inputLens) {

        lens.add(inputLens);
    }

    public void removeByIndex(int index){
        lens.remove(retrieve(index));
    }

    public int size(){

        return lens.size();
    }
    public Lens retrieve(int i ){
        return lens.get(i);
    }
    public int returnint(int i){
        return i;
    }
    public void EditLen(String Editmake, Double EditAperture,int EditFocal,int index){

        removeByIndex(index);
        Lens editlen = new Lens(Editmake,EditAperture,EditFocal);
        lens.add(editlen);

    }

}
