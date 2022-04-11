package com.example.chatapplication.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chatapplication.R;
import com.example.chatapplication.adapter.ChatlistAdapter;
import com.example.chatapplication.model.Chatlist;

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
    private List<Chatlist>list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerview=view.findViewById(r.id.recyclerView);
        recyclerView.setLayoutManaher(new LinearLayout(getContext()));

        getChatlist ();
        return view;
    }

    private void getChatlist() {

        list.add(new Chatlist(userID:"11",userName:"shekh hasina",description:"hello frie",date:"11/04/2022",urlProfile:"https://gumlet.assettype.com/nationalherald/2018-12/f0a289f0-3ac6-4ee7-a278-6334e2ba2ec0/bangladesh_polls_sheikh_hasina_wins_new_term_as_prime_minister.jpg?w=1200&h=750&auto=format%2Ccompress&fit=max"));
        list.add(new Chatlist(userID:"22",userName:"khaleda zia",description:"hello frie",date:"11/04/2022",urlProfile:""));
        list.add(new Chatlist(userID:"33",userName:"LyMin",description:"hello frie",date:"11/04/2022",urlProfile:""));
        recyclerView.setADapter(new ChatlistAdapter(new ChatlistAdapter(list.getContext()));
    }
}