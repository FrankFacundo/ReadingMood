package com.example.clement.readingmood.Fragments.FragmentCollection;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clement.readingmood.Adapters.CollectionAdapter.MyAdapterMyCollectionVersion2;
import com.example.clement.readingmood.Objets.BookMyCollection;
import com.example.clement.readingmood.SQLite.DataBaseMyCollection;
import com.example.clement.readingmood.Singletons.Singleton;
import com.example.clement.readingmood.Singletons.SingletonDataBaseMyColletion;
import com.example.clement.readingmood.R;

import java.util.ArrayList;

public class MyCollectionFragmentVersion2 extends Fragment {

    private ArrayList<BookMyCollection> list_displayed = new ArrayList<BookMyCollection>();
    /*
    List of Book (that is a JavaClass) that will be displayed in the recycleView
     */
    private String titles[] = {"Harry-Potter And The Chamber Of Secrets",
            "Harry Potter and the Philosopher's Stone",
            "Harry Potter and the Prisoner of Azkaban"};

    private ArrayList<String> summaryAll = new ArrayList<String>();

    private String summary = "\n" +
            "\n" +
            "Harry Potter and the Sorcerer's Stone is the first in a planned series of seven books. Harry's wizard and witch parents, James and Lily, have just been killed by Voldemort, an evil wizard who was thwarted and severely weakened when he tried to kill one-year-old Harry. The murder attempt leaves Harry with a lightning-shaped scar on his forehead. Harry is whisked off to live with his Muggle (non-magical) suburban middle-class aunt and uncle (Petunia and Vernon Dursley) and their bullying overweight son, Dudley.\n" +
            "\n" +
            "Fast-forward nearly ten years and chapter two begins when Harry is almost eleven and suffering a Dickensian childhood, forced to live in the cupboard under the stairs at 4 Privet Drive, the home of the Dursleys. Harry has not been told of his heritage, and is unaware of his own fame in the wizard world. He is punished when any hint of the out-of-ordinary appears, such as when he communicates with a snake at the zoo.\n" +
            "\n" +
            "The narrative then follows our bespectacled young protagonist as Hagrid, the huge groundskeeper of Hogwarts School of Witchcraft and Wizardry, informs Harry that he is invited to attend the school, takes him shopping for the necessary school equipment such as cauldron and wand, and offers the first sign of affection that Harry can remember. Uncle Vernon rants and tries to prevent Harry from attending the school.\n" +
            "\n" +
            "But when Vernon ridicules the name of Albus Dumbledore, the wise and beloved headmaster of Hogwarts, Hagrid hexes Dudley who sprouts a pig's tail, necessitating a visit to a private hospital. The train for Hogwarts leaves from London's King's Cross station, where Harry befriends the wizard Weasley family, who show him how to enter the magical Platform Nine and Three-Quarters.\n" +
            "\n" +
            "Hogwarts has four houses, and the new first year students are placed into the appropriate house (Gryffindor, Ravenclaw, Hufflepuff, and the sinister Slytherin) by a Sorting hat. Harry's life takes a definite upturn as he finds he is a natural broomstick flyer and is chosen for the Gryffindor Quidditch team. This high flying game with three kinds of balls and seven players per team does lead to injuries. Madam Pomfrey is the school nurse and runs the hospital wing. She cures with special spells and the magic of rest.\n" +
            "\n" +
            "The year at Hogwarts is filled with adventure, friendship, and danger. There are characters who seem to detest Harry, such as Potions teacher Severus Snape and a Slytherin first year, Draco Malfoy, mysterious characters such as Defense Against the Dark Arts Professor Quirrell, as well as a colorful assortment of ghosts and magical creatures. Harry and his Gryffindor friends Ron Weasley and Hermione Granger enter a quest: to prevent the sorcerer's stone from falling into the hands of Voldemort and his allies. The stone offers eternal life and hence would be key to Voldemort's plans to return to power.\n" +
            "\n" +
            "During part of the adventure, Harry finds the Mirror of Erised, and mourns the loss of his parents anew as he sees them in the reflection. As the astute headmaster Dumbledore teaches Harry, however, love is more powerful than evil and death may not be the worst outcome: \"After all, to the well-organized mind, death is but the next great adventure.\" (p. 297)\n";

