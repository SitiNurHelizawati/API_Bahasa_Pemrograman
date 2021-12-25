package simple.example.apibahasapemrograman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context context;
    ArrayList<DataBahasa> datas = new ArrayList();
    LayoutInflater layoutInflater;

    ImageView img;
    TextView title, helloWord, readMore, description;

    public CustomAdapter(Context context, ArrayList<DataBahasa> data) {
        super(context, R.layout.custom_row_list, data);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.datas = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.custom_row_list, parent, false);
        DataBahasa data = datas.get(position);

        img = convertView.findViewById(R.id.imgList);
        title = convertView.findViewById(R.id.titleList);
        readMore = convertView.findViewById(R.id.readMore);
        description = convertView.findViewById(R.id.desc);
        helloWord = convertView.findViewById(R.id.helloWord);

        Glide
                .with(context)
                .load(data.getLogo())
                .into(img);
        title.setText(data.getName());
        helloWord.setText(data.getHelloWord());
        readMore.setText(data.getReadMore());
        description.setText(data.getDesc());

        return convertView;
    }
}
