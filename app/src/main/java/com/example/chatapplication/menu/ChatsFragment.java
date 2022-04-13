package com.example.chatapplication.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatapplication.R;
import com.example.chatapplication.adapter.ChatListAdapter;
import com.example.chatapplication.model.ChatList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatsFragment newInstance(String param1, String param2) {
        ChatsFragment fragment = new ChatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private List<ChatList> list = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getChatlist();
        return view;
    }

    private void getChatlist() {

        list.add(new ChatList("11","Jannat","Hello, I'm Jannat","12/04/2022","https://w0.peakpx.com/wallpaper/262/819/HD-wallpaper-female-anonymous-blue-eyes-character-drawings-girl-hat-mask-scary.jpg"));
        list.add(new ChatList("22","Sumi","Hi, I'm Sumi","12/04/2022","https://dp.profilepics.in/profile_pictures/cartoon_girls/cartoon-profile-pic-03.jpg"));
        list.add(new ChatList("33","Anik","Yo, I'm Anik","12/04/2022","https://w0.peakpx.com/wallpaper/538/896/HD-wallpaper-neon-mask-abstracto-anime-azul-chaqueta-morado-the-purge.jpg"));
        list.add(new ChatList("44","Tamanna","Hey I'm Tamanna","12/04/2022","https://images.hdqwalls.com/download/anime-girl-art-kz-1125x2436.jpg"));

        recyclerView.setAdapter(new ChatListAdapter(list, getContext()));
    }
}