    private String summary1 = " Harry Potter and the Chamber of Secrets begins when Harry is spending a miserable summer with his only remaining family, the Dursleys. During a dinner party hosted by his uncle and aunt, Harry is visited by Dobby, a house-elf. Dobby warns Harry not to return to Hogwarts, the magical school for wizards that Harry attended the previous year. Harry politely disregards the warning, and Dobby wreaks havoc in the kitchen, infuriating the Dursleys. The Dursleys angrily imprison Harry in his room for the rest of the summer. Luckily, Harry's friend Ron Weasley steals Harry away in a flying car, and Harry happily spends the rest of the summer at the Weasley home.\n" +
            "\n" +
            "While shopping for school supplies with the Weasleys, Harry has two unfortunate encounters. He first encounters Lockhart, one of his teachers, who demands to be in a photo shoot with Harry. Harry then encounters Lucius Malfoy, the evil father of one of Harry's enemies, who almost starts a fight with Mr. Weasley. As Harry prepares to return to Hogwarts, he finds that he and Ron are unable to enter the magically invisible train platform, so they fly the Weasley car to Hogwarts. They land messily, and both boys are given detentions. Lockhart, who believes Harry flew the car to get attention, lectures Harry.\n" +
            "\n" +
            "Quidditch practices begin and Draco Malfoy is the new Slytherin seeker. On the field, he calls Hermione a \"mudblood,\" insulting her Muggle heritage. After taunting Hermione, Draco is the suspect when, on Halloween night, someone petrifies the school caretaker's cat and writes a threatening message. Before the cat is attacked, Harry twice hears an eerie voice. He hears it first during his detention and second during a party, moments before the cat is attacked. Everybody in the school is alarmed. By doing some research, Harry, Ron, and Hermione learn that fifty years ago a chamber at Hogwarts was opened and a student was killed.\n" +
            "\n" +
            "Playing for Gryffindor, Harry wins the Quidditch match against Slytherin. During the game, an enchanted ball hits Harry and causes him to lose the bones in his arm. Dobby, a house elf, has enchanted the ball in an effort to have Harry injured and sent home. That night, Harry sees the body of a first-year who has been petrified arrive at the hospital. Soon after, Lockhart begins a dueling club. During the first meeting, Harry terrifies his fellow students by speaking in Parseltongue to a snake. Harry's ability frightens the others because only the heir of Slytherin, who is responsible for opening the chamber, would have the ability to converse with snakes. Harry comes under further suspicion when he stumbles upon the petrified bodies of Justin Finch-Fletchley and Nearly- Headless Nick.\n" +
            "\n" +
            "Determined to catch the culprit, Ron, Harry and Hermione brew a potion called Polyjuice. The potion allows them to assume the bodies of Slytherins and question Malfoy on the Chamber of Secrets. They find out that Malfoy is not the heir of Slytherin. No more attacks occur for a while, and right before Valentine's Day, Harry finds a diary in the broken toilet. The diary belongs to a ghost named Moaning Myrtle who haunts the girls' restroom. Harry writes in the diary, which responds by writing back. Through this dialogue, Harry meets Tom Riddle, a boy who many years before had accused Hagrid of opening the Chamber of Secrets.\n" +
            "\n" +
            "Hermione and a Ravenclaw girl are mysteriously petrified. Harry and Ron venture out of the castle to question Hagrid. Before they reach Hagrid, the Minister of Magic, Cornelius Fudge, and Lucius Malfoy remove Dumbledore and Hagrid from Hogwarts. As Hagrid is led away, he instructs the boys that by following the spiders, they can find out about the Chamber monster. Several nights later, Harry and Ron sneak into the Forbidden Forest to follow the spiders. They discover the monster who killed the girl fifty years before was not a spider, that the girl's body was found in a bathroom, and that Hagrid is innocent. The boys are almost killed by a colony of giant spiders. As they escape, Harry and Ron decide that Moaning Myrtle must have been the girl killed by the monster.\n" +
            "\n" +
            "A few days later, Ron and Harry discover a piece of paper with a description of a basilisk on it in Hermione's frozen hand. They deduce the Chamber monster is a basilisk. Before the boys can act on their knowledge, the teachers announce that Ginny Weasley has been taken into the chamber. Ron, Harry, and Lockhart slide down a secret passage in Myrtle's bathroom to underground tunnels. When Lockhart accidentally curses himself, Ron helps him and Harry leaves them behind. Harry enters the Chamber of Secrets and encounters Ginny's still body and Tom Riddle. Tom turns out to be a younger version of Voldemort, who has been enchanting Ginny through his journal. Harry calls for help from Dumbledore. A phoenix and a magic hat arrive. Tom summons a basilisk, but the phoenix punctures its eyes. The hat produces a sword, which Harry uses to kill the giant snake. Harry sticks a basilisk fang through the diary, destroying Tom. Ginny wakes up.\n" +
            "\n" +
            "Harry explains his adventure to Dumbledore. Lucius Malfoy storms into the office with his house-elf, Dobby, and Harry frees Dobby from by tricking Lucius into giving Dobby a sock. All is well in the castle as the students leave for their summer vacations.";

