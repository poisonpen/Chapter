package com.example.chapter

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter.databinding.FragmentChapterListBinding
import kotlinx.coroutines.launch

private const val TAG = "ChapterListFragment"

class ChapterListFragment : Fragment() {
    private var _binding: FragmentChapterListBinding? = null
    private val binding
    get() = checkNotNull(_binding) {
        "Cannot access binding [null reference]"
    }
    private val chapterListViewModel: ChapterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChapterListBinding.inflate(inflater, container, false)
        binding.chapterRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
               // TODO
                val chapters = chapterListViewModel.loadChapters()
                binding.chapterRecyclerView.adapter =
                    ChapterListAdapter(chapters) {
                        findNavController().navigate(
                            R.id.show_chapter_detail
                        )
                    }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

