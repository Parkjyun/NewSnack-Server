package com.newsnack.www.newsnackserver.domain.article.repository;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.article.model.LocationCategory;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a from Article a order by a.id desc")
    Page<Article> findAllOrderByIdDesc(Pageable pageable);
    Page<Article> findAllBySectionCategoryOrderByIdDesc(SectionCategory sectionCategory, Pageable pageable);

    Page<Article> findAllByLocationCategoryOrderByIdDesc(LocationCategory locationCategory, Pageable pageable);

    @Query("SELECT a from Article a order by a.heartCount desc, a.id desc")
    Page<Article> findAllOrderByPopularAndIdDesc(Pageable pageable);

    @Query("SELECT a from Article a where a.sectionCategory = :sectionCategory order by a.heartCount desc, a.id desc")
    Page<Article> findAllBySectionCategoryOrderByHeartCountDescAndIdDesc(SectionCategory sectionCategory, Pageable pageable);

    @Query("SELECT a from Article a where a.locationCategory = :locationCategory order by a.heartCount desc, a.id desc")
    Page<Article> findAllByLocationCategoryOrderByHeartCountDescAndIdDesc(LocationCategory locationCategory, Pageable pageable);

}
