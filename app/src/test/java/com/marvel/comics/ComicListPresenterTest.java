package com.marvel.comics;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;
import com.marvel.comics.ui.comiclist.ComicListContract;
import com.marvel.comics.ui.comiclist.ComicListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ComicListPresenterTest {
    @Mock
    private ComicListContract.View comicListView;

    @Mock
    private ApiHelper comicDataModel;

    @Mock
    private List<Comic> comics;

    @Mock
    private Comic comic;

    @Captor
    private ArgumentCaptor<ApiHelper.Callback<List<Comic>>> dataCallbackCaptor;


    private ComicListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ComicListPresenter(comicListView, comicDataModel);
    }

    @Test
    public void testPresenterSuccess() throws Exception {
        presenter.loadComicList();
        Mockito.verify(comicListView).setProgressIndicator(true);

        Mockito.verify(comicDataModel).getComics(Mockito.anyInt(), Mockito.anyInt(),  dataCallbackCaptor.capture());
        dataCallbackCaptor.getValue().onSuccess(comics);

        Mockito.verify(comicListView).setProgressIndicator(false);
        Mockito.verify(comicListView).showComicList(comics);
    }

    @Test
    public void testPresenterError() throws Exception {
        presenter.loadComicList();
        Mockito.verify(comicListView).setProgressIndicator(true);

        Mockito.verify(comicDataModel).getComics(Mockito.anyInt(), Mockito.anyInt(),  dataCallbackCaptor.capture());
        dataCallbackCaptor.getValue().onFailed(Mockito.anyString());

        Mockito.verify(comicListView).setProgressIndicator(false);
        Mockito.verify(comicListView).showError(Mockito.anyString());
    }

    @Test
    public void testPresenterItemClick() throws Exception {
        presenter.onComicItemClick(comic);
        Mockito.verify(comicListView).showComicDetail(comic);
    }

}
