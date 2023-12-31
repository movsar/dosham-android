package com.nohchiyn.ui.alphabet

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nohchiyn.R
import com.nohchiyn.databinding.FragmentAlphabetBinding
import java.io.IOException


class AlphabetFragment : Fragment() {

    private var _binding: FragmentAlphabetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val alphabetViewModel =
                ViewModelProvider(this).get(AlphabetViewModel::class.java)

        _binding = FragmentAlphabetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mPlayer = MediaPlayer()

        val container = root.findViewById<TableLayout>(R.id.tlAlpha) // Replace with your actual container ID
        var counter: Int = 0
        for (i in 0 until container.childCount) {
            val tableRow = container.getChildAt(i) as TableRow
            for (j in 0 until tableRow.childCount) {
                val child = tableRow.getChildAt(j)
                if (child is ImageButton) {
                    child.setOnClickListener { onBtnClicked(it) }
                    counter++
                }
            }
        }

        return root
    }

    var assetFileDescriptor: AssetFileDescriptor? = null
    var mPlayer: MediaPlayer? = null


    @Throws(IOException::class)
    fun onBtnClicked(v: View) {
            var audioFileName = v.resources.getResourceName(v.id)
            audioFileName = audioFileName.substring(audioFileName.indexOf("_") + 1) + ".flac"

            /*
        switch (v.getId()) {
            case R.id.ibtn_a:
                Log.d("123", "a");
                break;
            case R.id.ibtn_a_one:
                Log.d("123", "aь");
                break;
            case R.id.ibtn_b:
                Log.d("123", "б");
                break;
        }
*/
            if (mPlayer != null) {
                if (mPlayer!!.isPlaying) {
                    mPlayer!!.stop()
                    mPlayer!!.reset()
                }
                mPlayer!!.reset()
            } else mPlayer = MediaPlayer()

            val appPath: String = requireContext().filesDir.absolutePath
            val filePath = "${appPath}/alpha/${audioFileName}";

            mPlayer!!.setDataSource(filePath)
            mPlayer!!.prepare()
            mPlayer!!.start()
    }

//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        val res = this.resources
//        // Change locale settings in the app.
//        val dm = res.displayMetrics
//        val conf = res.configuration
//        conf.locale = Locale(UI_LANG)
//        res.updateConfiguration(conf, dm)
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}