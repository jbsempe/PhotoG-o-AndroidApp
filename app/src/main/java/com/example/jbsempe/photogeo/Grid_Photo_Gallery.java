package com.example.jbsempe.photogeo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jbsempe.photogeo.Model.Photo;

public class Grid_Photo_Gallery extends ArrayAdapter<Photo> {

    private Context context;
    private int layoutResourceId;
    private List<Photo> data;

    public Grid_Photo_Gallery(Context context, int layoutResourceId, List<Photo> _data) {
        super(context, layoutResourceId, _data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = _data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.title);
            holder.imageId = (TextView) row.findViewById(R.id.id);
            holder.image = (ImageView) row.findViewById(R.id.image);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Photo photo = data.get(position);
        holder.imageTitle.setText(photo.get_libelle());
        holder.imageId.setText(Integer.toString(photo.get_id()));
        if (photo.get_photouri()!= null)
        holder.image.setImageURI(Uri.parse(photo.get_photouri()));
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        TextView imageId;
        ImageView image;
    }
}