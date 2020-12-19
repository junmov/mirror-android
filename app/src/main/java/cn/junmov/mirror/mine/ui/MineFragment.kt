package cn.junmov.mirror.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.databinding.FragmentMineBinding
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
            linkMyAsset.navTo(MineFragmentDirections.actionPageMineToAssetFragment())
            linkMyAuditPlan.navTo(MineFragmentDirections.actionPageMineToAuditPlanFragment())
            linkMyDebt.navTo(MineFragmentDirections.actionPageMineToBillTabFragment())
            linkMyThing.navTo(MineFragmentDirections.actionPageMineToThingFragment())
            linkMyTodo.navTo(MineFragmentDirections.actionPageMineToTodoFragment())
            btnSync.navTo(MineFragmentDirections.actionPageMineToSyncFragment())
        }
        return binding.root
    }

}