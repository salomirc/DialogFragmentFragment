package com.example.dialogfragmentfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogfragmentfragment.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.OnMainFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onMainFragmentInteraction(txt: String) {

    }
}
