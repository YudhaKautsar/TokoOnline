package com.yudha.tokoonline.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import com.yudha.tokoonline.widget.CircleImageView

fun EditText.onFocusChanged(hasFocus: (Boolean) -> Unit) {
    this.setOnFocusChangeListener { _, b -> hasFocus(b) }
}

fun EditText.OnTextChangedListener(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let {
                afterTextChanged(editableText.toString())
            }
        }

    })
}

fun getBitmapProfileCircle(name:String): Bitmap{
    val width = 200 // Width of the image
    val height = 200 // Height of the image
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
//    canvas.drawColor(Color.parseColor("#C5E0FD"))
    canvas.drawColor(Color.parseColor("#306BDD"))

    // Draw the initial on the canvas
    val paint = Paint()
    paint.color = Color.WHITE
    paint.textSize = 80f
    paint.isAntiAlias = true
    paint.textAlign = Paint.Align.CENTER

    val x = width / 2f
    val y = height / 2f + (paint.textSize / 3) // Adjust vertical alignment as needed
    canvas.drawText(name, x, y, paint)

    // Set the bitmap to the ImageView
    return bitmap
}


fun createBitMapProfileCircle(name: String, view: CircleImageView) {
    val width = 200 // Width of the image
    val height = 200 // Height of the image
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
//    canvas.drawColor(Color.parseColor("#C5E0FD"))
    canvas.drawColor(Color.parseColor("#306BDD"))

    // Draw the initial on the canvas
    val paint = Paint()
    paint.color = Color.WHITE
    paint.textSize = 80f
    paint.isAntiAlias = true
    paint.textAlign = Paint.Align.CENTER

    val x = width / 2f
    val y = height / 2f + (paint.textSize / 3) // Adjust vertical alignment as needed
    canvas.drawText(name, x, y, paint)

    // Set the bitmap to the ImageView
    view.setImageBitmap(bitmap)
}


fun createBitMapProfile(name: String, view: AppCompatImageView, color: Int =  Color.parseColor("#306BDD")) {
    val width = 200 // Width of the image
    val height = 200 // Height of the image
    val radius = 20f // Radius of the rounded corners

    // Create a bitmap with alpha channel (ARGB)
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Draw the rounded rectangle with the specified radius
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = color
    canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), radius, radius, paint)

    // Draw the initial on the canvas
    paint.color = Color.WHITE
    paint.textSize = 80f
    paint.textAlign = Paint.Align.CENTER
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

    val x = width / 2f
    val y = height / 2f + (paint.textSize / 3) // Adjust vertical alignment as needed
    canvas.drawText(name, x, y, paint)

    // Set the bitmap to the ImageView
    view.setImageBitmap(bitmap)
}

fun getInitial(name: String): String {
    val words = name.split(" ")
    val initials = StringBuilder()
    var count = 0

    for (word in words) {
        if (word.isNotEmpty() && count < 2) {
            initials.append(word[0])
            count++
        }
    }

    return initials.toString().uppercase()
}

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}