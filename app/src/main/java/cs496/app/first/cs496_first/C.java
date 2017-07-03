package cs496.app.first.cs496_first;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link C.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link C#newInstance} factory method to
 * create an instance of this fragment.
 */
public class C extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public static int[] chueck = new int[370000];
    public static float[][] coor = new float[9][2];
    public static String[] su = {"1", "2", "3", "4", "5", "6", "7", "8"};
    public static int[] zero = {2, 2};
    public static int[][] where = new int[3][3];
    public static int moveCnt = 0;
    public static int xx = -1;
    public static int yy = -1;
    public static boolean youwin = false;
    public static boolean simulating = false;
    public static boolean start_first = true;
    public static boolean resist = false;
    public static float[][][] mv = new float[101][9][2];
    public static int[] mvdirec = new int[101];
    public static int recent = -1;


    Queue<Integer> que = new LinkedList<Integer>();

    private int[] suu = new int[9];
    private int[] fact = {40320, 5040, 720, 120, 24, 6, 2, 1, 1};
    //private int[] chueck = new int[370000];
    int inf = 2100000000;
    int getPerm() {
        int[] chk = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int ans = 0;
        for (int i = 0; i < 8; i++) {
            int cnt = 0;
            for (int j = 0; j < suu[i]; j++) {
                cnt += 1 - chk[j];
            }
            chk[suu[i]] = 1;
            ans += cnt * fact[i];
        }
        return ans;
    }

    int setPerm(int target) {
        int[] chk = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int ans = 9;
        for (int i = 0; i < 9; i++) {
            int need = target / fact[i];
            for (int j = 0; ; j++) {
                if (chk[j] == 0) need--;
                if (need == -1) {
                    chk[j] = 1;
                    suu[i] = j;
                    if (j == 0) ans = i;
                    break;
                }
            }
            target %= fact[i];
        }
        return ans;
    }

    void chgfunc(int xx, int yy, int cntt) {
        int tmp;
        tmp = suu[xx];
        suu[xx] = suu[yy];
        suu[yy] = tmp;
        int target = getPerm();
        if (chueck[target] == inf) {
            chueck[target] = cntt + 1;
            que.offer(target);
        }
        tmp = suu[xx];
        suu[xx] = suu[yy];
        suu[yy] = tmp;
    }

    void bfs(int st) {
        for (int i = 0; i < 40320 * 9; i++) {
            chueck[i] = inf;
        }
        chueck[st] = 0;
        que.offer(st);
        while (!que.isEmpty()) {
            int now = que.peek();
            int zero = setPerm(now);
            que.remove();
            if (zero % 3 > 0) chgfunc(zero, zero - 1, chueck[now]);
            if (zero % 3 < 2) chgfunc(zero, zero + 1, chueck[now]);
            if (zero / 3 > 0) chgfunc(zero, zero - 3, chueck[now]);
            if (zero / 3 < 2) chgfunc(zero, zero + 3, chueck[now]);
        }
    }

    public C() {
        // Required empty public constructor
        if(start_first) {
            for (int i = 0; i < 9; i++) suu[i] = (i + 1) % 9;
            int start = getPerm();
            bfs(start);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment C.
     */
    // TODO: Rename and change types and number of parameters
    public static C newInstance(String param1, String param2) {
        C fragment = new C();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v){
            CanvasView cv = (CanvasView) getView().findViewById(R.id.myCanvas);
            cv.shuffle();
        }
    };
    Button.OnClickListener mClickListenerAns = new View.OnClickListener() {
        public void onClick(View v){
            if(!simulating) {
                simulating = true;
                CanvasView cv = (CanvasView) getView().findViewById(R.id.myCanvas);
                cv.showAns();
                //simulating = false;
            }
        }
    };
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
        View v = inflater.inflate(R.layout.fragment_c, container, false);
        Button cvButton = (Button) v.findViewById(R.id.canvas_button);
        cvButton.setOnClickListener(mClickListener);
        Button cvButtonAns = (Button) v.findViewById(R.id.canvas_answer);
        cvButtonAns.setOnClickListener(mClickListenerAns);
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
}
