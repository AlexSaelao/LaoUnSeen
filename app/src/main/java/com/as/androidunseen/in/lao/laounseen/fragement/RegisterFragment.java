package com.as.androidunseen.in.lao.laounseen.fragement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.as.androidunseen.in.lao.laounseen.MainActivity;
import com.as.androidunseen.in.lao.laounseen.R;

public class RegisterFragment extends Fragment {

    //    Explicit
    private Uri uri;
    private ImageView imageView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Photo Controller
        photoController();

    } //Main Class

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));

                //ScaleImage
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 400, 300, true);

                imageView.setImageBitmap(bitmap1);

            }catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getActivity(), "Please Choose Photo", Toast.LENGTH_SHORT).show();
        }
    }

    private void photoController() {
        imageView = getView().findViewById(R.id.imgRegis);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Please Choose App"),1);

            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toobarRegis);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity)getActivity()).getSupportActionBar().setSubtitle("Register lolzzz");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
