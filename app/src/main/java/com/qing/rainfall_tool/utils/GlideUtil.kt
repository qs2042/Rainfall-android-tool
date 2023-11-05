package com.qing.rainfall_tool.utils

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest


class GlideUtil {
    class BlackWhiteTransformation : BitmapTransformation() {
        override fun transform(
            pool: BitmapPool,
            toTransform: Bitmap,
            outWidth: Int,
            outHeight: Int
        ): Bitmap {
            val width = toTransform.width
            val height = toTransform.height

            val config =
                if (toTransform.config != null) toTransform.config else Bitmap.Config.ARGB_8888
            val bitmap = pool.get(width, height, config)

            val canvas = Canvas(bitmap)
            val paint = Paint()
            val colorMatrix = ColorMatrix().apply {
                setSaturation(0f) // 设置饱和度为0，实现黑白效果
            }
            val filter = ColorMatrixColorFilter(colorMatrix)
            paint.colorFilter = filter
            canvas.drawBitmap(toTransform, 0f, 0f, paint)

            return bitmap
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {
            // 不需要实现
        }
    }
}