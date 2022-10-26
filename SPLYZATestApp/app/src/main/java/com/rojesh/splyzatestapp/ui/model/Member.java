package com.rojesh.splyzatestapp.ui.model;

public class Member {

    public final int total;
    public final int administrators;
    public final int managers;
    public final int editors;
    public final int members;
    public final int supporters;

    public Member(int total, int administrators, int managers,
                  int editors, int members, int supporters) {
        this.total = total;
        this.administrators = administrators;
        this.managers = managers;
        this.editors = editors;
        this.members = members;
        this.supporters = supporters;
    }
}
