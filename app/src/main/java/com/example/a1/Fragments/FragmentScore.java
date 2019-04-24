package com.example.a1.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.R;

import java.util.ArrayList;


public class FragmentScore extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_fragment_score, container, false);

        ArrayList<String> DATA = new ArrayList();
        DATA = this.getArguments().getStringArrayList("DATA");

        for (int i = 0; i < DATA.size(); i++){
            Log.d("mLog", DATA.get(i));
        }

        return rootview;
    }

}
