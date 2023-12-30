package com.nohchiyn

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.nohchiyn.databinding.ActivityMainBinding
import com.nohchiyn.entities.RealmChangeSet
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmSound
import com.nohchiyn.entities.RealmSource
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.entities.RealmUser
import com.nohchiyn.services.FileService
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileService: FileService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
//
//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_alphabet
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fileService = FileService(this);
        fileService.deployLocalDatabase();

        // Setup local database access
        val config = RealmConfiguration.Builder(
            schema = setOf(
                RealmChangeSet::class,
                RealmEntry::class,
                RealmSource::class,
                RealmSound::class,
                RealmUser::class,
                RealmTranslation::class,
            )
        ).schemaVersion(18).name("local.datx").build()

        val realm: Realm = Realm.open(config)

//        realm.writeBlocking {
//            val test = RealmChangeSet();
//            copyToRealm(test.apply {
//                changeSetIndex = 2;
//                recordId = "asdfdsf";
//            })
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}