package com.example.bottomup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private val TAG = "MainActivity"
    }
    private var  topSheetDialog: BottomSheetDialog? = null

    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var topSheetBehavior: TopSheetBehavior<*>? = null
    private var mTextView: TextView? = null
    private var sheet_state = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheet = bottom_sheet

        topSheetBehavior = TopSheetBehavior.from(bottomSheet)
        mTextView = txt_state

        topSheetBehavior?.peekHeight = 10

        val btn_exp = btn_exp
        val btn_clsp = btn_collasp

        btn_exp.setOnClickListener {
            topSheetBehavior?.setState(TopSheetBehavior.STATE_EXPANDED)
        }

        btn_clsp.setOnClickListener {
            topSheetBehavior?.state = TopSheetBehavior.STATE_COLLAPSED
        }

        topSheetBehavior?.setTopSheetCallback(object : TopSheetBehavior.TopSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                when (i) {
                    TopSheetBehavior.STATE_COLLAPSED -> {
                        mTextView?.setText("Collapsed")
                        sheet_state = true
                        fab.setImageResource(R.drawable.curtain_button_on)
                        Log.d(TAG, "tag is: ${fab.getTag()}")
                    }
                    TopSheetBehavior.STATE_DRAGGING -> mTextView?.setText("Dragging...")
                    TopSheetBehavior.STATE_EXPANDED -> {
                        mTextView?.setText("Expanded")
                        sheet_state = false
                        Log.d(TAG, "tag is: ${fab.getTag()}")
                        fab.setImageResource(R.drawable.curtain_button_off)
                        Log.d(TAG, "tag is: ${fab.getTag()}")
                    }
                    TopSheetBehavior.STATE_HIDDEN -> {
                        mTextView?.setText("Hidden")
                        topSheetBehavior?.state = TopSheetBehavior.STATE_COLLAPSED
                    }
                    TopSheetBehavior.STATE_SETTLING -> mTextView?.setText("Settling...")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float, isOpening: Boolean?) {
                mTextView?.setText("Sliding.....")
            }
        })

        fab.setOnClickListener {
            if (sheet_state){
                topSheetBehavior?.setState(TopSheetBehavior.STATE_EXPANDED)
            }
            else{
                topSheetBehavior?.setState(TopSheetBehavior.STATE_COLLAPSED)
            }
        }
    }
}