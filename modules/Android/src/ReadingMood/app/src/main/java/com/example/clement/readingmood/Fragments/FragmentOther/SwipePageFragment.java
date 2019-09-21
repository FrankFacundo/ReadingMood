package com.example.clement.readingmood.Fragments.FragmentOther;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.clement.readingmood.R;

public class SwipePageFragment extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int currentPage;
    private int  numberPage;
    private TextView textNumber ;
    private SeekBar mySeek ;


    private OnFragmentInteractionListener mListener;

    public SwipePageFragment() {
        // Required empty public constructor
    }


    public static SwipePageFragment newInstance(int  currentPage, int numberPage) {
        SwipePageFragment fragment = new SwipePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, currentPage);
        args.putInt(ARG_PARAM2, numberPage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentPage= getArguments().getInt(ARG_PARAM1);
            numberPage = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_swipe_page, container, false);
        textNumber = v.findViewById(R.id.fragment_swipe_current_page);
        mySeek = v.findViewById(R.id.seekbar_swipe_page);
        initialisteSeek();
        updateText();
        return v;
    }

    public void setCurrentPage(int a)
    {
        this.currentPage = a;
    }

    public void setNumberPage(int a )
    {
        this.numberPage = a ;
    }

    public void updateValue()
    {
        mySeek.setProgress(currentPage);
        updateText();
    }

    private void updateText()
    {
        textNumber.setText(" - "+currentPage +" / " + numberPage + " - ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialisteSeek()
    {
        mySeek.setMin(0);
        mySeek.setMax(numberPage);
        mySeek.setProgress(currentPage);
        mySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("Change seekSwipe", "New value : "+progress);
                currentPage = progress;
                mListener.changePage(currentPage);
                updateText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
 public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void changePage(int newPage);
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.RIGHT);

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 400;
        params.y = 150;
        window.setAttributes(params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }



}