    private String summary2 = "Harry is back at the Dursleys for the summer holidays, where he sees on Muggle television that a convict named Sirius Black has escaped, though with no mention of what facility he has broken out of. Harry involuntarily inflates Aunt Marge when she comes to visit after she insults Harry and his parents. This leads to his running away and being picked up by the Knight Bus. He travels to the Leaky Cauldron where he meets Cornelius Fudge, the Minister for Magic, who asks Harry to stay in Diagon Alley for the remaining three weeks before the start of the school year at Hogwarts. While there, he meets his best friends Ron Weasley and Hermione Granger.\n" +
            "\n" +
            "The night before Harry is expected to leave for Hogwarts, he learns from Arthur Weasley that Sirius Black is a convicted murderer in the wizarding world, and he warns Harry that it is believed Black will attempt to murder Harry next. On the way to Hogwarts a Dementor boards the train, causing Harry to faint. Following the incident, Harry is helped by the new Defence Against the Dark Arts teacher Remus Lupin. Harry, Ron, and Hermione learn that the Dementors will be patrolling the school in an attempt to catch Black.\n" +
            "\n" +
            "Later on, Lupin's Defence Against the Dark Arts sessions prove far better than those of Gilderoy Lockhart (Harry's uselessly vain ex-teacher). They have a fun lesson on Boggarts and then learn about more Dark Creatures. When Lupin supposedly falls ill, the much hated Potions Master Professor Snape temporarily takes over teaching Defence Against the Dark Arts and torments the class, much to their dismay.\n" +
            "\n" +
            "At Hogwarts, Harry has several problems with the Dementors, including an episode at a Quidditch match during which he faints and falls off his broomstick from high in the air. His broom is blown away and smashed by the Whomping Willow. Working with Harry outside class, Lupin teaches him the Patronus Charm to repel Dementors.\n" +
            "\n" +
            "On an unauthorised visit to the village of Hogsmeade (thanks to The Marauder's Map, given to him by George Weasley and Fred Weasley), Harry overhears some of his teachers talking with Fudge about Black. They reveal that Black was a friend of Harry's parents but he betrayed them and gave Voldemort access to their house. They also mention that Black killed twelve Muggles and his former friend Peter Pettigrew.\n" +
            "\n" +
            "Ron and Hermione's friendship later suffers when Ron believes that Hermione's cat, Crookshanks, ate his rat, Scabbers. At Christmas Harry receives a mysterious present, a late-model Firebolt broom. Fearing it might be cursed, Hermione reports the gift to Professor McGonagall, which leads to more bad feelings between her and Ron and Harry.\n" +
            "\n" +
            "Ron, Hermione, and Harry join the effort to save Hagrid's hippogriff, Buckbeak, from being executed for attacking Draco Malfoy, after Draco provoked him. Their efforts are unsuccessful, but Scabbers reappears shortly after they hear Buckbeak being executed.\n" +
            "\n" +
            "Ron chases Scabbers, only to be attacked by a big black dog, which Harry has seen several times before. The dog drags Ron through a tunnel under the Whomping Willow into the Shrieking Shack. Harry and Hermione follow, and there is a brief standoff when they find Ron with Sirius Black, who has transformed from the dog. Lupin enters, and they explain the situation to Harry and his friends: Lupin is a werewolf, which led to his friends James Potter, Sirius Black, and Peter Pettigrew becoming animagi. Lupin explains that Scabbers is Pettigrew in his animal form; he has been hiding from Black, whom he had framed for betraying Harry's parents and murdering the twelve Muggles.\n" +
            "\n" +
            "Snape arrives to apprehend Black but Harry, Ron, and Hermione knock him unconscious with the Expelliarmus charm. Lupin and Black transform Pettigrew back into human form and prepare to kill him, but they are stopped by Harry, as he feels his father would not have wanted it. He convinces them to send Pettigrew to Azkaban instead.\n" +
            "\n" +
            "As they move back toward Hogwarts, Lupin turns into a werewolf and becomes violent, having missed a dose of his Wolfsbane potion. Pettigrew escapes again, and Black prevents Lupin from attacking the others in werewolf form. Some Dementors approach Harry, Ron and Hermione.\n" +
            "\n" +
            "When they wake up in the hospital, Harry, Ron, and Hermione are told that Black has been sentenced to receive the Dementor's kiss, which removes the soul of the recipient. Dumbledore advises Hermione and Harry to use Hermione's time-turner, a device she has been using to double-up on classes; this permits them to go back in time and save Buckbeak, who carries Black away to safety.\n" +
            "\n" +
            "Sadly, Snape lets slip that Lupin is a werewolf, leading to his resignation. Harry visits Lupin before he leaves, and as they say goodbye, Lupin is certain that they will meet again. ";


