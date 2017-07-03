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
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


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
    SimpleAdapter Adapter;
    public ArrayList<String> arGeneral = new ArrayList<String>();
    public ArrayList<String> arGeneral2 = new ArrayList<String>();
    public ArrayList<HashMap<String, String>> sPhoneList = new ArrayList<HashMap<String, String>>();
    String jsonStr = null;

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
        mResult = (ListView)v.findViewById(R.id.result);
        if(flag2 == 0)
        {
            Adapter = new SimpleAdapter(getActivity(), sPhoneList ,android.R.layout.simple_list_item_2, new String[]{"name", "phone"}, new int[]{android.R.id.text1, android.R.id.text2});
            mResult.setAdapter(Adapter);
        }
        else
        {
            tryOutContact();
            flag2 = 0;
        }
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
        ContentResolver cr = getActivity().getApplication().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        int nameidx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int nameidx2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        while(cursor.moveToNext())
        {
            arGeneral.add(cursor.getString(nameidx));
            arGeneral2.add(cursor.getString(nameidx2));
        }
        cursor.close();
        for(int i=0 ; i < arGeneral.size();i++)
        {
            HashMap singleMap = new HashMap();
            singleMap.put("name", arGeneral.get(i));
            singleMap.put("phone", arGeneral2.get(i));
            sPhoneList.add(singleMap);
        }

        try {
            InputStream stream = getActivity().getAssets().open("test.json");
            byte[] buf = new byte[stream.available()];
            stream.read(buf); stream.close();
            jsonStr = new String(buf, "UTF-8");
        }catch(IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jobj = new JSONObject(jsonStr);
            JSONArray jarray = jobj.getJSONArray("phone_list");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jobject = jarray.getJSONObject(i);
                String username = jobject.getString("name");
                String number = jobject.getString("phone_number");
                HashMap singleMap = new HashMap();
                singleMap.put("name", username);
                singleMap.put("phone", number);
                sPhoneList.add(singleMap);
            }
        }catch(JSONException e){
            System.out.println("#");
            e.printStackTrace();
        }

        Adapter = new SimpleAdapter(getActivity(), sPhoneList ,android.R.layout.simple_list_item_2, new String[]{"name", "phone"}, new int[]{android.R.id.text1, android.R.id.text2});
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
