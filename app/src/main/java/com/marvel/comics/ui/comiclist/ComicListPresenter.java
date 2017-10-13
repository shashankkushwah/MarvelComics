package com.marvel.comics.ui.comiclist;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicListPresenter implements ComicListContract.Presenter {
    private static final int LIMIT = 100;

    private ComicListContract.View comicListView;
    private ApiHelper apiHelper;

    public ComicListPresenter(ComicListContract.View comicListView, ApiHelper apiHelper) {
        this.comicListView = comicListView;
        this.apiHelper = apiHelper;
    }

    @Override
    public void loadComicList() {
        comicListView.setProgressIndicator(true);
        apiHelper.getComics(0, LIMIT, new ApiHelper.Callback<List<Comic>>() {
            @Override
            public void onSuccess(List<Comic> data) {
                comicListView.setProgressIndicator(false);
                Collections.sort(data);
                comicListView.showComicList(data);
            }

            @Override
            public void onFailed(String message) {
                comicListView.setProgressIndicator(false);
                comicListView.showError(message);
            }
        });
    }

    @Override
    public void onComicItemClick(Comic comic) {
        comicListView.showComicDetail(comic);
    }

    @Override
    public void filterComicForBudget(String budgetStr, List<Comic> comicList) {
        try {
            float budget = Float.parseFloat(budgetStr);
            Collections.sort(comicList);
            List<Comic> inBudgetComicList = new ArrayList<>();
            float total = 0f;
            int totalPageCount = 0;
            for (int i = 0, size = comicList.size(); i < size; i++) {
                Comic item = comicList.get(i);
                if (total + item.getPrice() <= budget) {
                    inBudgetComicList.add(item);
                    total += item.getPrice();
                    totalPageCount += item.getPageCount();
                } else {
                    break;
                }
            }
            Collections.sort(inBudgetComicList, Comic.DESC_COMPARATOR);
            comicListView.showBudgetComicList(inBudgetComicList, totalPageCount);
        } catch (NumberFormatException e) {
            comicListView.showError("Problem with budget!");
        }
    }


    @Override
    public void onDestroy() {
        comicListView = null;
    }
}
