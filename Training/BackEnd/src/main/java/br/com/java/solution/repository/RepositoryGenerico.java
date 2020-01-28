package br.com.java.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface RepositoryGenerico<T> extends JpaRepository<T, Long>, PagingAndSortingRepository<T, Long>, QuerydslPredicateExecutor<T> {
}
