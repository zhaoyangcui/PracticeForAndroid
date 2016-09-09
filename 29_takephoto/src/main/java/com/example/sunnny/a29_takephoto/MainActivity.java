package com.example.sunnny.a29_takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Environment;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST = 0;
    private static final int SAVE = 1;
    private String photopath = "";
    private Button btn_photo;
    private Button btn_save;
    private ImageView iv_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_photo = (Button) findViewById(R.id.photo);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        btn_save = (Button) findViewById(R.id.save);

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager())!=null){
                    File file_photo = null;
                    file_photo = createImageFile();
                    if (file_photo != null){
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file_photo));
                        startActivityForResult(intent,SAVE);
                    }
                }
            }
        });
    }
    //保存到外部存储设备
    private void addPicToExternalStorage(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(photopath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode && resultCode == RESULT_OK){
            //显示缩略图
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            iv_photo.setImageBitmap(bitmap);
            Log.d("Debug","get Photo");
        }
        if (requestCode == SAVE && resultCode == RESULT_OK){
            setPic();
            addPicToExternalStorage();
        }
    }

    private File createImageFile(){
        String img_name = "myimg.jpg";
        File storage_dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        if (!storage_dir.exists()){
            Log.v("Debug","Success");
            if(!storage_dir.mkdir()){
                Log.d("Debug","Fail to create");
                return null;
            }
        }

        File img_file = new File(storage_dir,img_name);

        photopath = img_file.getPath();
        Log.d("Debug","The path is "+photopath);
        return img_file;
    }
    //当点击Save按钮，在主界面显示缩略图
    private void setPic(){
        int targetW = 500;
        int targetH = 500;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photopath, bmOptions);
        int photoWidth = bmOptions.outWidth;
        int photoHeight = bmOptions.outHeight;
        int scaleFactor = Math.min(photoWidth/targetW, photoHeight/targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(photopath, bmOptions);
        iv_photo.setImageBitmap(bitmap);

    }
}
