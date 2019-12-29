package com.ikumb.edugate.core

import android.view.View
import androidx.databinding.BindingAdapter
import com.ikumb.edugate.utils.domain.hide
import com.ikumb.edugate.utils.domain.show


@BindingAdapter("app:visibility")
fun setVisibilty(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }
}
