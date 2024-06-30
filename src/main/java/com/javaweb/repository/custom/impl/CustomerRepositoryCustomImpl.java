package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
@Primary
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void createJoinQuery(CustomerSearchRequest request, StringBuilder sql) {
        // StaffID
        Long staffID = request.getStaffId();
        if (staffID != null) {
            sql.append(" INNER JOIN assignmentcustomer asm on c.id = asm.customerid ");
        }
    }

    private void createWhereQueryNormal(CustomerSearchRequest request, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId")) {
                    Object value = item.get(request);
                    if (value != null && value != "") {
                        if (item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND c.").append(fieldName).append(" = ").append(value).append(" ");
                        } else if (item.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND c.").append(fieldName).append(" = ").append(value).append(" ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND c.").append(fieldName).append(" like '%").append(value).append("%' ");
                        }
                    }
                }
            }
            where.append(" AND c.is_active = 1 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createWhereQuerySpecial(CustomerSearchRequest request, StringBuilder where) {
        Long staffID = request.getStaffId();
        if (staffID != null) {
            where.append(" AND asm.staffid = ").append(staffID);
        }
    }

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest request, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        createJoinQuery(request, sql);
        createWhereQueryNormal(request, where);
        createWhereQuerySpecial(request, where);
        sql.append(where);
        sql.append(" GROUP BY c.id ").append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter() {
        return "SELECT c.* FROM customer c ";
    }
}
