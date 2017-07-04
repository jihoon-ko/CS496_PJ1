package cs496.app.first.cs496_first;

import java.util.Arrays;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link A2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link A2#newInstance} factory method to
 * create an instance of this fragment.
 */
class Items
{
    Items(String aname, String aphonenum)
    {
        name = aname;
        phonenum =aphonenum;
    }
    String name;
    String phonenum;
}

class ItemsAdapter extends BaseAdapter
{
    ArrayList<Items> arSrc;
    int layout;
    Context maincon;
    LayoutInflater Inflater;

    public ItemsAdapter(Context context, int alayout, ArrayList<Items> aarSrc)
    {
        maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = aarSrc;
        layout = alayout;
    }

    public int getCount()
    {
        return arSrc.size();
    }
    public String getItem(int position)
    {
        return arSrc.get(position).name;
    }
    public long getItemId(int position)
    {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final int pos = position;
        if(convertView == null)
        {
            convertView = Inflater.inflate(layout, parent, false);
        }
        TextView txt1 = (TextView)convertView.findViewById(R.id.naming);
        txt1.setText(arSrc.get(position).name);

        TextView txt2 = (TextView)convertView.findViewById(R.id.phonenumber);
        txt2.setText(arSrc.get(position).phonenum);

        Button btn = (Button)convertView.findViewById(R.id.callbtn);
        btn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v){
                String str = arSrc.get(pos).name + "에게 전화합니다.";
                Toast.makeText(maincon,str, Toast.LENGTH_SHORT).show();
                String tel = "tel:" + arSrc.get(pos).phonenum;
                if(ContextCompat.checkSelfPermission(maincon, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                {
                    maincon.startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                }
            }
        });
        return convertView;
    }


}

class Ascending implements Comparator<Items> {

    @Override
    public int compare(Items o1, Items o2) {
        return o1.name.compareTo(o2.name);
    }

}

public class A2 extends Fragment {
    final int CONTACT_CODE = 0;

    ListView mResult;

    int flag = 1;
    int flag2 = 1;
    ItemsAdapter Adapter;
    public ArrayList<String> arGeneral = new ArrayList<String>();
    public ArrayList<String> arGeneral2 = new ArrayList<String>();
    public ArrayList<Items> sPhoneList = new ArrayList<Items>();
    String jsonStr = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public A2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A2.
     */
    // TODO: Rename and change types and number of parameters
    public static A2 newInstance(String param1, String param2) {
        A2 fragment = new A2();
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
        View v = inflater.inflate(R.layout.fragment_a2, container, false);
        mResult = (ListView)v.findViewById(R.id.result1);
        final EditText searchtext = (EditText)v.findViewById(R.id.searchbar);
        Button searchbutton = (Button)v.findViewById(R.id.searchbtn);
        Button resetbutton = (Button)v.findViewById(R.id.resetbtn);
        if(flag2 == 0)
        {
            Adapter = new ItemsAdapter(getActivity(), R.layout.fragment_a2_item ,sPhoneList);
            mResult.setAdapter(Adapter);
        }
        else
        {
            tryOutContact();
            flag2 = 0;
        }
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean searching = false;
                ArrayList<Items> sPhoneList1 = new ArrayList<Items>();
                for(int i = 0; i<sPhoneList.size();i++)
                {
                    if(sPhoneList.get(i).name.contains(searchtext.getText().toString()))
                    {
                        Items searched = new Items(sPhoneList.get(i).name, sPhoneList.get(i).phonenum);
                        searching = true;
                        sPhoneList1.add(searched);
                    }
                    else
                    {
                        continue;
                    }
                }
                if(!searching)
                {
                    sPhoneList1 = new ArrayList<Items>();
                }
                Adapter = new ItemsAdapter(getActivity(), R.layout.fragment_a2_item ,sPhoneList1);
                mResult.setAdapter(Adapter);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adapter = new ItemsAdapter(getActivity(), R.layout.fragment_a2_item ,sPhoneList);
                mResult.setAdapter(Adapter);
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
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE}, CONTACT_CODE);
        }
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CONTACT_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
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
            Items newitems = new Items(arGeneral.get(i), arGeneral2.get(i));
            sPhoneList.add(newitems);
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
                Items newitems = new Items(username, number);
                sPhoneList.add(newitems);
            }
        }catch(JSONException e){
            System.out.println("#");
            e.printStackTrace();
        }
        Ascending ascending = new Ascending();
        Collections.sort(sPhoneList, ascending);

        Adapter = new ItemsAdapter(getActivity(), R.layout.fragment_a2_item ,sPhoneList);
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
