package com.rojesh.splyzatestapp.ui.model;

public class Team {

    public final String id;
    public final Member members;
    public final Plan plan;

    public Team(String id, Member members, Plan plan) {
        this.id = id;
        this.members = members;
        this.plan = plan;
    }
}
