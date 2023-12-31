package com.nohchiyn.ui.home

import EntriesAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.nohchiyn.R
import com.nohchiyn.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val handler = Handler(Looper.getMainLooper())
    private var searchRunnable: Runnable? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val randomsButton: AppCompatImageButton = root.findViewById(R.id.btnRefresh)
        val recyclerView: RecyclerView = root.findViewById(R.id.rvMain)
        val searchView: TextView = root.findViewById(R.id.txtSearchPhrase)

//        val adapter = EntriesAdapter(emptyList())

        searchView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Cancel any previous runnable
                searchRunnable?.let { handler.removeCallbacks(it) }

                // Create a new runnable for the new text
                searchRunnable = Runnable {
                    homeViewModel.search(s.toString())
                }

                // Post the runnable with a delay of 250ms
                handler.postDelayed(searchRunnable!!, 250)
            }

            override fun afterTextChanged(s: Editable?) {
                // Do Nothing
            }

        })

        randomsButton.setOnClickListener {
            homeViewModel.loadRandomEntries();
        }

        homeViewModel.entries.observe(viewLifecycleOwner){data ->
            val adapter2 = EntriesAdapter(data)
            recyclerView.adapter = adapter2
        }
        homeViewModel.loadRandomEntries()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}