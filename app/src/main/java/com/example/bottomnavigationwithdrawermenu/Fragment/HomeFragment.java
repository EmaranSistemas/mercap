package com.example.bottomnavigationwithdrawermenu.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bottomnavigationwithdrawermenu.MainActivity;
import com.example.bottomnavigationwithdrawermenu.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RelativeLayout button = rootView.findViewById(R.id.rtbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones a realizar cuando se haga clic en el botón
                Toast.makeText(getActivity(), "Opciones", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout button1 = rootView.findViewById(R.id.promotorbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones a realizar cuando se haga clic en el botón
                Toast.makeText(getActivity(), "Promotor", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}