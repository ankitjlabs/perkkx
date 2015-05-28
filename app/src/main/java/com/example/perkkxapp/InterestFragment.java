package com.example.perkkxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InterestFragment extends Fragment {


Button next;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_interest, container, false);
next=(Button) rootView.findViewById(R.id.btndone);

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i=new Intent (getActivity(),Home.class);
                startActivity(i);
            }
        });


        return rootView;
	}


}
