package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Hàm này để tạo ra câu join trong sql
     *
     * @param request - các giá trị nhận từ client
     * @param sql     - câu sql gốc
     */
    public void createJoinQuery(BuildingSearchRequest request, StringBuilder sql) {
        // MinArea và MaxArea
        Long minArea = request.getAreaFrom();
        Long maxArea = request.getAreaTo();
        if (maxArea != null || minArea != null) {
            sql.append(" INNER JOIN rentarea r ON b.id = r.buildingid ");
        }

        // StaffID
        Long staffID = request.getStaffId();
        if (staffID != null) {
            sql.append(" INNER JOIN assignmentbuilding asm on b.id = asm.buildingid ");
        }
    }

    /**
     * Hàm này để tạo ra câu where với điều kiện = hoặc like
     *
     * @param request - các giá trị nhận từ client
     * @param where     - câu where gốc
     */
    private void createWhereQueryNormal(BuildingSearchRequest request, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(request);
                    if (value != null && value != "") {
                        if (item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b.").append(fieldName).append(" = ").append(value).append(" ");
                        } else if (item.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND b.").append(fieldName).append(" = ").append(value).append(" ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b.").append(fieldName).append(" like '%").append(value).append("%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm này để tạo ra câu where với các điều kiện đặc biệt như <= hoặc các trường phải nối bảng,
     *
     * @param request - các giá trị nhận từ client
     * @param where     - câu where gốc
     */
    private void createWhereQuerySpecial(BuildingSearchRequest request, StringBuilder where) {
        Long minArea = request.getAreaFrom();
        Long maxArea = request.getAreaTo();
        if (maxArea != null) {
            where.append(" AND r.value <= ").append(maxArea);
        }
        if (minArea != null) {
            where.append(" AND r.value >= ").append(minArea);
        }

        Long minPrice = request.getRentPriceFrom();
        Long maxPrice = request.getRentPriceTo();
        if (maxPrice != null) {
            where.append(" AND b.rentprice <= ").append(maxPrice);
        }
        if (minPrice != null) {
            where.append(" AND b.rentprice >= ").append(minPrice);
        }

        Long staffID = request.getStaffId();
        if (staffID != null) {
            where.append(" AND asm.staffid = ").append(staffID);
        }

        List<String> typeCode = request.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND ");
            String sqlJoin = typeCode.stream().map(item -> " b.type like '%" + item + "%'").collect(Collectors.joining(" or "));
            where.append(sqlJoin).append(" ");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest request, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        createJoinQuery(request, sql);
        createWhereQueryNormal(request, where);
        createWhereQuerySpecial(request, where);
        sql.append(where);
        sql.append(" GROUP BY b.id ").append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter() {
        return "SELECT b.* FROM building b ";
    }
}
