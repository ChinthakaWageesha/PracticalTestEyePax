package com.example.practicaltest.core.extenstion

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.example.practicaltest.R

fun EditText.validateOnTextChange(
    message: String? = "error",
    isCheckValidateIcon: Boolean = false,
    validator: (String) -> Boolean
): Boolean {
    this.onTextChanged {
        val errorMessage = if (validator(it)) null else message
        backGroundRunTime(errorMessage, isCheckValidateIcon)
    }
    val errorMessage = if (validator(this.text.toString())) null else message
    backGroundRunTime(errorMessage, isCheckValidateIcon)

    return validator(this.text.toString())
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.clearTextOnRightDrawableClick() {
    this.setOnTouchListener(object : View.OnTouchListener {

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {

            if (event?.action == MotionEvent.ACTION_UP) {

                val drawable = this@clearTextOnRightDrawableClick.compoundDrawables[2]
                if (drawable != null) {
                    val touchEventX = event.x
                    val touchAreaRight = this@clearTextOnRightDrawableClick.right
                    val touchAreaLeft = touchAreaRight - drawable.bounds.width()
                    if (touchEventX >= touchAreaLeft && touchEventX <= touchAreaRight) {
                        view?.performClick();
                        this@clearTextOnRightDrawableClick.clearText()
                    }
                    return true
                }
            }
            return false
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.enableEditOnRightDrawableClick() {
    this.setOnTouchListener(object : View.OnTouchListener {

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {

            if (event?.action == MotionEvent.ACTION_UP) {

                val drawable = this@enableEditOnRightDrawableClick.compoundDrawables[2]
                if (drawable != null) {
                    val touchEventX = event.x
                    val touchAreaRight = this@enableEditOnRightDrawableClick.right
                    val touchAreaLeft = touchAreaRight - drawable.bounds.width()
                    if (touchEventX >= touchAreaLeft && touchEventX <= touchAreaRight) {
                        view?.performClick();
                        this@enableEditOnRightDrawableClick.isEnabled = true
                        this@enableEditOnRightDrawableClick.isFocusable = true
                        this@enableEditOnRightDrawableClick.isClickable = true
                        this@enableEditOnRightDrawableClick.isFocusableInTouchMode = true
                    }
                    return true
                }
            }
            return false
        }
    })
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString().trim())
        }
    })
}

private fun EditText.backGroundRunTime(
    errorMessage: String?,
    isCheckValidateIcon: Boolean
) {

    if (isCheckValidateIcon) {
        if (errorMessage == null) {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                this.context.getCompatDrawable(R.drawable.ic_cross_black),
                null
            )

            this.background = this.context.getCompatDrawable(R.drawable.edit_text_enable)
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )

            this.background = this.context.getCompatDrawable(R.drawable.edit_text_disable)
        }
    } else {
        this.background = this.context.getCompatDrawable(R.drawable.edit_text_disable)
    }

}

fun EditText.getStringTrim(): String {
    return this.text.toString().trim()
}

fun EditText.clearText() {
    this.setText("")
}