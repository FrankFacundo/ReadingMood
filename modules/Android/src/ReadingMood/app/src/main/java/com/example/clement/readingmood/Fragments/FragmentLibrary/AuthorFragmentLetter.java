package com.example.clement.readingmood.Fragments.FragmentLibrary;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.Adapters.AtmosphereAdapter.AdapterSong;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterLibraryName;
import com.example.clement.readingmood.Adapters.LibraryAdapter.AdapterListAuthor;
import com.example.clement.readingmood.Adapters.LibraryAdapter.MyAdapterMyLibraryLetter;
import com.example.clement.readingmood.Objets.Atmosphere;
import com.example.clement.readingmood.Objets.BookMyLibrary;
import com.example.clement.readingmood.Objets.DescriptionAtmosphere;
import com.example.clement.readingmood.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class AuthorFragmentLetter extends Fragment {

    private final String[] listLetter ={ "A","B","C", "D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    /*
    List of atmosphere to display
     */
    private View v;
    /*
    Current view
     */

    private AuthorFragmentList myFragment;

    private Spinner mySpinner;

    private String currentLettre = "A";

    private TextView sortText;

    private FragmentTransaction ft;


    public AuthorFragmentLetter() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        Initialise the view
         */
        v = inflater.inflate(R.layout.library_name_fragment, container, false);
        mySpinner = v.findViewById(R.id.spinner_library_letter_author);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.letters_array, android.R.layout.simple_spinner_item);
        sortText = v.findViewById(R.id.sort_library_author);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        mySpinner.setAdapter(adapter);
        initialiseSpinner();
        createFrag();

        return v;

    }



    private void createFrag()
    {

        ft = getFragmentManager().beginTransaction();
//        listDisplay = new ArrayList<>();
        if (myFragment != null)
        {
            myFragment.onStop();
            myFragment= null;
        }
        myFragment = AuthorFragmentList.create1(currentLettre);
        ft.replace(R.id.layout_to_inflate_library_letter_author,myFragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    public void getAuthorFromName(String name)
    {
        sortText.setText("Result author for :" + name);
        ft = getFragmentManager().beginTransaction();
//        listDisplay = new ArrayList<>();
        if (myFragment != null)
        {
            myFragment.onStop();
            myFragment= null;
        }
        myFragment = AuthorFragmentList.create2(name);
        ft.replace(R.id.layout_to_inflate_library_letter_author,myFragment);
        ft.addToBackStack(null);
        ft.commit();
    }




    private void initialiseSpinner()
    {
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currentLettre= parent.getItemAtPosition(position).toString();
                sortText.setText("Sort Authors by");
//                Toast.makeText(parent.getContext(), "Selected: " + currentLettre, Toast.LENGTH_LONG).show();
                createFrag();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }







}
