package com.elitemobiletechnology.playlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elitemobiletechnology.playlist.model.Song;

import java.util.ArrayList;

public class PlayListAdapter extends BaseAdapter {
    private static final String TAG = "PlayListAdapter";
    private Activity activity;
    private ArrayList<Song> songList;
    private LayoutInflater inflater;

    public PlayListAdapter(Activity a, ArrayList<Song> songList) {
        activity = a;
        this.songList = songList;
        inflater = (LayoutInflater) a
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return songList.size();
    }

    public Object getItem(int position) {
        return songList.get(position);
    }

    public long getItemId(int position) {
        return songList.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Song song = songList.get(position);
        View vi = convertView;
        ViewHolder holder = null;
        if (vi == null) {
            vi = inflater.inflate(R.layout.playlist_row, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.tvTitle);
            holder.artist = (TextView) vi.findViewById(R.id.tvArtist);
            holder.album = (TextView) vi.findViewById(R.id.tvAlbum);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
        holder.album.setText(song.getAlbum());
        return vi;
    }

    static class ViewHolder {
        TextView title;
        TextView artist;
        TextView album;
    }
}