package jessehj.newssample.view.custom

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import java.util.*

/**
 * Created by jessehj on 06/12/2018.
 */

class MultilineRadioGroup : RadioGroup {

    private var checking = false

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        setOnHierarchyChangeListener(object : ViewGroup.OnHierarchyChangeListener {
            override fun onChildViewRemoved(parent: View, child: View) {
                if (parent === this@MultilineRadioGroup && child is ViewGroup) {
                    for (radioButton in getRadioButtonFromGroup(child)) {
                        radioButton.setOnCheckedChangeListener(null)
                    }
                }
            }

            override fun onChildViewAdded(parent: View, child: View) {
                if (parent === this@MultilineRadioGroup && child is ViewGroup) {
                    for (radioButton in getRadioButtonFromGroup(child)) {
                        var id = radioButton.id
                        // generates an id if it's missing
                        if (id == View.NO_ID) {
                            if (Build.VERSION.SDK_INT >= 17)
                                id = View.generateViewId()
                            else
                                id = radioButton.hashCode()
                            radioButton.id = id
                        }
                        if (radioButton.isChecked) {
                            check(id)
                        }

                        radioButton.setOnCheckedChangeListener(object :
                            CompoundButton.OnCheckedChangeListener {
                            override fun onCheckedChanged(
                                buttonView: CompoundButton,
                                isChecked: Boolean
                            ) {
                                if (isChecked) {
                                    radioButton.setOnCheckedChangeListener(null)
                                    check(buttonView.id)
                                    radioButton.setOnCheckedChangeListener(this)
                                }
                            }
                        })

                    }
                }
            }
        })
    }

    override fun check(id: Int) {
        if (checking) return
        checking = true
        super.check(id)
        checking = false
    }

    private fun getRadioButtonFromGroup(group: ViewGroup?): ArrayList<RadioButton> {
        if (group == null) return ArrayList()
        val list = ArrayList<RadioButton>()
        getRadioButtonFromGroup(group, list)
        return list
    }

    private fun getRadioButtonFromGroup(group: ViewGroup, list: ArrayList<RadioButton>) {
        var i = 0
        val count = group.childCount
        while (i < count) {
            val child = group.getChildAt(i)
            if (child is RadioButton) {
                list.add(child)

            } else if (child is ViewGroup) {
                getRadioButtonFromGroup(child, list)
            }
            i++
        }
    }

}