    private ArrayList<String> listBook;
    /*
    Title of books
     */

    private static Bundle mBundleRecyclerViewState;



   private View v;


    private RecyclerView myRecycle;
    /*
    Recycle View
     */
    private final String KEY_RECYCLER_STATE = "recycler_state";

    private MyAdapterMyCollectionVersion2 madapter;
    /*
    Adapter for the recycle view
     */

    private DataBaseMyCollection db;

    private final String beginPath = "file:///android_res/raw/harry_potter_";

    private String[] pathToRead = {beginPath + "1", beginPath + "2", beginPath + "3"};

    private ArrayList<ArrayList<String>> listTextTxt = new ArrayList<ArrayList<String>>();

    public MyCollectionFragmentVersion2() {    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.collection_fragment_version_2, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        myRecycle = view.findViewById(R.id.recycle_view_collection_version_2);

        db = SingletonDataBaseMyColletion.getInstance().getDataBase();

        initialyzeAll();
        getDataFromDataBase();
        madapter = new MyAdapterMyCollectionVersion2(list_displayed, getContext(), listTextTxt);
        madapter.makeFav( (MyAdapterMyCollectionVersion2.AddForFavorite) getActivity());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRecycle.setLayoutManager(layoutManager);
        myRecycle.setAdapter(madapter);




    }



