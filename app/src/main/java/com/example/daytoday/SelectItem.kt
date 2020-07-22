package com.example.daytoday

/**
 * This interface will act as a call back listener when we click on recyclerview image.
 */
interface SelectItem {
    fun onSelectItem(pos: Int)
}