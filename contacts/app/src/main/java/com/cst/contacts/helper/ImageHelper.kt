package com.cst.contacts.helper

import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import java.util.*


class ImageHelper {

    companion object {
        fun generateContactAvatar(name: String): Bitmap {
            val bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val random = Random()

            // Generate background.

            val background = ShapeDrawable(OvalShape())
            background.setBounds(0, 0, canvas.width, canvas.height)
            background.paint.color = Color.argb(
                255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
            background.draw(canvas)

            // Generate text.

            val text = Paint()
            text.color = Color.WHITE
            text.textAlign = Paint.Align.CENTER
            text.textSize = 600f

            val xPos = canvas.width / 2f
            val yPos = (canvas.height / 2f - (text.descent() + text.ascent()) / 2)

            canvas.drawText(name[0].toString(), xPos, yPos, text)

            return bitmap
        }
    }

}
