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
import java.util.Arrays;


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
    int maxhp;
    int maxmp;
    int level;
    int exp;
    int atk;
    int def;
    int spe;
    Item[] itemv;
    int itemposition;
    Skill[] skillv;
    int skillposition;

    Champion()
    {
        hp = 30;
        mp = 30;
        maxhp = 30;
        maxmp = 30;
        level = 1;
        exp = 0;
        atk = 10;
        def = 10;
        spe = 20;
        x = 5;
        y = 5;
        itemv = new Item[100];
        itemv[1] = new Item("약초", 3);
        itemv[2] = new Item("마나포션", 3);
        itemposition = 1;
        skillposition = 1;
        skillv = new Skill[4];
        skillv[1] = new Skill("파이어볼", 10);
        skillv[2] = new Skill("아이스 스피어", 15);
        skillv[3] = new Skill("라이트닝", 20);
    }
}

class Monster
{
    int x;
    int y;
    int hp;
    int mp;
    int atk;
    int def;
}

class Goblin extends Monster
{
    Goblin()
    {
        hp = 50;
        mp = 0;
        atk = 15;
        def = 0;
    }
}

public class D extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int[][] map =
            {{99,99,99,99,99,99,99,99,99,99},
                    {99,50,0,0,0,0,0,50,0,99},
                    {99,0,0,0,0,0,0,0,0,99},
                    {99,0,50,0,0,0,0,0,0,99},
                    {99,0,0,0,0,0,0,0,0,99},
                    {99,0,0,0,0,0,0,50,0,99},
                    {99,0,0,0,0,0,0,0,0,99},
                    {99,0,0,50,0,0,0,0,0,99},
                    {99,50,0,0,0,0,0,0,50,99},
                    {99,99,99,99,99,99,99,99,99,99}};
    int[] flag = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
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
    final Goblin goblin = new Goblin();

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
        else if(flag[6] == 1)
            textSetting7(champion,flag,v);
        else if(flag[7] == 1)
            textSetting8(champion,flag,v);
        else if(flag[8] == 1)
            textSetting9(champion,flag,v);
        else if(flag[9] == 1)
            textSetting10(champion,flag,v);
        else if(flag[10] == 1)
            textSetting11(champion,flag,v);
        else if(flag[11] == 1)
            textSetting12(champion,flag,v);

    }

    public void textSetting1(final Champion champion, final int[] flag, final View v)
    {
        System.out.println(Arrays.deepToString(map));
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
        System.out.println(Arrays.deepToString(map));
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
                    champion.x--;
                    flag[1] = 0;
                    if(map[champion.x][champion.y] == 0)
                    {
                        map[champion.x][champion.y] = 1;
                        flag[0] = 1;
                        textSetting1(champion,flag,v);
                    }
                    else if(map[champion.x][champion.y] == 99)
                    {
                        champion.x++;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                    else
                    {
                        int trig = map[champion.x][champion.y] + 1;
                        map[champion.x][champion.y] = trig;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
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
                    else if(map[champion.x][champion.y] == 99)
                    {
                        champion.y++;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                    else
                    {
                        int trig = map[champion.x][champion.y] + 1;
                        map[champion.x][champion.y] = trig;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
                    }
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
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
                    else if(map[champion.x][champion.y] == 99)
                    {
                        champion.y--;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                    else
                    {
                        int trig = map[champion.x][champion.y] + 1;
                        map[champion.x][champion.y] = trig;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
                    }
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
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
                    else if(map[champion.x][champion.y] == 99)
                    {
                        champion.x--;
                        flag[2] = 1;
                        textSetting3(champion,flag,v);
                    }
                    else
                    {
                        int trig = map[champion.x][champion.y] + 1;
                        map[champion.x][champion.y] = trig;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
                    }
                }
            });
        }
    }

    public void textSetting3(final Champion champion, final int[] flag, final View v)
    {
        System.out.println(Arrays.deepToString(map));
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
        System.out.println(Arrays.deepToString(map));
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
                    getString(R.string.hp) + " : " + String.valueOf(champion.hp) + "/" + String.valueOf(champion.maxhp) + "\n" +
                    getString(R.string.mp) + " : " + String.valueOf(champion.mp) + "/" + String.valueOf(champion.maxmp) + "\n" +
                    getString(R.string.exp) + " : " + String.valueOf(champion.exp) + "\n" +
                    getString(R.string.atk) + " : " + String.valueOf(champion.atk) + "\n" +
                    getString(R.string.def) + " : " + String.valueOf(champion.def) + "\n" +
                    getString(R.string.spe) + " : " + String.valueOf(champion.spe);
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
        System.out.println(Arrays.deepToString(map));
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
                        if(champion.itemv[champion.itemposition] != null)
                        {
                            textSetting5(champion,flag,v);
                        }
                        else
                        {
                            champion.itemposition++;
                            textSetting5(champion,flag,v);
                        }
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        champion.itemposition++;
                        if(champion.itemv[champion.itemposition] != null)
                        {
                            textSetting5(champion,flag,v);
                        }
                        else
                        {
                            champion.itemposition--;
                            textSetting5(champion,flag,v);
                        }
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
                                if(champion.hp > champion.maxhp)
                                {
                                    champion.hp = champion.maxhp;
                                }
                                break;
                            case "마나포션":
                                champion.mp += 15;
                                if(champion.mp > champion.maxmp)
                                {
                                    champion.mp =champion.maxmp;
                                }
                                break;
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
        System.out.println(Arrays.deepToString(map));
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

    public void textSetting7(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if(flag[6] == 1)
        {
            if(champion.hp > 0)
            {
                String tel = "monster";
                if(map[champion.x][champion.y] == 51)
                {
                    tel = getString(R.string.goblin) + getString(R.string.encounter) + "\n" +
                            getString(R.string.whatwillyoudo);
                }
                script.setText(tel);
                action1.setText(R.string.attack);
                action2.setText(R.string.item);
                action3.setText(R.string.skill);
                action4.setText(R.string.run);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[6] = 0;
                        flag[7] = 1;
                        textSetting8(champion,flag,v);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[6] = 0;
                        flag[8] = 1;
                        textSetting9(champion,flag,v);
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[6] = 0;
                        flag[9] = 1;
                        textSetting10(champion,flag,v);
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[6] = 0;
                        flag[0] = 1;
                        map[champion.x][champion.y] = 1;
                        goblin.hp = 60;
                        goblin.mp = 0;
                        textSetting1(champion,flag,v);
                    }
                });
            }
            else
            {
                script.setText("당신은 죽었습니다...\n다시 하려면 앱을 재시작하십시오.");
                action1.setText("");
                action2.setText("");
                action3.setText("");
                action4.setText("");
            }
        }
    }

    public void textSetting8(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if (flag[7] == 1) {
            if(map[champion.x][champion.y] == 51)
            {
                script.setText(getString(R.string.goblin) + "을 " + getString(R.string.attack) + " : " + String.valueOf(champion.atk - goblin.def) + "데미지" + "\n" +
                                getString(R.string.goblin) + "이 " + getString(R.string.attack) + " : " + String.valueOf(goblin.atk - champion.def) + "데미지");
                champion.hp -= (goblin.atk-champion.def);
                goblin.hp -= (champion.atk-goblin.def);
            }
            action1.setText("다음");
            action2.setText("");
            action3.setText("");
            action4.setText("");


            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if(champion.hp <= 0) {
                        flag[6] = 1;
                        flag[7] = 0;
                        textSetting7(champion, flag, v);
                    }
                    else
                    {
                        if(map[champion.x][champion.y] == 51)
                        {
                            if(goblin.hp > 0)
                            {
                                flag[6] = 1;
                                flag[7] = 0;
                                textSetting7(champion, flag, v);
                            }
                            else
                            {
                                flag[7] = 0;
                                flag[10] = 1;
                                textSetting11(champion,flag,v);
                            }
                        }
                    }
                }
            });
        }
    }
    public void textSetting9(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if (flag[8] == 1) {
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
                        if(champion.itemv[champion.itemposition] != null)
                        {
                            textSetting9(champion,flag,v);
                        }
                        else
                        {
                            champion.itemposition++;
                            textSetting9(champion,flag,v);
                        }
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        champion.itemposition++;
                        if(champion.itemv[champion.itemposition] != null)
                        {
                            textSetting9(champion,flag,v);
                        }
                        else
                        {
                            champion.itemposition--;
                            textSetting9(champion,flag,v);
                        }
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
                                if(champion.hp > champion.maxhp)
                                {
                                    champion.hp = champion.maxhp;
                                }
                                break;
                            case "마나포션":
                                champion.mp += 15;
                                if(champion.mp > champion.maxmp)
                                {
                                    champion.mp =champion.maxmp;
                                }
                                break;
                        }
                        if(champion.itemv[champion.itemposition].itemnumber == 0)
                        {
                            champion.itemv[champion.itemposition] = null;
                            System.arraycopy(champion.itemv, champion.itemposition + 1, champion.itemv, champion.itemposition, 99 - champion.itemposition);
                            champion.itemv[99] = null;
                            champion.itemposition--;
                        }
                        flag[8] = 0;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        flag[8] = 0;
                        flag[6] = 1;
                        textSetting7(champion,flag,v);
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
                        flag[8] = 0;
                        flag[6] = 1;
                        textSetting1(champion,flag,v);
                    }
                });
            }
        }
    }

    public void textSetting10(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if (flag[9] == 1) {
            script.setText(champion.skillv[champion.skillposition].skillname + " : " + String.valueOf(champion.skillv[champion.skillposition].skillmp) + "mp");
            action1.setText(R.string.before);
            action2.setText(R.string.next);
            action3.setText(R.string.use);
            action4.setText(R.string.back);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if(champion.skillposition >= 2)
                    {
                        champion.skillposition--;
                        textSetting10(champion,flag,v);
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if(champion.skillposition <=2)
                    {
                        champion.skillposition++;
                        textSetting10(champion,flag,v);
                    }
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if(champion.mp >= champion.skillv[champion.skillposition].skillmp)
                    {
                        champion.mp -= champion.skillv[champion.skillposition].skillmp;
                        flag[9] = 0;
                        flag[11] = 1;
                        textSetting12(champion,flag,v);
                    }
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[6] = 1;
                    flag[9] = 0;
                    textSetting7(champion,flag,v);
                }
            });
        }
    }
    public void textSetting11(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if(flag[10] == 1)
        {
            if(map[champion.x][champion.y] == 51)
            {
                script.setText(getString(R.string.goblin) + "을 무찔렀습니다.\n경험치 : 20\n얻은 아이템 : 약초 1");
                action1.setText("돌아가기");
                action2.setText("");
                action3.setText("");
                action4.setText("");
                champion.exp += 20;
                if(champion.exp >= 100)
                {
                    champion.level++;
                    champion.hp += 10;
                    champion.maxhp += 10;
                    champion.mp += 15;
                    champion.maxmp += 15;
                    champion.atk += 5;
                    champion.def += 5;
                    champion.spe += 10;
                    champion.exp -= 100;
                }
                boolean isit = false;
                for(int i = 1;i<100;i++)
                {
                    if(champion.itemv[i] != null)
                    {
                        if(champion.itemv[i].itemname.equals("약초"))
                        {
                            champion.itemv[i].itemnumber++;
                            isit = true;
                        }
                        else
                        {
                            continue;
                        }
                    }
                    else
                    {
                        continue;
                    }
                }
                if(!isit)
                {
                    for(int i=1;i<100;i++)
                    {
                        if(champion.itemv[i] != null)
                        {
                            continue;
                        }
                        else
                        {
                            champion.itemv[i] = new Item("약초", 1);
                        }
                    }
                }
                goblin.hp = 60;
                map[champion.x][champion.y] = 1;
            }

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    flag[10] = 0;
                    flag[0] = 1;
                    textSetting1(champion,flag,v);
                }
            });
        }
    }

    public void textSetting12(final Champion champion, final int[] flag, final View v) {
        System.out.println(Arrays.deepToString(map));
        TextView script = (TextView) v.findViewById(R.id.states);
        TextView action1 = (TextView) v.findViewById(R.id.action1);
        TextView action2 = (TextView) v.findViewById(R.id.action2);
        TextView action3 = (TextView) v.findViewById(R.id.action3);
        TextView action4 = (TextView) v.findViewById(R.id.action4);

        Button button1 = (Button) v.findViewById(R.id.btn1);
        Button button2 = (Button) v.findViewById(R.id.btn2);
        Button button3 = (Button) v.findViewById(R.id.btn3);
        Button button4 = (Button) v.findViewById(R.id.btn4);

        if(flag[11] == 1) {
            if(map[champion.x][champion.y] == 51)
            {
                int damage = 0;
                switch (champion.skillv[champion.skillposition].skillname)
                {
                    case "파이어볼":
                        damage = champion.spe;
                        break;
                    case "아이스 스피어":
                        damage = champion.spe*2;
                        break;
                    case "라이트닝":
                        damage = champion.spe*3;
                        break;
                }
                script.setText(getString(R.string.goblin) + "을 " + champion.skillv[champion.skillposition].skillname + "(으)로" + getString(R.string.attack) + " : " + String.valueOf(damage) + "데미지" + "\n" +
                        getString(R.string.goblin) + "이 " + getString(R.string.attack) + " : " + String.valueOf(goblin.atk - champion.def) + "데미지");
                champion.hp -= (goblin.atk-champion.def);
                goblin.hp -= damage;
            }
            action1.setText("다음");
            action2.setText("");
            action3.setText("");
            action4.setText("");


            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if(champion.hp <= 0) {
                        flag[6] = 1;
                        flag[11] = 0;
                        textSetting7(champion, flag, v);
                    }
                    else
                    {
                        if(map[champion.x][champion.y] == 51)
                        {
                            if(goblin.hp > 0)
                            {
                                flag[6] = 1;
                                flag[11] = 0;
                                textSetting7(champion, flag, v);
                            }
                            else
                            {
                                flag[11] = 0;
                                flag[10] = 1;
                                textSetting11(champion,flag,v);
                            }
                        }
                    }
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
