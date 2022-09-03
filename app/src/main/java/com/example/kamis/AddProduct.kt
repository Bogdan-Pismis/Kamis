package com.example.kamis

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class AddProduct: Fragment(R.layout.fragment_add_product) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var currentImagePath: String? = null

    private lateinit var btnOpenCamera: Button
    private lateinit var ivPhoto: ImageView
    private lateinit var btnSave: Button
    private lateinit var name: EditText
    private lateinit var price: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOpenCamera = view.findViewById(R.id.btnOpenCamera)
        ivPhoto = view.findViewById(R.id.ivImage)
        btnSave = view.findViewById(R.id.idBtnAddProduct)
        name = view.findViewById(R.id.idEdtName)
        price = view.findViewById(R.id.idEdtPriceNumber)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    handleCameraImage(result.data)
                }
            }

        btnSave.setOnClickListener {
            val productName: String = name.getText().toString()
            val productPrice: String = price.getText().toString()
            addProduct(productName, productPrice)
        }
        btnOpenCamera.setOnClickListener {

            //intent to open camera app
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(cameraIntent)

        }
    }


    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        ivPhoto.setImageBitmap(bitmap)

    }

    private fun addProduct(name: String, price: String) {
        // TO DO: AddProduct
    }
}