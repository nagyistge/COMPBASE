package com.kompetenzen.unipotsdam.kompetenzenapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 14/07/2015.
 */
public class CompetenceGroup implements Serializable {

    public Competence upperCompetence;
    public final List<Competence> children= new ArrayList<Competence>();

    public CompetenceGroup (Competence upComp){
        this.upperCompetence= upComp;
    }

}
