package com.unammobile.robertomtz.myapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {

    TextView v;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareDialog = new ShareDialog(this);
        Button fbShareButton = (Button) findViewById(R.id.btn_publish);


        fbShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                v = (TextView) findViewById(R.id.tv_to_publish);

                Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(b);
                v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
                v.draw(c);

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(b)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                if (shareDialog.canShow(SharePhotoContent.class)){
                    shareDialog.show(content);
                }

            }
        });


    }

}
