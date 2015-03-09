/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Controladores;

import GIISIC.Controladores.exceptions.NonexistentEntityException;
import GIISIC.Controladores.exceptions.PreexistingEntityException;
import GIISIC.Controladores.exceptions.RollbackFailureException;
import GIISIC.Entidades.MacCofinanciador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pruebas_ECV
 */
public class MacCofinanciadorJpaController implements Serializable {

    public MacCofinanciadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MacCofinanciador macCofinanciador) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(macCofinanciador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMacCofinanciador(macCofinanciador.getUsId()) != null) {
                throw new PreexistingEntityException("MacCofinanciador " + macCofinanciador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MacCofinanciador macCofinanciador) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            macCofinanciador = em.merge(macCofinanciador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = macCofinanciador.getUsId();
                if (findMacCofinanciador(id) == null) {
                    throw new NonexistentEntityException("The macCofinanciador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MacCofinanciador macCofinanciador;
            try {
                macCofinanciador = em.getReference(MacCofinanciador.class, id);
                macCofinanciador.getUsId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The macCofinanciador with id " + id + " no longer exists.", enfe);
            }
            em.remove(macCofinanciador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MacCofinanciador> findMacCofinanciadorEntities() {
        return findMacCofinanciadorEntities(true, -1, -1);
    }

    public List<MacCofinanciador> findMacCofinanciadorEntities(int maxResults, int firstResult) {
        return findMacCofinanciadorEntities(false, maxResults, firstResult);
    }

    private List<MacCofinanciador> findMacCofinanciadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MacCofinanciador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MacCofinanciador findMacCofinanciador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MacCofinanciador.class, id);
        } finally {
            em.close();
        }
    }

    public int getMacCofinanciadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MacCofinanciador> rt = cq.from(MacCofinanciador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
