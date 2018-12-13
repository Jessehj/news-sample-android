package jessehj.newssample.scene.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import kotlinx.android.synthetic.main.fragment_filter_dialog.view.*

/**
 * Created by jessehj on 06/12/2018.
 */

class FilterDialogFragment : BottomSheetDialogFragment() {

    interface OnFragmentInteractionListener {
        fun onDismissDialog(category: AppConstants.Category, country: AppConstants.Country)
    }

    companion object {
        const val ARG_CATEGORY = "argCategory"
        const val ARG_COUNTRY = "argCountry"

        fun newInstance(
            category: AppConstants.Category?,
            country: AppConstants.Country
        ): FilterDialogFragment {
            return FilterDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CATEGORY, category)
                    putSerializable(ARG_COUNTRY, country)
                }
            }
        }
    }

    private lateinit var category: AppConstants.Category
    private lateinit var country: AppConstants.Country

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            this.getSerializable(ARG_CATEGORY)?.let {
                category = it as AppConstants.Category
            } ?: run {
                category = AppConstants.Category.All
            }

            this.getSerializable(ARG_COUNTRY)?.let {
                country = it as AppConstants.Country
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter_dialog, container, false)

        configViews(view)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        listener?.apply {
            this.onDismissDialog(category, country)
        }
    }

    private fun configViews(view: View) {
        view.apply {
            when (category) {
                AppConstants.Category.Business -> this.businessRadioButton.isChecked = true
                AppConstants.Category.Entertainment -> this.enterRadioButton.isChecked = true
                AppConstants.Category.General -> this.generalRadioButton.isChecked = true
                AppConstants.Category.Health -> this.healthRadioButton.isChecked = true
                AppConstants.Category.Science -> this.scienceRadioButton.isChecked = true
                AppConstants.Category.Sports -> this.sportsRadioButton.isChecked = true
                AppConstants.Category.Technology -> this.techRadioButton.isChecked = true
                else -> this.allRadioButton.isChecked = true
            }

            when (country) {
                AppConstants.Country.Korea -> this.korRadioButton.isChecked = true
                AppConstants.Country.USA -> this.usaRadioButton.isChecked = true
                AppConstants.Country.China -> this.chnRadioButton.isChecked = true
                AppConstants.Country.Japan -> this.jpnRadioButton.isChecked = true
            }

            this.categoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                category = when (checkedId) {
                    R.id.businessRadioButton -> AppConstants.Category.Business
                    R.id.enterRadioButton -> AppConstants.Category.Entertainment
                    R.id.generalRadioButton -> AppConstants.Category.General
                    R.id.healthRadioButton -> AppConstants.Category.Health
                    R.id.scienceRadioButton -> AppConstants.Category.Science
                    R.id.sportsRadioButton -> AppConstants.Category.Sports
                    R.id.techRadioButton -> AppConstants.Category.Technology
                    else -> AppConstants.Category.All
                }
            }

            this.countryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                country = when (checkedId) {
                    R.id.usaRadioButton -> AppConstants.Country.USA
                    R.id.chnRadioButton -> AppConstants.Country.China
                    R.id.jpnRadioButton -> AppConstants.Country.Japan
                    else -> AppConstants.Country.Korea
                }
            }
        }
    }
}