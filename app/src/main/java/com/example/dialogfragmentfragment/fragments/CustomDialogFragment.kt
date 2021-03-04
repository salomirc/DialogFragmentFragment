package com.example.dialogfragmentfragment.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.example.dialogfragmentfragment.R
import kotlinx.android.synthetic.main.fragment_custom_dialog.*

private const val TAG : String = "CustomDialogFragment"

class CustomDialogFragment : DialogFragment() {

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(txt: String)
    }

    private lateinit var fgmView: View
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.fragment_custom_dialog, container, false)
        return fgmView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LinearLayout.LayoutParams.WRAP_CONTENT
            } else {
                LinearLayout.LayoutParams.MATCH_PARENT
            }
        )

        cancelButton.setOnClickListener {
            Log.d(TAG, "onClick: closing dialog")
            dialog?.dismiss()
        }

        okButton.setOnClickListener {
            val inputTxt = inputEditText.text.toString()
            if (inputTxt != "")
            {
                listener?.onFragmentInteraction(inputTxt)
            }
            val x = context
            dialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (targetFragment is OnFragmentInteractionListener) {
            listener = targetFragment as OnFragmentInteractionListener
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
