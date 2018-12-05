package jessehj.newssample.view.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by jessehj on 03/12/2018.
 */

abstract class SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        onTextChanged(editable.toString())
    }

    abstract fun onTextChanged(inputted: String)
}
