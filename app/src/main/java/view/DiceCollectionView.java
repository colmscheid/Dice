package view;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.christian.dice.R;

import model.DiceCollection;

/***
 * A view to show a dice collection
 */

public class DiceCollectionView extends TextView {

    private DiceCollection collection;

    public DiceCollectionView(Context context, DiceCollection collection) {

        super(context);

        this.collection = collection;

        setText("Collection: " + collection.getName());
        setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        this.setBackgroundResource(R.drawable.collection_layout);
    }
}
