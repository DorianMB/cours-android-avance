package iim.jetpack.dorianmarques.fr.iima4test

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "name"
private const val ARG_PARAM2 = "desc"
private const val ARG_PARAM3 = "link"
private const val ARG_PARAM4 = "img"

class GameSheetFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_sheet, container, false)
        view.findViewById<Button>(R.id.button).setOnClickListener { listener?.onFragmentInteractionListener(arguments?.getString(ARG_PARAM3, "link")) }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteractionListener(link: String?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView2).text = arguments?.getString(ARG_PARAM1, "title")
        view.findViewById<TextView>(R.id.textView3).text = arguments?.getString(ARG_PARAM2, "desc")
        // view.findViewById<TextView>(R.id.textView2).text = arguments?.getString(ARG_PARAM3, "link")
        val image = view.findViewById<ImageView>(R.id.imageView2)
        Picasso.get().load(arguments?.getString(ARG_PARAM4, "img")).into(image)
    }

    companion object {
        @JvmStatic
        fun newInstance(game: Game) =
            GameSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, game.name)
                    putString(ARG_PARAM2, game.description)
                    putString(ARG_PARAM3, game.link)
                    putString(ARG_PARAM4, game.img)
                }
            }
    }
}
