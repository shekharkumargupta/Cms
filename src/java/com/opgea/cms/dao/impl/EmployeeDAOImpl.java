/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Employee create(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(employee);
        tx.commit();
        session.close();
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(employee);
        tx.commit();
        session.close();
        return employee;
    }

    @Override
    public Employee remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Employee find(Long id) {
        Session session = sessionFactory.openSession();
        //Query query = session.createQuery("from Employee e WHERE e.id="+id);
        Query query = session.getNamedQuery("Employee.findById")
                .setParameter("id", id);
        Employee employee = (Employee)query.uniqueResult();
        session.close();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAll");
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        //Query query = session.createQuery("from Employee e WHERE e.branch.company.id="+companyId);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }
    
    @Override
    public List<Employee> findAllByBranchId(Long branchId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAllByBranchId");
        query.setParameter("branchId", branchId);
        //Query query = session.createQuery("from Employee e WHERE e.branch.id="+branchId);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> findAllByTeamId(Long teamId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAllByTeamId");
        query.setParameter("teamId", teamId);
        //Query query = session.createQuery("from Employee e WHERE e.team.id="+teamId);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public Employee getBranchHead(Long branchId) {
        Session session = sessionFactory.openSession();
        String queryString = "from Employee e WHERE e.branch.id="+branchId+" AND e.employeeType="+EmployeeType.BranchHead.ordinal();
        Query query = session.createQuery(queryString);
        Employee employee = (Employee)query.uniqueResult();
        System.out.println("BranchHead : "+employee);
        session.close();
        return employee;
    }
 
}
