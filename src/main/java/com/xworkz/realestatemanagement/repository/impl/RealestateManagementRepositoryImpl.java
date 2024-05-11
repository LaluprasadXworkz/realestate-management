package com.xworkz.realestatemanagement.repository.impl;


import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.repository.RealestateManagementRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class RealestateManagementRepositoryImpl implements RealestateManagementRepository {
    private static final Logger logger = LoggerFactory.getLogger(RealestateManagementRepositoryImpl.class);

    @Autowired
    EntityManagerFactory emf;
    @Override
    public boolean saveRegisterInfo(RegisterDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            logger.info("Saved RegisterDto with ID :", dto.toString());
            return  true;
        } catch (PersistenceException e) {
            logger.error("Error saving RegisterDto", e);
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
            logger.debug("Entity manager closed after saving RegisterDto");
        }
    }

    @Override
    public void savePropertyDTO(PropertyEntity dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            logger.info("Saved PropertyEntity : ", dto.toString());
        } catch (PersistenceException e) {
            logger.error("Error saving PropertyEntity", e);
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            logger.debug("Entity manager closed after saving PropertyEntity");
        }
    }

    @Override
    public void saveBiddingDto(BiddingDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            logger.info("Saved BiddingDto : ", dto.toString());
        } catch (PersistenceException e) {
            logger.error("Error saving BiddingDto", e);
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            logger.debug("Entity manager closed after saving BiddingDto");
        }
    }

    @Override
    public void saveSoldBought(SoldBoughtDto dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
            logger.info("Saved SoldBoughtDto : ", dto.toString());
        } catch (PersistenceException e) {
            logger.error("Error saving SoldBoughtDto", e);
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            logger.debug("Entity manager closed after saving SoldBoughtDto");
        }
    }

    @Override
    public List<String> getEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getEmail");
            List<String> list = query.getResultList();
            logger.info("Retrieved emails for email : ", list.toArray());
            return list;
        } catch (PersistenceException e) {
            logger.error("Error fetching emails for email: {}", email, e);
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching emails for email: {}", email);
        }

    }

    @Override
    public List<String> getEmailForLogin(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getEmail");
            List<String> list = query.getResultList();
            logger.info("Retrieved emails for login with email: {}", list.toArray());
            System.out.println(list);
            return list;
        } catch (PersistenceException e) {
            logger.error("Error fetching emails for login with email: {}", email, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching emails for login with email: {}", email);
        }
    }

    @Override
    public List<Long> getContactNumber(long contactNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getContactNumber");
            List<Long> list = query.getResultList();
            logger.info("Retrieved contact numbers for contact number: {}", list.toArray());
            return list;
        } catch (PersistenceException e) {
            logger.error("Error fetching contact numbers for contact number: {}", contactNumber, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching contact numbers for contact number: {}", contactNumber);
        }
    }

    @Override
    public List<String> getPanCardNumber(String panCardNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getPanCardNumber");
            List<String> list = query.getResultList();
            logger.info("Retrieved PAN card numbers for PAN card number: {}", list.toArray());
            return list;
        } catch (PersistenceException e) {
            logger.error("Error fetching PAN card numbers for PAN card number: {}", panCardNumber, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching PAN card numbers for PAN card number: {}", panCardNumber);
        }
    }

    @Override
    public List<Long> getAadharNumber(long aadharNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getAadharNumber");
            List<Long> list = query.getResultList();
            logger.info("Retrieved Aadhar numbers for Aadhar number: {}", list.toArray());
            return list;
        } catch (PersistenceException e) {
            logger.error("Error fetching Aadhar numbers for Aadhar number: {}", aadharNumber, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching Aadhar numbers for Aadhar number: {}", aadharNumber);
        }
    }

    @Override
    public RegisterDto getRegisterInfo(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            RegisterDto dto = em.find(RegisterDto.class, id);
            logger.info("Retrieved RegisterDto with ID: {}", dto.toString());
            return dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching RegisterDto with ID: {}", id, e);
            e.printStackTrace();
            return null;
        } finally {
            logger.debug("Entity manager closed after fetching RegisterDto with ID: {}", id);
            em.close();
        }
    }

    @Override
    public List<PropertyEntity> getProperty(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getProperty");
            query.setParameter("id", id);
            List<PropertyEntity> dto = query.getResultList();
            logger.info("PropertyEntity :"+dto.toArray());
            return dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching property for ID: {}", id, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching property for ID: {}", id);
        }
    }

    @Override
    public List<BiddingDto> getBiddingInfoById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBiddingInfoById");
            query.setParameter("rid", id);
            query.setParameter("pid", id);
            List<BiddingDto> dto = query.getResultList();
            logger.info("Bidding info"+dto.toArray());
            return dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching bidding info for ID: {}", id, e);
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching bidding info for ID: {}", id);
        }
    }

    @Override
    public List<SoldBoughtDto> getSellerDetailsById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getSellerDetailsById");
            query.setParameter("sellerId", id);
            List<SoldBoughtDto> dto= query.getResultList();
            logger.info("Seller details : "+dto.toArray());
            return dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching seller details for ID: {}", id, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching seller details for ID: {}", id);
        }

    }

    @Override
    public List<SoldBoughtDto> getBuyerDetailsById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBuyerDetailsById");
            query.setParameter("buyerId", id);
            List<SoldBoughtDto> dto= query.getResultList();
            logger.info("Buyer details :"+dto.toArray());
            return dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching buyer details for ID: {}", id, e);
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching buyer details for ID: {}", id);
        }
    }

    @Override
    public RegisterDto getInfoByEmail(String email) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getInfoByEmail");
            query.setParameter("email", email);
            Object dto = query.getSingleResult();
            logger.info("Info Email :"+dto.toString());
            return (RegisterDto) dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching RegisterDto by email: {}", email, e);
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching RegisterDto by email: {}", email);
        }
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
            int i2=query1.executeUpdate();

            if (i == 1 && i2 == 1) {
                logger.info("Updated RegisterDto with ID: {}", id);
                em.getTransaction().commit();
                return true;
            } else {
                logger.warn("Failed to update RegisterDto with ID: {}", id);
                em.getTransaction().rollback();
                return false;
            }
        } catch (PersistenceException e) {
            logger.error("Error updating RegisterDto with ID: {}", id, e);
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
            logger.debug("Entity manager closed after updating RegisterDto with ID: {}", id);
        }
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
                logger.info("OTP updated successfully for email: {}", email);
                return true;
            } else {
                logger.warn("No OTP updated for email: {}", email);
                em.getTransaction().rollback();
                return false;
            }
        } catch (PersistenceException e) {
            logger.error("Error updating OTP for email: {}", email, e);
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
            logger.debug("Entity manager closed after updating OTP for email: {}", email);
        }
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
                logger.info("Account status updated successfully for email: {}", email);
                return true;
            } else {
                logger.warn("No account status updated for email: {}", email);
                em.getTransaction().rollback();
                return false;
            }
        } catch (PersistenceException e) {
            logger.error("Error updating account status for email: {}", email, e);
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
            logger.debug("Entity manager closed after updating account status for email: {}", email);
        }
    }

    @Override
    public boolean updateStatuesById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("updateStatuesById");
            query.setParameter("statues", "sold");
            query.setParameter("id", id);
           int update= query.executeUpdate();
            if (update == 1) {
                em.getTransaction().commit();
                logger.info("Status updated to 'sold' for ID: {}", id);
                return true;
            }else {
                logger.warn("No record updated for ID: {}", id);
                em.getTransaction().rollback();
                return false;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            logger.error("Error updating status for ID: {}", id, e);
            return false;
        } finally {
            em.close();
            logger.debug("Entity manager closed after updating status for ID: {}", id);
        }
    }

    @Override
    public BiddingDto getBiddingById(int id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getBiddingById");
            query.setParameter("id", id);
            Object dto = query.getSingleResult();
            logger.info("Bidding : "+dto.toString());
            return (BiddingDto) dto;
        } catch (PersistenceException e) {
            e.printStackTrace();
            logger.error("Error fetching BiddingDto with ID: {}", id, e);
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching BiddingDto for ID: {}", id);
        }
    }

    @Override
    public String getOtpByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("getOtpByEmail");
            query.setParameter("email", email);
            Object result = query.getSingleResult();
            logger.info("Email : "+ result);
            return (String) result;
        } catch (PersistenceException e) {
            e.printStackTrace();
            logger.error("Error fetching OTP for email: {}", email, e);
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching OTP for email: {}", email);
        }
    }

    @Override
    public boolean deleteById(int rid) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            RegisterDto registerDto = em.find(RegisterDto.class, rid);
            if (registerDto != null) {
                em.remove(registerDto);
                em.getTransaction().commit();
                logger.info("Deleted RegisterDto with ID: {}", rid);
                return true;
            } else {
                logger.warn("RegisterDto with ID {} not found, data not deleted", rid);
            }
        } catch (PersistenceException e) {
            logger.error("Error deleting RegisterDto with ID: {}", rid, e);
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            logger.debug("Entity manager closed after deleting RegisterDto with ID: {}", rid);
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
            logger.info("PropertyEntity found for ID: {}",dto.toString());
            return (PropertyEntity) dto;
        } catch (PersistenceException e) {
            logger.error("Error fetching PropertyEntity by ID: {}", id, e);
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            logger.debug("Entity manager closed after fetching PropertyEntity by ID: {}", id);
        }
    }
}
