package com.qing.rainfall_tool.ui.functions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.qing.rainfall_tool.R
import java.io.IOException

class CodeScan : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editText: TextView
    private lateinit var btn: Button
    private lateinit var btnScan: Button
    private lateinit var btnImage: Button

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_code_scan)

        imageView = findViewById(R.id.image)
        editText = findViewById(R.id.et_text)
        btn = findViewById(R.id.btn)
        btnScan = findViewById(R.id.button1)
        btnImage = findViewById(R.id.button2)

        btn.setOnClickListener {
            val s: String = editText.getText().toString().trim()
            val writer = MultiFormatWriter()
            try {
                val matrix = writer.encode(s, BarcodeFormat.QR_CODE, 350, 350)
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(matrix)
                imageView.setImageBitmap(bitmap)
                val manager: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                //                    manager.hideSoftInputFromWindow(editText.getApplicationWindowToken(),0);
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }

        // TODO: 之后改为竖屏扫描
        btnScan.setOnClickListener {
            // 扫码的类型,可选：一维码，二维码，一/二维码, 具体类型查看BarcodeFormat
            val barcodeFormats: MutableCollection<String> = ArrayList()
            barcodeFormats.add("QR_CODE")
            barcodeFormats.add(BarcodeFormat.CODE_39.name)
            barcodeFormats.add(BarcodeFormat.CODE_93.name)
            barcodeFormats.add("CODE_128")
            IntentIntegrator(this)
                .setOrientationLocked(false)
                .setDesiredBarcodeFormats(barcodeFormats)
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan()// 初始化扫码
        }

        btnImage.setOnClickListener {
            // 启动文件选择器
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        // 使用Intent跳转到选择媒体activity
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 将intent的类型设置为image图片
        intent.type = "image/*"
        // 启动intent活动, 并返回给当前activity
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun loadBitmapFromUri(uri: Uri?): Bitmap? {
        try {
            return MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun decodeBarcodeFromBitmap(bitmap: Bitmap): String? {
        val intArray = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(intArray, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        val source = RGBLuminanceSource(bitmap.width, bitmap.height, intArray)
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

        val reader = MultiFormatReader()
        val hints = mutableMapOf<DecodeHintType, Any>()
        hints[DecodeHintType.POSSIBLE_FORMATS] = listOf(BarcodeFormat.QR_CODE) // 可以根据需要添加其他条形码格式
        reader.setHints(hints)

        try {
            val result = reader.decode(binaryBitmap)
            return result.text
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    // 用于接收其他activity返回的结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 如果是图片上传方式
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            val bitmap: Bitmap? = loadBitmapFromUri(selectedImage)

            if (bitmap != null) {
                val barcodeResult: String? = decodeBarcodeFromBitmap(bitmap)
                if (barcodeResult != null) {
                    // 在这里处理扫描到的条形码结果
                    Toast.makeText(this, "Scanned: $barcodeResult", Toast.LENGTH_LONG).show();
                    // barcodeResult 包含解析到的条形码数据
                }
            }

            return
        }

        // 如果是调用摄像头方式
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.i("2042", "onActivityResult: requestCode($requestCode)")
        Log.i("2042", "onActivityResult: resultCode($resultCode)")
        Log.i("2042", "onActivityResult: data($data)")
        Log.i("2042", "onActivityResult: result($result)")
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
