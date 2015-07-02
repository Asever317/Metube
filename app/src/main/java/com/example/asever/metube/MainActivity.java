package com.example.asever.metube;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.github.pedrovgs.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class MainActivity extends FragmentActivity  {

    private static final int DELAY_MILLIS = 10;
   DraggablePanel draggablePanel;

    private YouTubePlayer youtubePlayer;
    private YouTubePlayerSupportFragment youtubeFragment;
    //////////////////
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL =
            "http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past"
                    + "-wolverine-poster.jpg";
    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future ";
    private static final String VIDEO_POSTER_THUMBNAIL =
            "http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/"
                    + "xmenDOFP.wobbly.1.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draggablePanel =(DraggablePanel)findViewById(R.id.draggable_panel);
        initializeYoutubeFragment();
       initializeDraggablePanel();

    }

    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;
                    youtubePlayer.loadVideo(Config.YOUTUBE_VIDEO_CODE);
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult error) {
            }
        });
    }


    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();

//        moviePosterFragment.setPoster(VIDEO_POSTER_THUMBNAIL);
//        moviePosterFragment.setPosterTitle(VIDEO_POSTER_TITLE);

        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();
    }


}
