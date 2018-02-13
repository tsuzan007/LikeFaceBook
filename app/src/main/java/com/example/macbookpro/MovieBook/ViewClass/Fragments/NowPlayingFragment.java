package com.example.macbookpro.MovieBook.ViewClass.Fragments;

import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.macbookpro.likefacebook.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


import java.io.File;

import java.util.ArrayList;

import butterknife.ButterKnife;




public class NowPlayingFragment extends Fragment {


    final String[] permision = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    Intent intent;
    Uri uriSavedImage = null;
    private TextRecognizer textRecognizer;
    private Frame frame;
    File image;
    ArrayList<Rect> rect;
    private ProgressDialog dlg;
    String realpath = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albumfragment, container, false);
        ButterKnife.bind(this, view);
        dlg = new ProgressDialog(getActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();



    }




}
