package cs496.app.first.cs496_first;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class ImageviewActivity extends AppCompatActivity implements View.OnClickListener{

    private FABToolbarLayout layout;
    private View one, two, three, four;
    private FloatingActionButton fab;
    private boolean visibleToolbar = false;
    private boolean isDrawable = false;
    private String filePath;
    private int fileDrawable;
    Uri uri;
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
            isDrawable = false;
            uri = intent.getParcelableExtra("imageUri");
            photoView.setImageURI(uri);
            filePath = uri.toString();
            String filename = filePath.substring(filePath.lastIndexOf('/')+1, filePath.length());
            Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
        }
        else{
            isDrawable = true;
            fileDrawable = imageId;
            photoView.setImageResource(imageId);
        }
        layout = (FABToolbarLayout) findViewById(R.id.fabtoolbar);
        fab = (FloatingActionButton) findViewById(R.id.fabtoolbar_fab);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        if(isDrawable){
            three.setVisibility(View.GONE);
        }
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
                String filename;
                String addInfo;
                int width;
                int height;
                try {
                    if(isDrawable){
                        Bitmap b = ((BitmapDrawable)getResources().getDrawable(fileDrawable)).getBitmap();
                        filename = "Drawable(*.jpg)";
                        addInfo = "";
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeResource(getResources(), fileDrawable, options);
                        width = options.outWidth;
                        height = options.outHeight;
                    }else {
                        /*
                        ExifInterface exif = new ExifInterface("file://"+filePath.substring(5, filePath.length()));
                        exifInfo = "";
                        exifInfo += ExifInterface.TAG_DATETIME + ": " + exif.getAttribute(ExifInterface.TAG_DATETIME) + "\n";
                        */
                        filename = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
                        addInfo = "\n경로: " + filePath.substring("file:///storage/emulated/0/".length(), filePath.length() - filename.length());
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
                        height = options.outHeight;
                        width = options.outWidth;

                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(filename)
                            .setMessage("크기: 가로 "+width+"px * 세로 "+height+"px" + addInfo)
                            .setCancelable(true)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    dialog.cancel();
                                    visibleToolbar = false;
                                    layout.hide();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //System.out.println("two");
                visibleToolbar = false;
                layout.hide();
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(view.getContext());
                if(isDrawable){
                    try {
                        wallpaperManager.setResource(fileDrawable);
                        Toast.makeText(view.getContext(), "배경으로 설정되었습니다!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        Toast.makeText(view.getContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    try {
                        wallpaperManager.setBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
                        Toast.makeText(view.getContext(), "배경으로 설정되었습니다!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        Toast.makeText(view.getContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //System.out.println("three");
                visibleToolbar = false;
                layout.hide();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
                if(isDrawable || resInfo.isEmpty()){
                    Toast.makeText(view.getContext(), "공유 기능을 사용할 수 없습니다", Toast.LENGTH_SHORT);
                    return;
                }else{
                    List<Intent> shareIntentList = new ArrayList<Intent>();
                    for(ResolveInfo info: resInfo){
                        Intent shareIntent = (Intent) intent.clone();
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));
                        shareIntent.setPackage(info.activityInfo.packageName);
                        shareIntentList.add(shareIntent);
                    }
                    Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
                    startActivity(chooserIntent);
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //System.out.println("four");
                visibleToolbar = false;
                ((ImageviewActivity) view.getContext()).finish();
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
