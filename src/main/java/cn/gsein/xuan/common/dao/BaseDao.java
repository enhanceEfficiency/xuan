package cn.gsein.xuan.common.dao;

import cn.gsein.xuan.common.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    List<T> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    Page<T> findAll(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deleted = false")
    Optional<T> findById(Long id);

    @Override
    default boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    List<T> findAllById(Iterable<Long> longs);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.deleted = false")
    long count();

    @Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
    @Modifying
    @Transactional
    void softDeleteById(Long id);

    default void softDelete(T entity) {
        deleteById(entity.getId());
    }

    default void softDeleteAll(Iterable<? extends T> entities) {
        entities.forEach(this::delete);
    }

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted = true")
    void softDeleteAll();

    /**
     * 将查询条件封装为specification
     *
     * @param t 查询条件
     * @return 封装后的查询条件
     */
    default Specification<T> getSpecification(T t) {
        return ((root, criteriaQuery, criteriaBuilder) -> null);
    }
}
