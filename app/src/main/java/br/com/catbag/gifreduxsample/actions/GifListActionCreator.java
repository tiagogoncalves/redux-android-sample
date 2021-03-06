package br.com.catbag.gifreduxsample.actions;

import com.umaplay.fluxxan.Action;
import com.umaplay.fluxxan.impl.BaseActionCreator;

import java.util.HashMap;
import java.util.Map;

import br.com.catbag.gifreduxsample.MyApp;
import br.com.catbag.gifreduxsample.asyncs.data.DataManager;

/**
 * Created by niltonvasques on 10/12/16.
 */

public final class GifListActionCreator extends BaseActionCreator {

    public static final String GIF_LIST_UPDATED = "GIF_LIST_UPDATED";

    private static GifListActionCreator sInstance;

    private DataManager mDataManager = new DataManager();

    private GifListActionCreator() {
        MyApp.getFluxxan().inject(this);
    }

    public static GifListActionCreator getInstance() {
        if (sInstance == null) {
            sInstance = new GifListActionCreator();
        }
        return sInstance;
    }

    public void loadGifs() {
        if (MyApp.getFluxxan().getState().getHasMoreGifs()) {
            mDataManager.fetchGifs((gifs, hasMore) -> {
                Map<String, Object> params = new HashMap<>();
                params.put(PayloadParams.PARAM_GIFS, gifs);
                params.put(PayloadParams.PARAM_HAS_MORE, hasMore);
                dispatch(new Action(GIF_LIST_UPDATED, params));
            });
        }
    }

    public void setDataManager(DataManager dataManager) {
        mDataManager = dataManager;
    }

}
