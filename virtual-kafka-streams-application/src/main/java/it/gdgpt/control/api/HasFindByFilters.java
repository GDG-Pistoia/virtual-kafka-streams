package it.gdgpt.control.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.List;

public interface HasFindByFilters<T, K> extends HasPersistence<T>  {

    List<T> findByFilterts(K filters, Integer first, Integer last);

    default List<T> doFindByFilters(K filters, Integer firstResult, Integer maxResults) {

        EntityManager em = getEm();
        Class<T> entityType = getEntityType();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityType);
        Root<T> root = query.from(entityType);

        MultivaluedMap<String, String> queryParameters = getQueryParameters(filters);
        Predicate[] predicates = getPredicates(queryParameters, query, criteriaBuilder, root);

        addJoins(root);
        query.where(predicates);
        query.select(root);


        TypedQuery<T> typedQuery = em.createQuery(query);
        if (firstResult != null && maxResults != null) {
            typedQuery.setFirstResult(firstResult).setMaxResults(maxResults);
        }

        return typedQuery.getResultList();

    }



    default Predicate[] getPredicates(MultivaluedMap<String, String> filters, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> predicates = buildPredicates(filters, query, criteriaBuilder, root);
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    List<Predicate> buildPredicates(MultivaluedMap<String, String> filters, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Root<T> root);

    void addJoins(Root<T> root);

    MultivaluedMap<String, String> getQueryParameters(K filtersSource);

}
