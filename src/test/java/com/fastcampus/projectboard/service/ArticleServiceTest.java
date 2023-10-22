package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;

import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;
/*
 검색
 각 게시글 페이지로 이동
 페이지네이션
 홈버튼->게시판페이지로 리다이렉션
 정렬기능
 */
@DisplayName("게시글을 검색하면,게시글 리스트를 반환한다.")
@Test
void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
//Given

    //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE,"search keword");//제목,본문,Id,닉네임,해시태그

    //
    assertThat(articles).isNotNull();
}






    @DisplayName("게시글을 조회하면,게시글을 반환한다.")
@Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
//Given

        //when
        ArticleDto articles = sut.searchArticle(1L);

        //
        assertThat(articles).isNotNull();
}

@DisplayName("게시글 정보를 입력하면,게시글을 생성한다")
@Test
void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
    //Given
   // ArticleDto dto=ArticleDto.of(LocalDateTime.now(),"eunyoung329","title","content","java");
   //willDoNothing().given(articleRepository.save(any(Article.class)));
given(articleRepository.save(any(Article.class))).willReturn(null);


    //When
    sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "eunyoung329","title","content","#"));

    //Then
    then(articleRepository).should().save(any(Article.class));


}

    @DisplayName("게시글의 ID와 수정 정보를 입력하면,게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        //Given

        given(articleRepository.save(any(Article.class))).willReturn(null);


        //When
        sut.updateArticle(1L, ArticleUpdateDto.of("title","content","#"));

        //Then
        then(articleRepository).should().save(any(Article.class));


    }

    @DisplayName("게시글의 ID를 입력하면,게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        //Given

        willDoNothing().given(articleRepository).delete(any(Article.class));


        //When
        sut.deleteArticle(1L);

        //Then
        then(articleRepository).should().delete(any(Article.class));


    }



}