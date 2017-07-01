package cs496.app.first.cs496_first;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link A.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link A#newInstance} factory method to
 * create an instance of this fragment.
 */
public class A extends Fragment {
    ListView mResult;
    final int READ_CONTACT_CODE = 0;
    int flag = 1;
    int flag2 = 1;
    static int flag3 = 1;
    ArrayAdapter<String> Adapter;
    public static ArrayList<String> arGeneral = new ArrayList<String>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public A() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A.
     */
    // TODO: Rename and change types and number of parameters
    public static A newInstance(String param1, String param2) {
        A fragment = new A();
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
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        if(flag2 == 0)
        {
            Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arGeneral);
            mResult = (ListView)v.findViewById(R.id.result);
            mResult.setAdapter(Adapter);
        }
        button = (Button)v.findViewById(R.id.btnread);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btnread:
                        if(flag == 1 && flag2 == 1)
                        {
                            tryOutContact();
                            flag = 0;
                            flag2 = 0;
                            break;
                        }
                }
            }
        });
        return v;
    }

    void tryOutContact()
    {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            outContact();
        }
        else
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_CODE);
        }
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case READ_CONTACT_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    outContact();
                }
        }
    }

    void outContact()
    {
        if(flag3 == 1)
        {
            ContentResolver cr = getActivity().getApplication().getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

            while(cursor.moveToNext())
            {
                arGeneral.add(cursor.getString(nameidx));
            }
            cursor.close();
            flag3 = 0;
        }

        Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arGeneral);
        mResult = (ListView)getView().findViewById(R.id.result);
        mResult.setAdapter(Adapter);
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
}
