package com.appssquare.androidtask.Utils

import android.content.Context
import android.text.TextUtils.replace
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appssquare.androidtask.R

fun Context.ShowToast(message: String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
fun Fragment.ShowToast(message: String){
    Toast.makeText(this.context,message, Toast.LENGTH_SHORT).show()
}
fun Fragment.replaceFragment(
    fragment: Fragment,
    @IdRes id: Int,
    addToBackStack: Boolean = false) {
    val trans = activity?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(id, fragment)

    if (addToBackStack)
        trans?.addToBackStack("")
    try {
        trans?.commit()
    } catch (e: Exception) {

    }
}