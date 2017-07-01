package cs496.app.first.cs496_first;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link B.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link B#newInstance} factory method to
 * create an instance of this fragment.
 */
public class B extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Integer[] img = {
            R.drawable.style_1_1, R.drawable.style_1_2,  R.drawable.style_1_3,
            R.drawable.style_1_4, R.drawable.style_1_5,  R.drawable.style_1_6,
            R.drawable.style_1_7, R.drawable.style_1_8,  R.drawable.style_1_9,
            R.drawable.style_2_1, R.drawable.style_2_2,  R.drawable.style_2_3,
            R.drawable.style_2_4, R.drawable.style_2_5,  R.drawable.style_2_6,
            R.drawable.style_2_7, R.drawable.style_2_8,  R.drawable.style_2_9,
            R.drawable.style_3_1, R.drawable.style_3_2};
/*  R.drawable.style_3_3,
            R.drawable.style_3_4, R.drawable.style_3_5,  R.drawable.style_3_6,
            R.drawable.style_3_7, R.drawable.style_3_8,  R.drawable.style_3_9,
            R.drawable.style_4_1, R.drawable.style_4_2,  R.drawable.style_4_3,
            R.drawable.style_4_4, R.drawable.style_4_5,  R.drawable.style_4_6,
            R.drawable.style_4_7, R.drawable.style_4_8,  R.drawable.style_4_9
    };*/
    GridView gridView;

    public B() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment B.
     */
    // TODO: Rename and change types and number of parameters
    public static B newInstance(String param1, String param2) {
        B fragment = new B();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        gridView = (GridView) v.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this.getContext()));
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
            return img.length;
        }
        public Object getItem(int position){
            return img[position];
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(mContext);
                int width = parent.getWidth();
                imageView.setLayoutParams(new GridView.LayoutParams(width/3, width/3));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(10, 10, 10, 10);
            }else{
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(img[position]);
            return imageView;
        }
    };
}
