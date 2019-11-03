package iim.jetpack.dorianmarques.fr.iima4test

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ListFragment.OnClickItem, GameSheetFragment.OnFragmentInteractionListener {

    override fun onFragmentInteractionListener(link: String?) {
        if (!link.isNullOrBlank()) {
            val ntm = getSharedPreferences("TIMES", Context.MODE_PRIVATE).getInt("times", 0)
            if (ntm.rem(2) == 0) {
                val editor = getSharedPreferences("TIMES", Context.MODE_PRIVATE).edit()
                editor.putInt("times", ntm + 1)
                editor.apply()

                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(link)
                startActivity(openURL)
            } else {
                val editor = getSharedPreferences("TIMES", Context.MODE_PRIVATE).edit()
                editor.putInt("times", ntm + 1)
                editor.apply()

                val transaction = supportFragmentManager.beginTransaction()
                val webFragment = WebFragment.newInstance(link!!)
                transaction.replace(R.id.fragmentContainer, webFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onClickItem(game: Game) {
        val transaction = supportFragmentManager.beginTransaction()
        val gameSheetFragment = GameSheetFragment.newInstance(game)
        transaction.replace(R.id.fragmentContainer, gameSheetFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("TIMES", getSharedPreferences("TIMES", Context.MODE_PRIVATE).getInt("times", 0))
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            val editor = getSharedPreferences("TIMES", Context.MODE_PRIVATE).edit()
            editor.putInt("times", savedInstanceState.getInt("TIMES"))
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val listFragment = ListFragment()
        transaction.replace(R.id.fragmentContainer, listFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        val editor = getSharedPreferences("NTM", Context.MODE_PRIVATE).edit()
        editor.putInt("ntm", 0)
        editor.apply()
    }
}
