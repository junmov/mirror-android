package cn.junmov.mirror

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import cn.junmov.mirror.core.data.worker.AccountBalanceMonthlyWorker
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.page_home, R.id.page_budget, R.id.page_wallet, R.id.page_mine)
        )
        toolbar.setupWithNavController(navController, appBarConfiguration)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.page_home, R.id.page_budget, R.id.page_wallet, R.id.page_mine -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavView.visibility = View.VISIBLE
                }
                R.id.sign_in_fragment -> {
                    toolbar.visibility = View.GONE
                    bottomNavView.visibility = View.GONE
                }
                else -> {
                    bottomNavView.visibility = View.GONE
                    toolbar.visibility = View.VISIBLE
                }
            }
        }
        // 每月1号结转上月余额
        if (LocalDate.now().dayOfMonth == 1){
            val request = OneTimeWorkRequestBuilder<AccountBalanceMonthlyWorker>().build()
            WorkManager.getInstance(applicationContext).enqueue(request)
        }
    }
}