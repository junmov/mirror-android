package cn.junmov.mirror.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.apply {
            linkUserBorrow.setNavLink(MainNavDirections.actionGlobalBorrowFragment())
            linkUserRepay.setNavLink(MainNavDirections.actionGlobalDateRepayListFragment())
            linkUserAccount.setNavLink(MainNavDirections.actionGlobalScreenAccountList())
            linkUserAsset.setNavLink(MainNavDirections.actionGlobalScreenAsset())
            linkUserSync.setNavLink(MainNavDirections.actionGlobalScreenSync())
            linkUserAssetLog.setNavLink(MainNavDirections.actionGlobalScreenAssetLogList())
        }
        return binding.root
    }

}