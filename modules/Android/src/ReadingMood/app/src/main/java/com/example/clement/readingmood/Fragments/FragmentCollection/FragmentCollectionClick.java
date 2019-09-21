package com.example.clement.readingmood.Fragments.FragmentCollection;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clement.readingmood.Pages.MainActivity;
import com.example.clement.readingmood.Pages.Manuel;
import com.example.clement.readingmood.Pages.Research;
import com.example.clement.readingmood.Pages.Resume;
import com.example.clement.readingmood.R;
import com.example.clement.readingmood.Singletons.SingletonForRead;


public class FragmentCollectionClick extends Fragment {
    private static final String LOG_TAG = FragmentCollectionClick.class.getSimpleName();

    private View v;

    private Toolbar myToolBar;

    private TextView textTitle;

    private TextView textAuthor;

    private WebView textSummary;

    private ImageView imageBook, play, delete, manual, displayPlotButton;

    private LinearLayout layoutForPlot;

    private boolean isPlotDisplayed = false;

    private String[] listDialog = {"Automatic Mode", "Manual Mode"};

    private String pathToUrl;



    public static FragmentCollectionClick create(String title, String author, String summary, int pathImage, String pathToRead)
    /*
    static factory method
     */
    {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("author",author);
        args.putString("summary",summary);
        args.putInt("image",pathImage);
        args.putString("path", pathToRead);
        FragmentCollectionClick fragment = new FragmentCollectionClick();
        fragment.setArguments(args);
        return fragment;

    }
    public FragmentCollectionClick(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle argument = getArguments();
        this.v = inflater.inflate(R.layout.fragment_collection_click, container, false);
        textTitle = v.findViewById(R.id.title_dialog_my_collection_fragment);
        textAuthor = v.findViewById(R.id.author_dialog_my_collection_fragment);
        textSummary = v.findViewById(R.id.summary_dialog_my_collection_fragment);
        imageBook = v.findViewById(R.id.book_dialog_image_fragment);
        play = v.findViewById(R.id.play_button_dialog_my_collection_fragment);


        manual = v.findViewById(R.id.manual_mode_dialog_my_collection_fragment);
        layoutForPlot = v.findViewById(R.id.layout_collection_click);
        displayPlotButton = v.findViewById(R.id.display_plot_collection_click);
        if (argument!=null)
        {
            textTitle.setText(argument.getString("title"));
            textAuthor.setText(argument.getString("author"));

            pathToUrl = argument.getString("path");
            Glide.with(imageBook.getContext()).load( argument.getInt("image")).into(imageBook);
        }
        configureToolbar();
        initialiseListener(argument.getString("summary"));
        return v;
    }


    private void initialiseListener(final String summary)
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

        final Dialog myDialog = new Dialog(getContext());

        myDialog.setContentView(R.layout.dialog_manual_auto);

        TextView modeChosen = myDialog.findViewById(R.id.send_dialog);

        final RadioGroup myRadioGroup = myDialog.findViewById(R.id.radio_group_dialog);

        modeChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               switch(myRadioGroup.getCheckedRadioButtonId())
               {

                   case R.id.automatic_mode_dialog :
                        addIntentResume(v);
                       break;
                   case R.id.manual_mode_dialog :
                       addIntent(v);
                       break;
               }
                myDialog.hide();
            }
        });


        myDialog.show();



            }
        });

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIntent(v);
            }
        });

        layoutForPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPlotDisplayed)
                {
                    displayPlotButton.setImageResource(R.drawable.false_spinner_white);
                    textSummary.loadUrl("");
                    isPlotDisplayed = false;
                }else
                {
                    displayPlotButton.setImageResource(R.drawable.false_spinner_white_2);
                    textSummary.loadDataWithBaseURL("file:///android_asset/",
                            SingletonForRead.getInstance().getBooks().getListAllTextToDisplay().get(1), "text/html", "utf-8",null);
                    isPlotDisplayed = true;
                }


            }
        });


    }

    private void addIntent(View v)
    {
        final Context context = v.getContext();
        Intent intent = new Intent(context, Manuel.class);
        intent.putExtra("path", pathToUrl);
        context.startActivity(intent);
    }

    private void addIntentResume(View v)
    {
        final Context context = v.getContext();
        Intent intent = new Intent(context, Resume.class);
        Bundle myBundle = new Bundle();
        myBundle.putBoolean("isManual", false);
        intent.putExtras(myBundle);
        Log.e("AUTOMATIC","Test");
        context.startActivity(intent);
    }

    private void configureToolbar()
    {
        myToolBar = v.findViewById(R.id.toolbar_collection_clicked);
        myToolBar.setNavigationIcon(R.drawable.back_button);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

}
