package cs496.app.first.cs496_first;

import android.content.Intent;
import android.graphics.Color;
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
        photoView.setImageResource(intent.getIntExtra("imageSelected", R.drawable.style_1_1));
    }
}
