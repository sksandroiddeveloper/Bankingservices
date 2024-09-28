package com.digitalfintech.customer.Qrcoder;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Changeapin;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.PinUpdate;
import com.digitalfintech.customer.R;

import java.util.StringTokenizer;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrcodeGenerator extends AppCompatActivity {
    private EditText edtValue;
    private ImageView qrImage;
    private String inputValue;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private AppCompatActivity activity;
    private EditText mColorPreviewWhite, mColorPreviewBlack;
    String memberId;
    TextView name,upid;
    ImageView backbutton;
    Integer i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generator);
        qrImage = findViewById(R.id.qr_image);
        mColorPreviewWhite = findViewById(R.id.preview_selected_firstcolor);
        name = findViewById(R.id.topay);
        backbutton = findViewById(R.id.imageback);
        mColorPreviewBlack = findViewById(R.id.preview_selected_secondcolor);
        upid = findViewById(R.id.scan_id);
        Send(memberId);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void Send(String memberId )
    {
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        memberId =sh.getString("id","");//name
        String named = sh.getString("name","");
        name.setText("To Pay\n"+named);
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Qrmodel> call = apiInterface.qrcode(memberId);
        call.enqueue(new Callback<Qrmodel>() {

            @Override

            public void onResponse(Call<Qrmodel> call, Response<Qrmodel> response) {

                if (response.body().getStatus_message().equalsIgnoreCase( "User Already Have a UPI ID"))
                {
                    String upi =   response.body().data.getUpi_id().toString();
                   //Toast.makeText(QrcodeGenerator.this, "upi"+upi, Toast.LENGTH_SHORT).show();
                    StringTokenizer tokens = new StringTokenizer(upi, ".");
                    String first = tokens.nextToken();// this will contain "Fruit"
                    String second = tokens.nextToken();
                     StringBuilder sb = new StringBuilder();
                    for (i = 0; i < 4; i++)
                        sb.append("*");
                    String l = String.valueOf(sb);///////   split a name in them
                    upid.setText(l+second);
                    inputValue = upi;
                    if (inputValue.length() > 0) {
                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int smallerDimension = Math.min(width, height);
                        smallerDimension = smallerDimension * 3 / 4;
                        qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
                        qrgEncoder.setColorBlack(Color.parseColor(mColorPreviewBlack.getText().toString()));
                        qrgEncoder.setColorWhite(Color.parseColor(mColorPreviewWhite.getText().toString()));
                        try {
                            bitmap = qrgEncoder.getBitmap();
                            qrImage.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(activity, "Something went Wrong Try Again Later!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
//                    progressBar.dismiss();
                    Toast.makeText(QrcodeGenerator.this, "Something went Wrong Try Again Later !! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Qrmodel> call, Throwable t) {
                Toast.makeText(QrcodeGenerator.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });






    }

}


