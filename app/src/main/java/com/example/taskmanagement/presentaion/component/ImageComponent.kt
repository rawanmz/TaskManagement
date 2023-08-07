package com.example.taskmanagement.presentaion.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.taskmanagement.R

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    type: Int = 0,
    data: String? = null,
    withGlideCircleCrop: Boolean = false,
    colorTint: Color? = null,
    placeHolder: Painter? = null,
    contentScale: ContentScale = ContentScale.Fit,
    imageBlurred: Boolean = false
) {

    Box {
        val painter = loadPicture(
            type = type,
            data = data.orEmpty(),
            modifier = modifier,
            withCircleCrop = withGlideCircleCrop,
            colorTint = colorTint,
            placeholder = placeHolder,
            imageBlurred = imageBlurred
        )
        if (painter != null) {
            Image(
                painter = painter,
                contentDescription = contentDescription.orEmpty(),
                modifier = modifier,
                colorFilter = if (colorTint != null) ColorFilter.tint(colorTint) else null,
                contentScale = contentScale
            )
        }
    }
}

@Composable
fun loadPicture(
    type: Int = 0,
    data: String,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    withCircleCrop: Boolean = false,
    colorTint: Color? = null,
    imageBlurred: Boolean = false
): Painter? {
    val context = LocalContext.current
    val options: RequestOptions = RequestOptions().autoClone()
    var state by remember {
        mutableStateOf(placeholder)
    }
    val result = object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(p: Drawable?) {
            state = placeholder
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            state = placeholder
        }

        override fun onResourceReady(
            resource: Bitmap,
            transition: Transition<in Bitmap>?,
        ) {
//            if (state == null)
            state = BitmapPainter(resource.asImageBitmap())
        }
    }

    FetchResources(
        context = context,
        result = result,
        type = type,
        data = data,
        modifier = modifier,
        withCircleCrop = withCircleCrop,
        colorTint = colorTint,
        imageBlurred = imageBlurred
    )

    return state
}

@Composable
fun FetchResources(
    context: Context,
    result: CustomTarget<Bitmap>? = null,
    type: Int = 0,
    data: String = "",
    modifier: Modifier = Modifier,
    withCircleCrop: Boolean = false,
    colorTint: Color? = null,
    imageBlurred: Boolean = false
) {
    when (type) {
        MediaResourceTypes.LocaleResource.id -> {
            Image(
                painter = painterResource(id = findResource(data)),
                contentDescription = null,
                modifier = modifier,
                colorFilter = if (colorTint != null) ColorFilter.tint(colorTint) else null
            )
        }
        else -> {
            fetchGlideImageToComposable(
                context = context,
                result = result,
                type = type,
                data = data,
                withCircleCrop = withCircleCrop,
                imageBlurred = imageBlurred
            )
        }
    }
}

private fun findResource(
    name: String,
    defaultImageResource: Int = R.drawable.empty
): Int {
    val localresources: MutableList<DrawableResourcesProvider.DrawableResourceDataModel> =
        DrawableResourcesProvider.loaclDrawableRes
    val resourceId = localresources
        .firstOrNull { it.resourceName.lowercase() == name.lowercase() }
    resourceId?.let {
        return it.resourceId
    } ?: run {
        return defaultImageResource // place holder
    }
}

object DrawableResourcesProvider {
    val loaclDrawableRes = mutableListOf<DrawableResourceDataModel>()

    init {
        loaclDrawableRes.add(
            DrawableResourceDataModel(
                resourceName = "banner_ar_1",
                resourceId = R.drawable.empty
            )
        )
    }

    data class DrawableResourceDataModel(
        val resourceName: String,
        @DrawableRes val resourceId: Int = R.drawable.ic_vector
    )
}

fun fetchGlideImageToComposable(
    context: Context,
    result: CustomTarget<Bitmap>? = null,
    type: Int = 0,
    data: String = "",
    withCircleCrop: Boolean = false,
    imageBlurred: Boolean = false
) {
    when (type) {
        MediaResourceTypes.RawData.id -> {
            result?.let {
//                var glideOptions = GlideOptions()
//                if(withCircleCrop)
//                    glideOptions = GlideOptions().circleCrop()
//
//
                Glide.with(context)
                    .asBitmap()
                    .load(Base64.decode(data, Base64.DEFAULT))
                    .override(400, 400)
                    .into(it)
            }
        }

        else -> {
            result?.let {
                Glide.with(context)
                    .asBitmap()
                    .load(data)
                    .apply(RequestOptions().override(600, 200))
                    //.apply(options.override(200, 200))
                    .into(it)
            }
        }
    }
}

enum class MediaResourceTypes(val id: Int) {
    LocaleResource(2),

    /** Drawable resources **/
    RawData(4),

    /** url **/
//    ImageBase64(7),
    /** URL Image with Headers (Auth, Signature ...), Return the vale (URL) return Image as Base64 **/
}