    public void updateData()
    {

        getDataFromDataBase();
        madapter = new MyAdapterMyCollectionVersion2(list_displayed, getContext(), listTextTxt);
        madapter.makeFav( (MyAdapterMyCollectionVersion2.AddForFavorite) getActivity());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRecycle.setLayoutManager(layoutManager);
        myRecycle.setAdapter(madapter);
    }

    private void initialyzeAll()
    /*
    Initialise the list of book to display
     */
    {

        summaryAll.add(summary);
        summaryAll.add(summary1);
        summaryAll.add(summary2);

        String author = "J. K. Rowling";
        TypedArray book_ressource = getResources().obtainTypedArray(R.array.harry_potter_covers);
        int m = summaryAll.size();
        try
        {
            for (int i=0; i<m ; i++)
            {
                BookMyCollection myBook = new BookMyCollection(titles[i],author,
                        book_ressource.getResourceId(i,0), summaryAll.get(i),pathToRead[i],null,null,null,null,0);
                if (db.getAllData() == null)
                {
                    db.insertData(myBook);
                }else
                {
                    db.updateBook(myBook);
                }

//                   list_displayed.add(myBook);
            }

        }catch (IndexOutOfBoundsException e)
        {
            Log.e("Index","out");
        }


    }

    public void getDataFromDataBase()

    {

            list_displayed = new ArrayList<>();
            db = SingletonDataBaseMyColletion.getInstance().getDataBase();
            Cursor data = db.getAllData();
            if (data.getCount() ==0)
            {
                Log.e("Error","No data found");
            }
            else
            {

//                data.moveToFirst();
                try
                {
                    while (data!=null && data.moveToNext())
                    {

                        String id = data.getString(0);
                        String title = data.getString(1);
                        String author = data.getString(2);
                        int link_image = data.getInt(3);
                        String summary =data.getString(4);
                        String pathtoread =data.getString(5);
                        String content =data.getString(6);
                        String listesong =data.getString(7);
                        String listesmell =data.getString(8);
                        String listindexes = data.getString(9);
                        int currentPage = data.getInt(10);
                        BookMyCollection new_book = new BookMyCollection(title,author,link_image,summary,
                                pathtoread, DataBaseMyCollection.makeListToArray(listesong), null,
                                DataBaseMyCollection.makeStringToArrayInteger(listindexes),
                                DataBaseMyCollection.makeListToArray(content),currentPage);
                        list_displayed.add(new_book);

                    }
                } catch (RuntimeException e)
                {
                    Log.e("DATABASE","RunTimeexception, mycollecionfragmentv2");
                }


            }

        Singleton.getInstance().setBooks(list_displayed);

        }






    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();


    }


    private void deleteBookToDataBase(BookMyCollection myBook)
    {
        db.delete(myBook);
    }

    public void deleteCollection(BookMyCollection myBook) {


        Log.e("TRY","TO DELETE");
        deleteBookToDataBase(myBook);
        try
        {
            getDataFromDataBase();
        } catch(RuntimeException e)
        {
            Log.e("MyCollectionFragV2","getDataFromDataBase failed");
        }

        madapter = new MyAdapterMyCollectionVersion2(list_displayed, getContext(), listTextTxt);
        madapter.makeFav( (MyAdapterMyCollectionVersion2.AddForFavorite) getActivity());
        myRecycle.setAdapter(madapter);

    }





    public void addBook(BookMyCollection newBook)
    {
        addBookToDataBase(newBook);
        getDataFromDataBase();

        madapter = new MyAdapterMyCollectionVersion2(list_displayed, getContext(), listTextTxt);
        madapter.makeFav( (MyAdapterMyCollectionVersion2.AddForFavorite) getActivity());
        myRecycle.setAdapter(madapter);
    }


    private void addBookToDataBase(BookMyCollection myBook)
    {
        Log.e("AddBookToDB", myBook.getListAllTextToDisplay().size() + "" );
        if (list_displayed.contains(myBook))
        {
            db.updateBook(myBook);
        }else
        {
            db.insertData(myBook);
        }

    }

}


