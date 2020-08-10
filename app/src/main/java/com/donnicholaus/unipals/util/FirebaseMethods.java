package com.donnicholaus.unipals.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.donnicholaus.unipals.Login;
import com.donnicholaus.unipals.R;
import com.donnicholaus.unipals.Register;
import com.donnicholaus.unipals.models.User;
import com.donnicholaus.unipals.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    private String userID;
    private Context mContext;

    public FirebaseMethods(Context context) {

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();
        mContext = context;

        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public void registerNewUser(final String email, final String username, final String Fullname, final String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Register", "Registration successful");
                            Toast.makeText(mContext, "Registration successful.",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, "Login to Continue.",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);

                            userID = mAuth.getCurrentUser().getUid();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Register", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public boolean checkUsername(String username, DataSnapshot dataSnapshot){

        User user = new User();

        for(DataSnapshot ds: dataSnapshot.child(userID).getChildren()){

            //Fetch username from database and assign it to user usernme

            user.setUsername(ds.getValue(User.class).getUsername());

            if(user.getUsername().equals(username)){
                //Username kama iyo ipo tayar
                return true;
            }

        }
        return false;
    }

    public void addNewUser(String email, long phone_no, String username, String fullname,
                           String description, String profile_photo){

        User user = new User(userID, username, fullname, phone_no, email);
        mRef.child(mContext.getString(R.string.db_user))
                .child(userID)
                .setValue(user);

        UserAccountSettings details = new UserAccountSettings(description, fullname, profile_photo,
                0, 0, 0, username);
        mRef.child(mContext.getString(R.string.db_user_account_settings))
                .child(userID)
                .setValue(details);
    }


}
