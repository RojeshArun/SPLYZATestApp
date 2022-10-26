package com.rojesh.splyzatestapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.rojesh.splyzatestapp.R;
import com.rojesh.splyzatestapp.databinding.InviteMembersLytBinding;
import com.rojesh.splyzatestapp.ui.model.Member;
import com.rojesh.splyzatestapp.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class InviteMembersScreen extends AppCompatActivity implements
        OnItemSelectedListener {
    @Inject
    ViewModelFactory viewModelFactory;

    private InviteMembersLytBinding mIMViewBinding;
    private String[] mPermissionLevels = {"Coach", "Player Coach", "Player", "Supporter"};
    private InviteMemberViewModel mMemberViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMemberViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(InviteMemberViewModel.class);
        mIMViewBinding = InviteMembersLytBinding.inflate(getLayoutInflater());
        setContentView(mIMViewBinding.getRoot());
        setTheView();
        setTheData();
    }

    private void setTheData() {
        mMemberViewModel.getTeams().observe(this, teams ->{
            Member member = teams.members;
            Toast.makeText(this, member.supporters+"", Toast.LENGTH_SHORT).show();

        });
    }

    private void setTheView() {
        setSupportActionBar(mIMViewBinding.myToolbar);
        mIMViewBinding.sprnPermissionLevel.setOnItemSelectedListener(this);
        ArrayAdapter mPermissionSpinner = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, mPermissionLevels);
        mPermissionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIMViewBinding.sprnPermissionLevel.setAdapter(mPermissionSpinner);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
