package com.example.dialogfragmentfragment.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialogfragmentfragment.R
import kotlinx.android.synthetic.main.fragment_main.*

private const val TAG : String = "MainFragmentActivity"
private const val MESSAGE_KEY : String = "messageTextView"

class MainFragment : Fragment(), CustomDialogFragment.OnFragmentInteractionListener {
    private lateinit var fgmView: View
    private var listenerMain: OnMainFragmentInteractionListener? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MESSAGE_KEY, messageTextView.text.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.fragment_main, container, false)

        return fgmView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val message = savedInstanceState.getString(MESSAGE_KEY)
            messageTextView.text = message
        }

        openDialogButton.setOnClickListener {
            Log.d(TAG, "onClick: opening dialog.")

            val dialog = CustomDialogFragment()
            dialog.setTargetFragment(this, 100)
            dialog.show(fragmentManager!!, "MyCustomDialog")
        }
    }

    fun onButtonPressed(uri: Uri) {
        listenerMain?.onMainFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainFragmentInteractionListener) {
            listenerMain = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerMain = null
    }

    interface OnMainFragmentInteractionListener {
        fun onMainFragmentInteraction(uri: Uri)
    }

    override fun onFragmentInteraction(txt: String) {
        messageTextView.text = txt
    }

}
