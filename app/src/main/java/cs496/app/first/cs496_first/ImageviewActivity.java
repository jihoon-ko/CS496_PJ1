package cs496.app.first.cs496_first;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.chrisbanes.photoview.PhotoView;

public class ImageviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_imageview);
        Intent intent = getIntent();
        PhotoView photoView = (PhotoView) findViewById(R.id.detailPhotoview);
        photoView.setBackgroundColor(Color.BLACK);
        int imageId = intent.getIntExtra("imageSelected", R.drawable.images);
        if(imageId == R.drawable.images){
            Uri uri = intent.getParcelableExtra("imageUri");
            photoView.setImageURI(uri);
        }
        else photoView.setImageResource(imageId);
    }
}
