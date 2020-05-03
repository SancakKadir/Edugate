package com.ikumb.edugate.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseActivity
import com.ikumb.edugate.databinding.ActivityMainBinding
import com.ikumb.edugate.db.User
import com.ikumb.edugate.utils.domain.show
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity :
    BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java),
    HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.container_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when (NavHostFragment.findNavController(container_fragment).currentDestination?.id) {
                R.id.splashFragment -> Timber.v("Reselect blocked.")
                R.id.dashboardFragment -> Timber.v("Reselect blocked.")
                else -> NavigationUI.onNavDestinationSelected(it, navController)
            }
        }

        FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().uid ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val isStudent = p0.getValue(User::class.java)?.isStudent
                    binding.bottomNavigation.menu.findItem(R.id.studentsFragment).isVisible =
                        isStudent?.not() == true
                }
            })

        findNavController(R.id.container_fragment).addOnDestinationChangedListener { controller
                                                                                     , destination
                                                                                     , arguments ->
            when (controller.currentDestination?.id) {
                R.id.splashFragment -> binding.bottomNavigation.show()
                else -> binding.bottomNavigation.show()
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = findNavController(R.id.container_fragment)
        if (navController.currentDestination?.id != item?.itemId)
            NavigationUI.onNavDestinationSelected(item!!, navController)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.container_fragment).navigateUp()
}
