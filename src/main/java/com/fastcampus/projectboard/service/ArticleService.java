package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {

        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);


        }
        return switch (searchType){
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT-> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#"+searchKeyword, pageable).map(ArticleDto::from);
        };

    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {

        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(()->new EntityNotFoundException("게시글이 없습니다"));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {
        try {

            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("업데이트 실패.게시글을 찾을 수 없습니다.-dto:{}",dto);
            //log.warn("업데이트 실패.게시글을 찾을 수 없습니다.-dto:{}"+dto); 보다 나음
            //string을 concatenation(집중)해서 쓰는것보다는 interpolation(보간:사이를 채우다)해주세요.

        }
    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

}