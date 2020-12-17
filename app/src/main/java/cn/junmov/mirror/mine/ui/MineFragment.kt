package cn.junmov.mirror.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cn.junmov.mirror.databinding.FragmentMineBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : Fragment() {

    private val viewModel: MineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMineBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MineFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.message.observe(viewLifecycleOwner){
            Snackbar.make(view,it,Snackbar.LENGTH_SHORT).show()
        }
    }
}