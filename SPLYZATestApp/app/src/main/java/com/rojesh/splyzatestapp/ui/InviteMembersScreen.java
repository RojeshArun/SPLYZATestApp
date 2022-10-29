package com.rojesh.splyzatestapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.rojesh.splyzatestapp.R;
import com.rojesh.splyzatestapp.databinding.InviteMembersLytBinding;
import com.rojesh.splyzatestapp.ui.model.Member;
import com.rojesh.splyzatestapp.ui.model.Team;
import com.rojesh.splyzatestapp.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class InviteMembersScreen extends AppCompatActivity implements
        OnItemSelectedListener, View.OnClickListener {
    @Inject
    ViewModelFactory viewModelFactory;

    private InviteMembersLytBinding mIMViewBinding;
    private String[] mPermissionLevels = {"Coach", "Player Coach", "Player", "Supporter"};
    private String[] mTeamFull = {"Supporter"};
    private String[] mWithoutSupporters = {"Coach", "Player Coach", "Player"};
    private InviteMemberViewModel mMemberViewModel;
    private ArrayAdapter mPermissionSpinner;
    private String inviteURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMemberViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(InviteMemberViewModel.class);
        mIMViewBinding = InviteMembersLytBinding.inflate(getLayoutInflater());
        setContentView(mIMViewBinding.getRoot());
        setTheView();
        observerViewModels();
    }

    private void observerViewModels() {
        mMemberViewModel.getTeams().observe(this, teams -> {
            if (teams != null)
                setTheData(teams);
        });

        mMemberViewModel.getInviteULR().observe(this,inviteURL ->{
            if(inviteURL != null ){
                this.inviteURL = String.valueOf(inviteURL);
            }
        });
    }

    private void gotoQRCodeScreen(String url) {
        Intent qrIntent = new Intent(this,QRCodeInviteScreen.class);
        qrIntent.putExtra("url",url);
        startActivity(qrIntent);
    }

    private void setTheData(Team teams) {
        mIMViewBinding.txtCurrentMemebersVal.setText(teams.members.members + "");
        mIMViewBinding.txtLimitVal.setText(teams.plan.memberLimit + "");

        // Implementing optional feature
        if (teams.plan.supporterLimit != 0) {
            mIMViewBinding.lytSupporters.setVisibility(View.VISIBLE);
            mIMViewBinding.txtCurrentSupportersVal.setText(teams.members.supporters + "");
            mIMViewBinding.txtSupLimitVal.setText(teams.plan.supporterLimit + "");
        } else {
            mIMViewBinding.lytSupporters.setVisibility(View.GONE);
        }
        //Set Invitation Permissions
        //Rule 1 If there are no available members slots ( team is full ), the coach , player
        // coach and Player options should be disabled.
        if (teams.plan.memberLimit == teams.members.members) {
            setTheSpinner(mTeamFull);
        }

        //Rule 2 If supporters are not available for the team , then the supporter options shuold be hidden
        if (teams.plan.supporterLimit == 0) {
            setTheSpinner(mWithoutSupporters);
        }

        //Rule 3 If supporters are available but there are no open slots, the supporters
        // option should be disabled
        if(teams.members.supporters == teams.plan.supporterLimit){
            setTheSpinner(mWithoutSupporters);
        }

    }

    private void setTheView() {
        setSupportActionBar(mIMViewBinding.myToolbar);
        mIMViewBinding.sprnPermissionLevel.setOnItemSelectedListener(this);
        setTheSpinner(mPermissionLevels);
        mIMViewBinding.btnBack.setOnClickListener(this);
        mIMViewBinding.btnShareQr.setOnClickListener(this);
        mIMViewBinding.btnCopyLink.setOnClickListener(this);
    }

    private void setTheSpinner(String[] list) {
        mPermissionSpinner = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        mPermissionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIMViewBinding.sprnPermissionLevel.setAdapter(mPermissionSpinner);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Permission Changed to "+mPermissionSpinner.getItem(position),
                Toast.LENGTH_SHORT).show();
        mMemberViewModel.fetchInviteURL((String) mPermissionSpinner.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_copy_link:
                copyTheURLToClickBoard();
                break;
            case R.id.btn_share_qr:
                gotoQRCodeScreen(inviteURL);
                break;
        }
    }

    private void copyTheURLToClickBoard() {
    }
}
