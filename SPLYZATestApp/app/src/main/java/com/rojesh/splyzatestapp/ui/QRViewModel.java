package com.rojesh.splyzatestapp.ui;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rojesh.splyzatestapp.QRCodeGenerator;
import com.rojesh.splyzatestapp.networking.AppExecutors;

public class QRViewModel extends ViewModel {
    private final MutableLiveData<Bitmap> urlBitpa = new MutableLiveData<>();
    private AppExecutors appExecutors;

    public QRViewModel() {
        appExecutors = new AppExecutors();
    }

    LiveData<Bitmap> getURL() {
        return urlBitpa;
    }

    public LiveData<Bitmap> generateQRCode(String url) {
        // Generate QR Code
        appExecutors.diskIO().execute(() -> {
            QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
            appExecutors.mainThread().execute(() -> {
                urlBitpa.setValue(qrCodeGenerator.generateQRCode(url));
            });

        });
        return urlBitpa;
    }
}
