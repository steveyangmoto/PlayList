package com.elitemobiletechnology.playlist;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.elitemobiletechnology.playlist.model.Song;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by SteveYang on 4/2/15.
 */
public class PlayListFragment extends Fragment{

    ListView list;
    PlayListAdapter adapter;
    ArrayList<Song> songList = new ArrayList<Song>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        setRetainInstance(true);
        list = (ListView) view.findViewById(R.id.list);

        adapter = new PlayListAdapter(this.getActivity(), songList);
        list.setAdapter(adapter);
        return view;
    }

    @Override public void onResume(){
        super.onResume();
        Utils.bus.register(this);
    }

    @Override public void onPause(){
        Utils.bus.unregister(this);
        super.onPause();
    }

    @Subscribe
    public void answerAvailable(Song song) {
        songList.add(0,song);
        //need to reload the entire list because current list could contain search result only
        adapter = new PlayListAdapter(this.getActivity(), songList);
        list.setAdapter(adapter);
    }

    private ArrayList<Song> search(String keyword){
        ArrayList<Song> songs = new ArrayList<Song>();
        for(Song song:songList){
            if(song.getTitle().contains(keyword)||song.getArtist().contains(keyword)||song.getAlbum().contains(keyword)){
                songs.add(song);
            }
        }
        return songs;
    }

    @Subscribe public void searchRequest(String keyword){
        adapter = new PlayListAdapter(this.getActivity(), search(keyword));
        list.setAdapter(adapter);
    }
}
