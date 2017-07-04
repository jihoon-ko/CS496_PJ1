package cs496.app.first.cs496_first;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

public class ImageviewActivity extends AppCompatActivity implements View.OnClickListener{

    private FABToolbarLayout layout;
    private View one, two, three, four;
    private FloatingActionButton fab;
    private boolean visibleToolbar = false;

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
            String filePath = uri.toString();
            String filename = filePath.substring(filePath.lastIndexOf('/')+1, filePath.length());
            Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
        }
        else photoView.setImageResource(imageId);

        layout = (FABToolbarLayout) findViewById(R.id.fabtoolbar);
        fab = (FloatingActionButton) findViewById(R.id.fabtoolbar_fab);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                layout.show();
                visibleToolbar = true;
            }
        });
        one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("one");
            }
        });
        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("two");
            }
        });
        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("three");
            }
        });
        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("four");
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(visibleToolbar){
            visibleToolbar = false;
            layout.hide();
        }
        else this.finish();
    }
    @Override
    public void onClick(View v){
    }
}
