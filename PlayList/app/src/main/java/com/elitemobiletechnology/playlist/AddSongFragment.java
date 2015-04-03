package com.elitemobiletechnology.playlist;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.elitemobiletechnology.playlist.model.Song;

import java.util.ArrayList;

/**
 * Created by SteveYang on 4/2/15.
 */
public class AddSongFragment extends Fragment{
    Button saveButton;
    EditText title;
    EditText artist;
    EditText album;
    EditText etSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_song, container, false);
        setRetainInstance(true);
        title = (EditText)view.findViewById(R.id.etTitle);
        artist = (EditText)view.findViewById(R.id.etArtist);
        album = (EditText)view.findViewById(R.id.etAlbum);
        saveButton = (Button)view.findViewById(R.id.btSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = title.getText().toString();
                String songArtist = artist.getText().toString();
                String songAlbum = album.getText().toString();
                if(songTitle.isEmpty()){
                    title.setError(getResources().getString(R.string.empty_title));
                    return;
                }
                if(songArtist.isEmpty()){
                    artist.setError(getResources().getString(R.string.empty_artist));
                    return;
                }
                if(songAlbum.isEmpty()){
                    album.setError(getResources().getString(R.string.empty_album));
                    return;
                }
                Song newSong = new Song(songTitle,songArtist,songAlbum);
                Utils.bus.post(newSong);
            }
        });

        etSearch = (EditText)view.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.bus.post(etSearch.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        return view;
    }
}
