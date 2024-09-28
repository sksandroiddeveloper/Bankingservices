package com.digitalfintech.customer.Upipayment;
import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.digitalfintech.customer.databinding.ActivitySecurityupiBinding;

public class Securityupi extends Dialog {
ActivitySecurityupiBinding upisecurity;
    public Securityupi(@NonNull Context context, String s) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        upisecurity = ActivitySecurityupiBinding.inflate(getLayoutInflater());
        setContentView(upisecurity.getRoot());
    }
}