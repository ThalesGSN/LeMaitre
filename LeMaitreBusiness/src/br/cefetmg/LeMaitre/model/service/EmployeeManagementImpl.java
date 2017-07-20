/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.EmployeeDAO;
import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public class EmployeeManagementImpl implements EmployeeManagemant {
    EmployeeDAO DAO;

    public EmployeeManagementImpl(EmployeeDAO DAO) {
        this.DAO = DAO;
    }
    
    @Override
    public Integer employeeInsert(Employee employee) throws BusinessException, PersistenceException {
        if(employee == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null employee cannot be inserted.");
        
        if(employee.getNomName().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomName cannot be empty.");
        
        if(String.copyValueOf(Employee.IDT_PROFILE_POSSIBLE_VALUES)
                .indexOf(employee.getidtProfile()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtProfile invalid value.");
        }
        
        if(employee.getNomUsername() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomUsername cannot be null.");
        
        if(employee.getNomUsername().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomUsername cannot be empty.");
        
        if(employee.getTxtPassword() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "txtPassword cannot be null.");
        
        if(employee.getTxtPassword().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "txtPassword cannot be empty.");
        
        return DAO.insert(employee);
    }

    @Override
    public boolean employeeUpdate(Employee employee) throws BusinessException, PersistenceException {
        if(employee == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null employee cannot be inserted.");
        
        if(employee.getCodID() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codId cannot be null.");

        if(employee.getNomName() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomName cannot be null.");
        
        if(employee.getNomName().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomName cannot be empty.");
        
        if(String.copyValueOf(Employee.IDT_PROFILE_POSSIBLE_VALUES)
                .indexOf(employee.getidtProfile()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtProfile invalid value.");
        }
        
        if(employee.getNomUsername() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomUsername cannot be null.");
        
        if(employee.getNomUsername().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomUsername cannot be empty.");
        
        if(employee.getTxtPassword() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "txtPassword cannot be null.");
        
        if(employee.getTxtPassword().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "txtPassword cannot be empty.");
        
        return DAO.update(employee);
    }

    @Override
    public boolean employeeRemove(Integer employeeID) throws PersistenceException {
        if(employeeID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "employeeID cannot be null.");
        
        return DAO.remove(employeeID);
    }

    @Override
    public Employee getEmployeeByID(Integer employeeID) throws PersistenceException {
        if(employeeID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "employeeID cannot be null.");
        
        return DAO.getEmployeeByID(employeeID);
    }
}
