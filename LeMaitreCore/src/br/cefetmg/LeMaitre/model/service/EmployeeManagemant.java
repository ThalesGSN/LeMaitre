/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface EmployeeManagemant {
    public Long employeeInsert(Employee employee) throws BusinessException, PersistenceException;
    public boolean employeeUpdate(Employee employee) throws BusinessException, PersistenceException;
    public boolean employeeRemove(Integer employeeID) throws PersistenceException;
    public Employee getEmployeeByID(Integer employeeID) throws PersistenceException;
}
