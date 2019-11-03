package iim.jetpack.dorianmarques.fr.iima4test

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONArray
import org.json.JSONObject


class ListFragment: Fragment() {

    private var listener: OnClickItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickItem) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnClickItem {
        fun onClickItem(game: Game)
    }

    private fun fetchData()
    {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        fragRecyclerView?.layoutManager = layoutManager
        val URL = "https://my-json-server.typicode.com/bgdom/cours-android/games"
        val queue = Volley.newRequestQueue(activity)
        val request = StringRequest(Request.Method.GET, URL, Response.Listener<String> { response: String ->
            fragRecyclerView?.adapter = ExampleAdapter(object: GameInterface {
                override val game: Array<Game> = Gson().fromJson(response, Array<Game>::class.java)


                override fun open(game: Game) {
                    Log.d("game", game.name)
                    listener?.onClickItem(game)
                }

            })
            swiperefresh.isRefreshing = false
        }, Response.ErrorListener { error -> Log.e("test", error.localizedMessage) ; error.printStackTrace()})

        queue.add(request)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swiperefresh.setOnRefreshListener { fetchData() }
        fetchData()
    }
}
