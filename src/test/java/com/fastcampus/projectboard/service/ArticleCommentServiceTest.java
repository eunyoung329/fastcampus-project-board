package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비지니스 로직-댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

@DisplayName("게시글 ID로 조회하면,해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        //Given
        Long articleId=1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title","content","#java")));
        //when
       List<ArticleCommentDto>articleComments= sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);


    }

    @DisplayName("댓글정보를 입력하면,댓글을 저장한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        //Given
        Long articleId=1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title","content","#java")));
        //when
        List<ArticleCommentDto>articleComments= sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);


    }
}