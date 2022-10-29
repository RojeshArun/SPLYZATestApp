package com.rojesh.splyzatestapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.rojesh.splyzatestapp.R;
import com.rojesh.splyzatestapp.databinding.ActivityQrcodeInviteScreenBinding;
import com.rojesh.splyzatestapp.databinding.InviteMembersLytBinding;
import com.rojesh.splyzatestapp.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class QRCodeInviteScreen extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private String url;

    ActivityQrcodeInviteScreenBinding mQrBinding;
    private QRViewModel mQRViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQrBinding = ActivityQrcodeInviteScreenBinding.inflate(getLayoutInflater());
        setContentView(mQrBinding.getRoot());
        mQRViewModel = ViewModelProviders.of(this, viewModelFactory).get(QRViewModel.class);

        setTheView();
    }

    private void setTheView() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            url = getIntent().getExtras().getString("url");
            mQRViewModel.generateQRCode(url);
        }
        mQRViewModel.getURL().observe(this, bitmap -> {
            if (bitmap != null) {
                mQrBinding.qrCode.setImageBitmap(bitmap);
            }
        });
    }
}