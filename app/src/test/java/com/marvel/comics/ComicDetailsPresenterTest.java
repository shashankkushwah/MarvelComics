package com.marvel.comics;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.ui.comicdetail.ComicDetailContract;
import com.marvel.comics.ui.comicdetail.ComicDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComicDetailsPresenterTest {
    @Mock
    private ComicDetailContract.View view;

    @Mock
    private Comic comic;

    private ComicDetailPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ComicDetailPresenter(view);
    }

    @Test
    public void testPresenterSuccess() throws Exception {
        presenter.loadComicDetail(comic);
        Mockito.verify(view).showComicDetail(comic);
    }
}
