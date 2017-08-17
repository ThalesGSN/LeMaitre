/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface EmployeeDAO {
    public Long insert(Employee employee) throws PersistenceException;
    public boolean update(Employee employee) throws PersistenceException;
    public boolean remove(Long employeeID) throws PersistenceException;
    public Employee getEmployeeByID(Long employeeID) throws PersistenceException;
}
