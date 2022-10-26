package com.rojesh.splyzatestapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.rojesh.splyzatestapp.R;
import com.rojesh.splyzatestapp.databinding.InviteMembersLytBinding;

public class InviteMembersScreen extends AppCompatActivity  implements
        OnItemSelectedListener{

    InviteMembersLytBinding viewBinding;
    String[] mPermissionLevels = {"Coach","Player Coach","Player","Supporter"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = InviteMembersLytBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        setTheView();
    }

    private void setTheView() {
        setSupportActionBar(viewBinding.myToolbar);
        viewBinding.sprnPermissionLevel.setOnItemSelectedListener(this);
        ArrayAdapter mPermissionSpinner = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,mPermissionLevels);
        mPermissionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewBinding.sprnPermissionLevel.setAdapter(mPermissionSpinner);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
