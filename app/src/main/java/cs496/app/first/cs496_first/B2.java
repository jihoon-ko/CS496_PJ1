package cs496.app.first.cs496_first;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link B2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link B2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class B2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    GridView gridView;
    SeekBar seekBar;
    DisplayMetrics dm;
    TextView szText;
    int one_row = 3;
    boolean first_fetch = true;
    public B2() {
        // Required empty public constructor
        first_fetch = true;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment B2.
     */
    // TODO: Rename and change types and number of parameters
    public static B2 newInstance(String param1, String param2) {
        B2 fragment = new B2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ArrayList<Uri> result = new ArrayList<Uri>();
    ArrayList<Bitmap> thumbs = new ArrayList<Bitmap>();

    void fetchAllImages(){
        System.out.println("LETS FETCH!");
        if (first_fetch){
            first_fetch = false;
        }else{
            return;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor imageCursor = getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        System.out.println("imagecursor Count: " + imageCursor.getCount());
        System.out.println("imagecursor Count: " + dataColumnIndex);
        if(imageCursor == null) {
        }else if(imageCursor.moveToFirst()){
            do{
                String filePath = imageCursor.getString(dataColumnIndex);
                File saveFile = new File(filePath);
                System.out.println(filePath);
                Uri imageUri = Uri.fromFile(saveFile);
                result.add(imageUri);
                thumbs.add(null);
            }while(imageCursor.moveToNext());
        }
        imageCursor.close();
        gridView.invalidateViews();
        gridView.postInvalidate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 42: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchAllImages();
                } else {
                    Toast.makeText(getActivity(), "Bye-bye!", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                return;
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b2, container, false);
        gridView = (GridView) v.findViewById(R.id.gridview);
        seekBar = (SeekBar) v.findViewById(R.id.seekbar);
        szText = (TextView) v.findViewById(R.id.sztext);
        dm = new DisplayMetrics();
        ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ImageviewActivity.class);
                intent.putExtra("imageUri", result.get(position));
                startActivity(intent);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                one_row = progress + 1;
                gridView.setNumColumns(one_row);
                gridView.invalidateViews();
                gridView.postInvalidate();
                szText.setText(String.valueOf(one_row));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 42);
        }else{
            fetchAllImages();
        }
        System.out.println(result.size());
        gridView.setAdapter(new B2.ImageAdapter(this.getContext()));
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }
        public final int getCount(){
            return result.size();
        }
        public Object getItem(int position){
            return result.get(position);
        }
        public long getItemId(int position){
            return position;
        }
        public Bitmap getBitmap(int position){
            Bitmap myThumbnail = thumbs.get(position);
            if(myThumbnail != null) return myThumbnail;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream((Uri) getItem(position)), null, options);
                int imageHeight = options.outHeight;
                int imageWidth = options.outWidth;
                options.inSampleSize = Math.max(1, Math.min(imageWidth, imageHeight) / 100);
                options.inJustDecodeBounds = false;
                Bitmap original = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream((Uri) getItem(position)), null, options);
                int width = original.getWidth();
                int height = original.getHeight();
                if(original.getWidth() > original.getHeight()){
                    myThumbnail = Bitmap.createBitmap(original, (width - height)/2, 0, height, height);
                }else{
                    myThumbnail = Bitmap.createBitmap(original, 0, (height - width)/2, width, width);
                }
                thumbs.set(position, myThumbnail);
                return myThumbnail;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView;
            int width = dm.widthPixels;
            if(convertView == null){
                imageView = new ImageView(mContext);
            }else{
                imageView = (ImageView) convertView;
            }
            imageView.setLayoutParams(new GridView.LayoutParams(width/one_row, width/one_row));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageBitmap(getBitmap(position));
            return imageView;
        }
    };
}
