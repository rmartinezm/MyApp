package com.unammobile.robertomtz.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ShareDialog shareDialog;
    View viewToPublish;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareDialog = new ShareDialog(this);
        Button fbShareButton = (Button) findViewById(R.id.btn_publish);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");

        viewToPublish = findViewById(R.id.view_to_publish);

        fbShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                textView = (TextView) findViewById(R.id.tv_to_publish);
                textView.setText(((EditText)findViewById(R.id.et_to_publish)).getText().toString());

                Bitmap b = Bitmap.createBitmap(viewToPublish.getLayoutParams().width, viewToPublish.getLayoutParams().height, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(b);
                viewToPublish.layout(0, 0, viewToPublish.getLayoutParams().width, viewToPublish.getLayoutParams().height);
                viewToPublish.draw(c);

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(b)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                if (shareDialog.canShow(SharePhotoContent.class))
                    ShareDialog.show(MainActivity.this, content);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressDialog.dismiss();
    }
}
