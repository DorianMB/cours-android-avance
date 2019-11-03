package iim.jetpack.dorianmarques.fr.iima4test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ExampleAdapter(private val list: GameInterface) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.layout_example, parent, false)
        return ExampleViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = list.game.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int)
    {
        holder.bind(list.game[position])
        holder.itemView.setOnClickListener {
            list.open(list.game[position])
        }
    }

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView =
                itemView.findViewById(R.id.textView)
        private val image: ImageView =
            itemView.findViewById(R.id.imageView)

        fun bind(game: Game){
            textView.text = game.name
            Picasso.get().load(game.img).into(image)
        }
    }
}