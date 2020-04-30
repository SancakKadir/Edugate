package com.ikumb.edugate.core

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
