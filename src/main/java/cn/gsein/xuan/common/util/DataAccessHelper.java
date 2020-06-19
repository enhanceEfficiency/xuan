package cn.gsein.xuan.common.util;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.function.Consumer;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
public class DataAccessHelper {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void doInTransaction(Consumer<EntityManager> consumer) {
        consumer.accept(entityManager);
    }
}
