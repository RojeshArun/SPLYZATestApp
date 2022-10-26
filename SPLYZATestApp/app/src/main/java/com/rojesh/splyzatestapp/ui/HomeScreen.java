package com.rojesh.splyzatestapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rojesh.splyzatestapp.R;
import com.rojesh.splyzatestapp.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding viewBindig;
    private InviteMemberViewModel mInviteMemberVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBindig = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(viewBindig.getRoot());

        viewBindig.btnInvite.setOnClickListener(v -> {
            //Goto Invite Screen
            Intent intent = new Intent(HomeScreen.this,InviteMembersScreen.class);
            this.startActivity(intent);
        });

    }
}