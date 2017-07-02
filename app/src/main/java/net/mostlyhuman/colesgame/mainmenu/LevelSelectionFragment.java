package net.mostlyhuman.colesgame.mainmenu;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.data.DatabaseContract;
import net.mostlyhuman.colesgame.data.Level;
import net.mostlyhuman.colesgame.game.GameActivity;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelSelectionFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private final String TAG = "LevelSelection";

    private int itemWidth;

    Resources resources;
    private LevelListAdapter levelListAdapter;

    public LevelSelectionFragment() {
        // Required empty public constructor
    }

    LevelListAdapter.LevelItemListener itemListener = new LevelListAdapter.LevelItemListener() {
        @Override
        public void onLevelClick(String level, int levelID, boolean available) {
            if (available) {
                beginLevel(level, levelID);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resources = getResources();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point resolution = new Point();
        display.getSize(resolution);

        itemWidth = resolution.x;

        levelListAdapter = new LevelListAdapter(null, itemListener, itemWidth);

        Bundle args = new Bundle();
        args.putString(Constants.CHAPTER_TITLE, getArguments().getString(Constants.CHAPTER_TITLE));

        getActivity().getLoaderManager().initLoader(1, args, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getLoaderManager().destroyLoader(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_level_selection, container, false);

        final RecyclerView levelList = (RecyclerView) v.findViewById(R.id.levels_recyclerview);
        levelList.setAdapter(levelListAdapter);
        levelList.setLayoutManager(new GridLayoutManager(getActivity(), 3,
                GridLayoutManager.VERTICAL, false));

        return v;
    }

    public static Fragment newInstance() {
        return new LevelSelectionFragment();
    }

    private void beginLevel(String levelTitle, int levelID) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        intent.putExtra(Constants.LEVEL_TITLE, levelTitle);
        intent.putExtra(Constants.LEVEL_ID, levelID);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = DatabaseContract.DEFAULT_SORT;

        String[] selectionArgs = new String[10];

        String selection = "";
        for (int i = 0; i < selectionArgs.length; i++) {
            if (i != selectionArgs.length - 1) {
                selection = selection + DatabaseContract.LevelColumns.LEVEL_TITLE + " = ? OR ";
            } else {
                selection = selection + DatabaseContract.LevelColumns.LEVEL_TITLE + " = ?";
            }
        }

        String chapterTitle = args.getString(Constants.CHAPTER_TITLE);
        selectionArgs[0] = String.format(getActivity().getString(R.string.level_title), 1);

        String[] chapterTitles = resources.getStringArray(R.array.chapter_titles);
        for (int i = 0; i < chapterTitles.length; i++) {
            if (chapterTitles[i].equals(chapterTitle)) {
                int maxLevel = (i + 1) * 10;
                int lowestLevel = maxLevel - 9;
                int currentIndex = 0;
                for (int j = lowestLevel; j <= maxLevel; j++) {
                    selectionArgs[currentIndex] =
                            String.format(getActivity().getString(R.string.level_title), j);
                    currentIndex++;
                }
            }
        }

        Uri uri = DatabaseContract.CONTENT_URI;

        return new CursorLoader(getActivity(), uri,
                new String[] {
                        DatabaseContract.LevelColumns._ID,
                        DatabaseContract.LevelColumns.LEVEL_TITLE,
                        DatabaseContract.LevelColumns.COMPLETED,
                        DatabaseContract.LevelColumns.IS_AVAILABLE },
                selection, selectionArgs, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "" + data.getCount());
        levelListAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        levelListAdapter.swapCursor(null);
    }

    private static class LevelListAdapter extends RecyclerView.Adapter<LevelListAdapter.ViewHolder> {

        // TODO: 6/8/2017 make current levelView tile white, grey out levels that haven't been completed.
        // TODO: 6/8/2017 put a checkmark or something on the tiles for the levels that have been completed.

        private int itemWidth;

        private Cursor mCursor;
        private LevelItemListener levelItemListener;

        public LevelListAdapter(Cursor cursor, LevelItemListener itemListener, int screenWidth) {
            this.itemWidth = screenWidth / 4;

            this.mCursor = cursor;
            this.levelItemListener = itemListener;
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView levelView;
            TextView tv2;
            TextView tv3;
            LinearLayout linearLayout;
            FrameLayout frameLayout;

            private LevelItemListener levelItemListener;

            ViewHolder(View itemView, LevelItemListener listener) {
                super(itemView);

                frameLayout = (FrameLayout) itemView.findViewById(R.id.level_item_frame);
                levelView = (TextView) itemView.findViewById(R.id.level_number);
                tv2 = (TextView) itemView.findViewById(R.id.textView2);
                tv3 = (TextView) itemView.findViewById(R.id.textView3);

                linearLayout = (LinearLayout) itemView.findViewById(R.id.level_view_ll);

                levelItemListener = listener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Level level = getItem(position);
                levelItemListener.onLevelClick(level.getTitle(),
                        level.getID(), level.isAvailable());
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View levelView = inflater.inflate(R.layout.recyclerview_level_list_item, parent, false);

            return new ViewHolder(levelView, levelItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Level level = getItem(position);

            holder.frameLayout.setPadding(itemWidth / 8, itemWidth / 8, itemWidth / 8, itemWidth / 8);
            holder.linearLayout.setMinimumWidth(itemWidth);
            holder.linearLayout.setMinimumHeight(itemWidth);

            if (level.isAvailable()) {
                holder.levelView.setTextColor(Color.BLACK);
            } else {
                holder.levelView.setTextColor(Color.GRAY);
            }
            holder.levelView.setText(String.valueOf(level.getTitle()));
            holder.tv2.setText(String.valueOf(level.isCompleted()));
            holder.tv3.setText(String.valueOf(level.isAvailable()));
        }

        public Level getItem(int position) {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Invalid item position requested");
            }

            return new Level(mCursor);
        }

        public void swapCursor(Cursor cursor) {
            if (mCursor != null) {
                mCursor.close();
            }
            mCursor = cursor;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return (mCursor != null) ? mCursor.getCount() : 0;
        }

        interface LevelItemListener {
            void onLevelClick(String level, int levelID, boolean available);
        }
    }


}
