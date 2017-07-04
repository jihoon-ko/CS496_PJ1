package cs496.app.first.cs496_first;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
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

class Item
{
    String itemname;
    int itemnumber;
    Item(String aitemname, int aitemnumber)
    {
        itemname = aitemname;
        itemnumber = aitemnumber;
    }
}

class Skill
{
    String skillname;
    int skillmp;
    Skill(String askillname, int askillmp)
    {
        skillname = askillname;
        skillmp = askillmp;
    }
}

class Champion
{
    int x;
    int y;
    int hp;
    int mp;
    int level;
    int exp;
    int strength;
    int dex;
    int inte;
    Item[] itemv;
    int itemposition;
    Skill[] skillv;

    Champion()
    {
        hp = 30;
        mp = 30;
        level = 1;
        exp = 0;
        strength = 10;
        dex = 10;
        inte = 10;
        x = 5;
        y = 5;
        itemv = new Item[100];
        itemv[1] = new Item("약초", 3);
        itemposition = 1;
        skillv = new Skill[4];
        skillv[1] = new Skill("파이어볼", 10);
        skillv[2] = new Skill("아이스 스피어", 15);
        skillv[3] = new Skill("라이트닝", 20);
    }
}

public class D extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int[][] map =
            {{99,99,99,99,99,99,99,99,99,99}, {99,0,0,0,0,0,0,0,0,99}, {99,0,0,0,0,0,0,0,0,99}, {99,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,99},{99,0,0,0,0,0,0,0,0,99},{99,99,99,99,99,99,99,99,99,99}};
    int[] flag = {1,0,0,0,0,0,0};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


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
    final Champion champion = new Champion();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_d, container, false);
        map[champion.x][champion.y] = 1;
        textSetting(champion,flag,v);
        return v;
    }

    public void textSetting(final Champion champion, final int[] flag, final View v)
    {
        if(flag[0] == 1)
            textSetting1(champion,flag,v);
        else if(flag[1] == 1)
            textSetting2(champion,flag,v);
        else if(flag[2] == 1)
            textSetting3(champion,flag,v);
        else if(flag[3] == 1)
            textSetting4(champion,flag,v);
        else if(flag[4] == 1)
            textSetting5(champion,flag,v);
        else if(flag[5] == 1)
            textSetting6(champion,flag,v);

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
            script.setText(R.string.action);
            action1.setText(R.string.move);
            action2.setText(R.string.state);
            action3.setText(R.string.item);
            action4.setText(R.string.skill);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[0] = 0;
                    flag[1] = 1;
                    textSetting2(champion,flag,v);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[0] = 0;
                    flag[3] = 1;
                    textSetting4(champion,flag,v);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[0] = 0;
                    flag[4] = 1;
                    textSetting5(champion,flag,v);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[0] = 0;
                    flag[5] = 1;
                    textSetting6(champion,flag,v);
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

        if(flag[1] == 1)
        {
            script.setText(R.string.direction);
            action1.setText(R.string.north);
            action2.setText(R.string.west);
            action3.setText(R.string.east);
            action4.setText(R.string.south);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    map[champion.x][champion.y] = 0;
                    champion.y--;
                    flag[1] = 0;
                    if(map[champion.x][champion.y] == 0)
                    {
                        map[champion.x][champion.y] = 1;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                    else
                    {
                        champion.y++;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    map[champion.x][champion.y] = 0;
                    champion.x--;
                    flag[1] = 0;
                    if(map[champion.x][champion.y] == 0)
                    {
                        map[champion.x][champion.y] = 1;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                    else
                    {
                        champion.x++;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    map[champion.x][champion.y] = 0;
                    champion.x++;
                    flag[1] = 0;
                    if(map[champion.x][champion.y] == 0)
                    {
                        map[champion.x][champion.y] = 1;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                    else
                    {
                        champion.x--;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    map[champion.x][champion.y] = 0;
                    champion.y++;
                    flag[1] = 0;
                    if(map[champion.x][champion.y] == 0)
                    {
                        map[champion.x][champion.y] = 1;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                    else
                    {
                        champion.y--;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                }
            });
        }
    }

    public void textSetting3(final Champion champion, final int[] flag, final View v)
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

        if(flag[2] == 1)
        {
            script.setText(R.string.wall);
            action1.setText(R.string.back);
            action2.setText("");
            action3.setText("");
            action4.setText("");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[2] = 0;
                    flag[0] = 1;
                    textSetting1(champion,flag,v);
                }
            });
        }
    }

    public void textSetting4(final Champion champion, final int[] flag, final View v)
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
        if(flag[3] == 1)
        {
            String nowscript = getString(R.string.stat) + "\n" +
                    getString(R.string.lvl) + " : " + String.valueOf(champion.level) + "\n" +
                    getString(R.string.hp) + " : " + String.valueOf(champion.hp) + "\n" +
                    getString(R.string.mp) + " : " + String.valueOf(champion.mp) + "\n" +
                    getString(R.string.exp) + " : " + String.valueOf(champion.exp) + "\n" +
                    getString(R.string.str) + " : " + String.valueOf(champion.strength) + "\n" +
                    getString(R.string.dex) + " : " + String.valueOf(champion.dex) + "\n" +
                    getString(R.string.inte) + " : " + String.valueOf(champion.inte);
                    ;
            script.setText(nowscript);
            action1.setText(R.string.back);
            action2.setText("");
            action3.setText("");
            action4.setText("");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[3] = 0;
                    flag[0] = 1;
                    textSetting1(champion,flag,v);
                }
            });
        }
    }

    public void textSetting5(final Champion champion, final int[] flag, final View v) {
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if(flag[4] == 1)
        {
            if(champion.itemv[champion.itemposition] != null)
            {
                script.setText(champion.itemv[champion.itemposition].itemname + " : " + String.valueOf(champion.itemv[champion.itemposition].itemnumber));
                action1.setText(R.string.before);
                action2.setText(R.string.next);
                action3.setText(R.string.use);
                action4.setText(R.string.back);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        champion.itemposition--;
                        textSetting5(champion,flag,v);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        champion.itemposition++;
                        textSetting5(champion,flag,v);
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        champion.itemv[champion.itemposition].itemnumber--;
                        switch (champion.itemv[champion.itemposition].itemname)
                        {
                            case "약초":
                                champion.hp += 15;
                        }
                        if(champion.itemv[champion.itemposition].itemnumber == 0)
                        {
                            champion.itemv[champion.itemposition] = null;
                            System.arraycopy(champion.itemv, champion.itemposition + 1, champion.itemv, champion.itemposition, 99 - champion.itemposition);
                            champion.itemv[99] = null;
                            champion.itemposition--;
                        }
                        textSetting5(champion,flag,v);
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[4] = 0;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                });
            }
            else
            {
                script.setText("아이템이 없습니다.");
                action1.setText(R.string.back);
                action2.setText("");
                action3.setText("");
                action4.setText("");
                champion.itemposition = 1;
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[4] = 0;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                });
            }
        }
    }

    public void textSetting6(final Champion champion, final int[] flag, final View v) {
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);
        if(flag[5] == 1)
        {
            String nowscript = champion.skillv[1].skillname + " : " + String.valueOf(champion.skillv[1].skillmp) + "mp\n" +
                    champion.skillv[2].skillname + " : " + String.valueOf(champion.skillv[2].skillmp) + "mp\n" +
                    champion.skillv[3].skillname + " : " + String.valueOf(champion.skillv[3].skillmp) + "mp\n";
            script.setText(nowscript);
            action1.setText(R.string.back);
            action2.setText("");
            action3.setText("");
            action4.setText("");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[5] = 0;
                    flag[0] = 1;
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
