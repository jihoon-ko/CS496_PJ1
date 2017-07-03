package cs496.app.first.cs496_first;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link D.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link D#newInstance} factory method to
 * create an instance of this fragment.
 */

class viewofeach
{

}

class Champion
{
    int x;
    int y;
    int hp;
    int mp;
    int level;
    int exp;
    Champion()
    {
        hp = 30;
        mp = 30;
        level = 1;
        exp = 0;
        x = 5;
        y = 5;
    }
}

public class D extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int[][] map = new int[10][10];
    int[] flag = {1,0};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    int mov = 0;
    int sta = 0;
    int ite = 0;
    int ski = 0;

    int nor = 0;
    int wes = 0;
    int eas = 0;
    int sou = 0;

    int next = 0;


    public D() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment D.
     */
    // TODO: Rename and change types and number of parameters
    public static D newInstance(String param1, String param2) {
        D fragment = new D();
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
        final View v = inflater.inflate(R.layout.fragment_d, container, false);
        final Champion champion = new Champion();

        textSetting1(champion,flag,v);
        return v;
    }

    public void textSetting1(final Champion champion, final int[] flag, final View v)
    {
        TextView script = (TextView)v.findViewById(R.id.states);
        TextView action1 = (TextView)v.findViewById(R.id.action1);
        TextView action2 = (TextView)v.findViewById(R.id.action2);
        TextView action3 = (TextView)v.findViewById(R.id.action3);
        TextView action4 = (TextView)v.findViewById(R.id.action4);

        Button button1 = (Button)v.findViewById(R.id.btn1);
        Button button2 = (Button)v.findViewById(R.id.btn2);
        Button button3 = (Button)v.findViewById(R.id.btn3);
        Button button4 = (Button)v.findViewById(R.id.btn4);

        if(flag[0] == 1)
        {
            flag[0] = 0;
            script.setText(R.string.action);
            action1.setText(R.string.move);
            action2.setText(R.string.state);
            action3.setText(R.string.item);
            action4.setText(R.string.skill);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    textSetting2(champion,flag,v);
                }
            });
        }
    }

    public void textSetting2(final Champion champion, final int[] flag, final View v)
    {
        TextView script = (TextView)v.findViewById(R.id.states);
        TextView action1 = (TextView)v.findViewById(R.id.action1);
        TextView action2 = (TextView)v.findViewById(R.id.action2);
        TextView action3 = (TextView)v.findViewById(R.id.action3);
        TextView action4 = (TextView)v.findViewById(R.id.action4);

        Button button1 = (Button)v.findViewById(R.id.btn1);
        Button button2 = (Button)v.findViewById(R.id.btn2);
        Button button3 = (Button)v.findViewById(R.id.btn3);
        Button button4 = (Button)v.findViewById(R.id.btn4);

        if(flag[0] == 0)
        {
            flag[0] = 1;
            script.setText(R.string.direction);
            action1.setText(R.string.north);
            action2.setText(R.string.west);
            action3.setText(R.string.east);
            action4.setText(R.string.south);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    champion.y--;
                    textSetting1(champion,flag,v);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    champion.x--;
                    textSetting1(champion,flag,v);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    champion.x++;
                    textSetting1(champion,flag,v);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    champion.y++;
                    textSetting1(champion,flag,v);
                }
            });
        }
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
