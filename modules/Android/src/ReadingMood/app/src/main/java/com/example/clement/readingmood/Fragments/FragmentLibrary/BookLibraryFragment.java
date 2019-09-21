package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.R;
import java.util.ArrayList;

public class BookLibraryFragment extends Fragment {

    private final String[] listLetter ={ "A","B","C", "D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private Spinner mySpin;
    private FrameLayout layoutToInflate;
    private View v;
    private String currentLettre = "a";
    private FragmentLibraryLetter fragmentLetter;
    private FragmentTransaction ft;
    private TextView sortText;


    public static BookLibraryFragment createFrag(String letter)
    {
        BookLibraryFragment myFrag = new BookLibraryFragment();
        Bundle myBundle = new Bundle();
        myBundle.putString("letter", letter);
        myFrag.setArguments(myBundle);
        return myFrag;

    }
    public BookLibraryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.library_letter_fragment, container, false);
        mySpin = v.findViewById(R.id.spinner_library_letter);
        layoutToInflate = v.findViewById(R.id.layout_to_inflate_library_letter);
        sortText = v.findViewById(R.id.sort_library);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.letters_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        mySpin.setAdapter(adapter);

        Bundle myBundle = getArguments();
        try
        {
            if (myBundle.getString("letter") != null)
            {
                currentLettre = myBundle.getString("letter");
            }

        } catch (NullPointerException e)
        {
        }
        initialiseListener();
//        createFrag();

    }



    public void getBooksFromName(String name)
    {
        sortText.setText("Result for : " + name);
        ft = getFragmentManager().beginTransaction();
//        listDisplay = new ArrayList<>();
        if (fragmentLetter != null)
        {
            fragmentLetter.onStop();
            fragmentLetter = null;
        }

        fragmentLetter = FragmentLibraryLetter.createFragment2(name);
        ft.replace(R.id.layout_to_inflate_library_letter,fragmentLetter);
        ft.addToBackStack(null);
        ft.commit();

    }



    private void createFrag()
    {

        ft = getFragmentManager().beginTransaction();
//        listDisplay = new ArrayList<>();
        if (fragmentLetter != null)
        {
            fragmentLetter.onStop();
            fragmentLetter = null;
        }

        fragmentLetter = FragmentLibraryLetter.createFragment1(currentLettre);


        ft.replace(R.id.layout_to_inflate_library_letter,fragmentLetter);
        ft.addToBackStack(null);
        ft.commit();
    }




    private void initialiseListener()
    {
        mySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentLettre= parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), "Selected: " + currentLettre, Toast.LENGTH_LONG).show();
                sortText.setText("Sort by");

                createFrag();


                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }






}
