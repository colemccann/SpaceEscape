package net.mostlyhuman.colesgame.mainmenu;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.data.Chapter;
import net.mostlyhuman.colesgame.data.DatabaseContract;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class ChapterSelectionFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private final String TAG = "ChapterSelection";

    private ChapterListAdapter chapterListAdapter;
    private Resources resources;
    private Bundle args = new Bundle();
    private String[] chapterNumbers;
    private String[] chapterTitles;

    ChapterListAdapter.ChapterItemListener itemListener =
            new ChapterListAdapter.ChapterItemListener() {
        @Override
        public void onChapterClick(String chapter, boolean available) {
            if (available) {
                Log.d(TAG, chapter);
                openLevelSelection(chapter);
            }
        }
    };

    public ChapterSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chapterListAdapter = new ChapterListAdapter(getActivity(),
                new ArrayList<Chapter>(), itemListener);

        resources = getResources();
        try {
            chapterNumbers = resources.getStringArray(R.array.chapter_numbers);
            chapterTitles = resources.getStringArray(R.array.chapter_titles);
        } catch (Resources.NotFoundException e) {
            Log.d(TAG, "Something went wrong finding array");
        }

        getActivity().getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getActivity().getLoaderManager().destroyLoader(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chapter_selection, container, false);

        final RecyclerView chapterList = (RecyclerView) v.findViewById(R.id.chapters_recyclerview);
        chapterList.setAdapter(chapterListAdapter);
        chapterList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    public static Fragment newInstance() {
        return new ChapterSelectionFragment();
    }

    public void openLevelSelection(String chapterTitle) {
        args.putString(Constants.CHAPTER_TITLE, chapterTitle);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = LevelSelectionFragment.newInstance();
        fragment.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = DatabaseContract.DEFAULT_SORT;

        Uri uri = DatabaseContract.CONTENT_URI;
        return new CursorLoader(getActivity(), uri,
                new String[] {DatabaseContract.LevelColumns.COMPLETED},
                null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        chapterListAdapter.createChapters(
                chapterNumbers,
                chapterTitles,
                data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}

    private static class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {

        private String TAG = "ChapterListAdapter";

        private ArrayList<Chapter> mChapters;
        private ChapterItemListener chapterItemListener;
        private Context context;

        private static final int LEVELS_PER_CHAPTER = 10;
        private static final int NUMBER_OF_CHAPTERS = 3;

        ChapterListAdapter(Context context, ArrayList<Chapter> chapters,
                           ChapterItemListener listener) {
            this.context = context;

            setChapters(chapters);

            chapterItemListener = listener;
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView number;
            TextView title;
            TextView levelsCompleted;
            ImageView chapterImageView;

            private ChapterItemListener chapterItemListener;

            ViewHolder(View itemView, ChapterItemListener listener) {
                super(itemView);

                number = (TextView) itemView.findViewById(R.id.chapter_number);
                title = (TextView) itemView.findViewById(R.id.chapter_title);
                levelsCompleted = (TextView) itemView.findViewById(R.id.levels_completed);
                chapterImageView = (ImageView) itemView.findViewById(R.id.chapter_imageview);

                chapterItemListener = listener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Chapter chapter = getItem(position);
                chapterItemListener.onChapterClick(chapter.getChapterTitle(),
                        chapter.isAvailable());
            }
        }

        @Override
        public ChapterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View chapterView = inflater.inflate(R.layout.recyclerview_chapter_list_item, parent, false);

            return new ViewHolder(chapterView, chapterItemListener);
        }

        @Override
        public void onBindViewHolder(ChapterListAdapter.ViewHolder holder, int position) {
            Chapter chapter = mChapters.get(position);

            if (chapter.isAvailable()) {
                holder.chapterImageView.setImageResource(R.mipmap.icon_level_unlocked);
                if (chapter.getLevelsCompleted() == 10) {
                    holder.chapterImageView.setImageResource(R.mipmap.icon_level_checkmark);
                }
            } else {
                holder.number.setTextColor(Color.GRAY);
                holder.title.setTextColor(Color.GRAY);
                holder.levelsCompleted.setTextColor(Color.GRAY);
                holder.chapterImageView.setImageResource(R.mipmap.icon_level_locked);
            }

            holder.number.setText(chapter.getChapterNumber());
            holder.title.setText(chapter.getChapterTitle());
            holder.levelsCompleted.setText(String.format(context.getString(R.string.out_of_ten),
                    chapter.getLevelsCompleted()));

        }

        @Override
        public int getItemCount() {
            return mChapters.size();
        }

        private Chapter getItem(int position) {
            return mChapters.get(position);
        }

        void replaceData(ArrayList<Chapter> chapters) {
            setChapters(chapters);
            notifyDataSetChanged();
        }

        private void setChapters(ArrayList<Chapter> chapters) {
            this.mChapters = chapters;
        }

        private void createChapters(String[] numbers, String[] titles, Cursor cursor) {
            ArrayList<Chapter> chapters = new ArrayList<>();
            int[] levelsCompletePerChapter = new int[NUMBER_OF_CHAPTERS];

            int rows = cursor.getCount();
            int[] completeStatus = new int[rows];
            for (int i = 0; i < rows; i++) {
                cursor.moveToPosition(i);
                completeStatus[i] = DatabaseContract.getColumnInt(cursor,
                        DatabaseContract.LevelColumns.COMPLETED);
            }

            int totalLevelsCompleted = 0;
            for (int complete : completeStatus) {
                if (complete == 1) {
                    totalLevelsCompleted++;
                }
            }

            int chaptersCompleted = totalLevelsCompleted / LEVELS_PER_CHAPTER;
            int remainder = totalLevelsCompleted % LEVELS_PER_CHAPTER;

            boolean[] chapterAvailability = new boolean[NUMBER_OF_CHAPTERS];

            if (chaptersCompleted > 0) {
                for (int i = 0; i < chaptersCompleted; i++) {
                    levelsCompletePerChapter[i] = 10;
                    chapterAvailability[i] = true;
                }
                if (totalLevelsCompleted >= chaptersCompleted * 10) {
                    if (chaptersCompleted != 3) {
                        chapterAvailability[chaptersCompleted] = true;
                        levelsCompletePerChapter[chaptersCompleted] = remainder;
                    }
                }
            } else {
                chapterAvailability[0] = true;
                levelsCompletePerChapter[0] = remainder;
                for (int i = 1; i < NUMBER_OF_CHAPTERS; i++) {
                    chapterAvailability[i] = false;
                }
            }
            for (boolean b : chapterAvailability) {
                Log.d(TAG, String.valueOf(b));
            }

            if (chaptersCompleted + 1 < NUMBER_OF_CHAPTERS) {
                for (int i = chaptersCompleted + 1; i == NUMBER_OF_CHAPTERS; i++) {
                    levelsCompletePerChapter[i] = 0;
                }
            }

            for (int i = 0; i < NUMBER_OF_CHAPTERS; i++) {
                chapters.add(new Chapter(numbers[i],
                        titles[i],
                        levelsCompletePerChapter[i],
                        chapterAvailability[i]));
            }

            replaceData(chapters);
        }

        interface ChapterItemListener {
            void onChapterClick(String chapterTitle, boolean available);
        }
    }

}
