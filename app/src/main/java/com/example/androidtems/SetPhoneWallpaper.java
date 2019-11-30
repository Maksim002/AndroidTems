package com.example.androidtems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class SetPhoneWallpaper extends AppCompatActivity {

    private Button botStarted;
    private ImageView imageView;
    private WallpaperManager wallpaperManager;
    private DisplayMetrics displayMetrics;
    private int width, height;
    private Bitmap bitmapE, bitmapB;
    private BitmapDrawable bitmapDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_phone_wallpaper);

        imageView = findViewById(R.id.imageGalery);
        botStarted = findViewById(R.id.botStart);
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        bitmapE = bitmapDrawable.getBitmap();

        botStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetScreenWidthHeight();

                SetBitmapSize();

                wallpaperManager = WallpaperManager.getInstance(SetPhoneWallpaper.this);
                try {
                    wallpaperManager.setBitmap(bitmapB);
                    wallpaperManager.suggestDesiredDimensions(width, height);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public  void  GetScreenWidthHeight(){
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }
    public  void SetBitmapSize(){
        bitmapB = Bitmap.createScaledBitmap(bitmapE, width, height,false);
    }
}
