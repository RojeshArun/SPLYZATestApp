package com.rojesh.splyzatestapp;

import android.content.CursorLoader;
import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeGenerator {

    public QRCodeGenerator() {
    }

    public Bitmap generateQRCode(String url) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(url,
                    BarcodeFormat.QR_CODE,
                    400, 400);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
