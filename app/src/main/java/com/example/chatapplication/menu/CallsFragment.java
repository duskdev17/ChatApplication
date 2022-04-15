package com.example.chatapplication.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatapplication.R;
import com.example.chatapplication.adapter.CallListAdapter;
import com.example.chatapplication.model.CallList;
import com.example.chatapplication.model.ChatList;

import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
public class CallsFragment extends Fragment {
=======
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  CallsFragment extends Fragment {
>>>>>>> Stashed changes

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CallsFragment() {
        // Required empty public constructor
    }

    public static CallsFragment newInstance(String param1, String param2) {
        CallsFragment fragment = new CallsFragment();
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
        View view = inflater.inflate(R.layout.fragment_calls, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CallList> lists = new ArrayList<>();

        lists.add(new CallList(
                "001",
                "Jannat",
                "12/04/2022, 9:33 pm",
                "https://w0.peakpx.com/wallpaper/262/819/HD-wallpaper-female-anonymous-blue-eyes-character-drawings-girl-hat-mask-scary.jpg",
                "income"));


        lists.add(new CallList(
                "001",
                "Sumi",
                "12/04/2022, 10:03 am",
                "https://dp.profilepics.in/profile_pictures/cartoon_girls/cartoon-profile-pic-03.jpg",
                "missed"));

        lists.add(new CallList(
                "001",
                "Anik",
                "12/04/2022, 02:49 pm",
                "https://w0.peakpx.com/wallpaper/538/896/HD-wallpaper-neon-mask-abstracto-anime-azul-chaqueta-morado-the-purge.jpg",
                "out"));

        lists.add(new CallList(
                "001",
                "Tamanna",
                "12/04/2022, 07:43 am",
                "https://images.hdqwalls.com/download/anime-girl-art-kz-1125x2436.jpg",
                "income"));

        recyclerView.setAdapter(new CallListAdapter(lists,getContext()));
        return view;
    }
}