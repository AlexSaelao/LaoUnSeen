package com.as.androidunseen.in.lao.laounseen.fragement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.as.androidunseen.in.lao.laounseen.MainActivity;
import com.as.androidunseen.in.lao.laounseen.R;
import com.as.androidunseen.in.lao.laounseen.utility.MyAlert;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterFragment extends Fragment {

    //    Explicit
    private Uri uri;
    private ImageView imageView;
    boolean aBoolean = true;
    private String nameString, emailString, passwordString,
            uidString, pathURLString, myPostString;

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

        inflater.inflate(R.menu.menu_register, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {
            uploadProcess();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void uploadProcess() {

        EditText nameEditText = getView().findViewById(R.id.edtNameRegis);
        EditText emailEditText = getView().findViewById(R.id.edtEmailRegis);
        EditText passwordEditText = getView().findViewById(R.id.edtPasswordRegis);

//        Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

//        Check choose photo
        if (aBoolean) {
//            Non Choose Photo
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog("Non Choose Photo",
                    "Please Choose Photo");

//             Check name || email || password == null
        } else if (nameString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {

//            Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog("Have Space",
                    "Please Fill All Every Blank");

        } else {
//            No Space
            createAuthentication();
            uploadPhotoToFirebase();
        }
    }

    private void createAuthentication() {

        Log.d("8AugV1", "CreateAuthen Work");

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    uidString = firebaseAuth.getCurrentUser().getUid();
                    Log.d("8AugV1", "uidString ==>" + uidString);

                } else {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Cannot Rregister",
                            "Beacause ==>" + task.getException().getMessage());
                    Log.d("8AugV1", "Error ==>" + task.getException().getMessage());
                }
            }
        });





    }

    private void uploadPhotoToFirebase() {

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference storageReference1 = storageReference.child("Avata/" + nameString);
        storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(),"Success Upload Photo",Toast.LENGTH_SHORT).show();

                findPathUrlPhoto();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Cannot Upload Photo",Toast.LENGTH_SHORT).show();
            }
        });

    } // UploadPhoto

    private void findPathUrlPhoto() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();
            aBoolean = false;

            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));

                //ScaleImage
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 400, 300, true);

                imageView.setImageBitmap(bitmap1);

            } catch (Exception e) {
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
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);

            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toobarRegis);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Register lolzzz");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
