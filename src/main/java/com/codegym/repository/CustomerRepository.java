package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Repository
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveWithStoredProcedure(Customer customer) {
//        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("insertCustomer")
//                .registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN)
//                .setParameter("firstName", customer.getFirstName());
//        storedProcedure.executeUpdate();
//        StoredProcedureQuery storedProcedure2 = entityManager.createStoredProcedureQuery("insertCustomer")
//                .registerStoredProcedureParameter("lastName", String.class, ParameterMode.IN)
//                .setParameter("lastName", customer.getLastName());
//        storedProcedure2.executeUpdate();
        String sql = "CALL Insert_Customer(:firstName, :lastName)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        return query.executeUpdate() != 0;

    }
}