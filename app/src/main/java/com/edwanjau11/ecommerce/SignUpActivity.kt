package com.edwanjau11.ecommerce

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.edwanjau11.ecommerce.databinding.ActivitySignUpBinding
import com.edwanjau11.ecommerce.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        val database = Firebase.database.reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        binding.imageButton.setOnClickListener{

        }

        binding.btnSignUp.setOnClickListener {
            //val profilePicture = binding.imageButton
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phoneNumber = binding.etPhone.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()




            //fun writeNewUser(userId: String, firstName: String, lastName: String, phoneNumber:String, email: String, password: String) {
                //val user = User(firstName, lastName, phoneNumber, email, password)

                //database.child("users").child(userId).setValue(user)


            if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val user = User(firstName, lastName, phoneNumber, email, password)
                        if (uid != null){

                            databaseReference.child(uid).setValue(user).addOnCompleteListener {

                                if (it.isSuccessful){
                                    uploadProfilePic()
                                }
                            }
                        }

                        Toast.makeText(this, "User created successfully!" , Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Empty fields are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        binding.tvTerms.setOnClickListener{
            Toast.makeText(this,"Terms clicked", Toast.LENGTH_SHORT).show()
        }


    }
    private fun uploadProfilePic(){
        imageUri = Uri.parse("android.resource")
        storageReference = FirebaseStorage.getInstance().getReference("users/" +firebaseAuth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener{
            Toast.makeText(this,"Profile picture upload successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Profile Picture upload unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }
}



