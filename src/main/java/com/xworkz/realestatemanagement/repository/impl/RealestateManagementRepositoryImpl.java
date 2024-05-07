package com.xworkz.realestatemanagement.repository.impl;


import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.repository.RealestateManagementRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Component
@NoArgsConstructor
public class RealestateManagementRepositoryImpl implements RealestateManagementRepository {

    @Autowired
    EntityManagerFactory emf;
    @Override
    public boolean saveRegisterInfo(RegisterDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            System.out.println("data saved ");
            return  true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void savePropertyDTO(PropertyEntity dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            System.out.println("data saved ");
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void saveBiddingDto(BiddingDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            System.out.println("saveBiddingDto data saved ");
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void saveSoldBought(SoldBoughtDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            System.out.println("saveSoldBought data saved ");
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> getEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getEmail");
            List<String> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<String> getEmailForLogin(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getEmail");
            List<String> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Long> getContactNumber(long contactNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getContactNumber");
            List<Long> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<String> getPanCardNumber(String panCardNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getPanCardNumber");
            List<String> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Long> getAadharNumber(long aadharNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getAadharNumber");
            List<Long> list = query.getResultList();
            return list;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public RegisterDto getRegisterInfo(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            RegisterDto dto = em.find(RegisterDto.class, id);
            System.out.println("getRegisterInfo :" + dto);
            return dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;

    }

    @Override
    public List<PropertyEntity> getProperty(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getProperty");
            query.setParameter("id", id);
            List<PropertyEntity> dto = query.getResultList();
            return dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<BiddingDto> getBiddingInfoById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBiddingInfoById");
            query.setParameter("rid", id);
            query.setParameter("pid", id);
            List<BiddingDto> dto = query.getResultList();
            return dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<SoldBoughtDto> getSellerDetailsById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getSellerDetailsById");
            query.setParameter("sellerId", id);
            List<SoldBoughtDto> dto= query.getResultList();
            return dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<SoldBoughtDto> getBuyerDetailsById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBuyerDetailsById");
            query.setParameter("buyerId", id);
            List<SoldBoughtDto> dto= query.getResultList();
            return dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public RegisterDto getInfoByEmail(String email) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getInfoByEmail");
            query.setParameter("email", email);
            Object dto = query.getSingleResult();
            return (RegisterDto) dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public boolean updateById(RegisterDto dto, int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("updateRegisterAndAuditById");
            query.setParameter("firstName", dto.getFirstName());
            query.setParameter("lastName", dto.getLastName());
            query.setParameter("email", dto.getEmail());
            query.setParameter("alternativeEmail", dto.getAlternativeEmail());
            query.setParameter("contactNumber", dto.getContactNumber());
            query.setParameter("alternativeContact", dto.getAlternativeContact());
            query.setParameter("currentAddress", dto.getCurrentAddress());
            query.setParameter("permanentAddress", dto.getPermanentAddress());
            query.setParameter("panCardNumber", dto.getPanCardNumber());
            query.setParameter("aadharNumber", dto.getAadharNumber());
            query.setParameter("rid", id);
            int i = query.executeUpdate();

            Query query1 = em.createNamedQuery("updateAudit");
            query1.setParameter("updatedBy", dto.getAudit().getUpdatedBy());
            query1.setParameter("updatedOn", dto.getAudit().getUpdatedOn());
            query1.setParameter("i", id);
            query1.executeUpdate();

            if (i == 1) {
                em.getTransaction().commit();
                System.out.println(i + "-----------------");
                return true;
            } else {
                System.out.println("can't delete");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean updateOTPByEmail(String otp, String email) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("updateOTPByEmail");
            query.setParameter("otp", otp);
            query.setParameter("email", email);
            int i = query.executeUpdate();
            if (i == 1) {
                em.getTransaction().commit();
                System.out.println("Otp updateRegisterByEmail ");
                return true;
            } else {
                System.out.println("updateRegisterByEmail not");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean updateAccountStatusByEmail(String accountStatus, String email) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("updateAccountStatusByEmail");
            query.setParameter("accountStatus", accountStatus);
            query.setParameter("email", email);
            int i = query.executeUpdate();
            if (i == 1) {
                em.getTransaction().commit();
                System.out.println("accountStatus updateAccountStatusByEmail ");
                return true;
            } else {
                System.out.println("updateAccountStatusByEmail not");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean updateStatuesById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("updateStatuesById");
            query.setParameter("statues", "sold");
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public BiddingDto getBiddingById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBiddingById");
            query.setParameter("id", id);
            Object dto = query.getSingleResult();
            return (BiddingDto) dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public String getOtpByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getOtpByEmail");
            query.setParameter("email", email);
            Object result = query.getSingleResult();
            return (String) result;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(int rid) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            RegisterDto shopInfoEntity = em.find(RegisterDto.class, rid);
            if (shopInfoEntity != null) {
                em.remove(shopInfoEntity);
                em.getTransaction().commit();
                System.out.println("Data deleted ..!");
                return true;
            } else {
                System.out.println("Data not deleted");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public PropertyEntity getPropertyTypeById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getPropertyTypeById");
            query.setParameter("id", id);
            Object dto = query.getSingleResult();
            return (PropertyEntity) dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
