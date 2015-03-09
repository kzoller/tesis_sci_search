/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Controladores;

import GIISIC.Controladores.exceptions.NonexistentEntityException;
import GIISIC.Controladores.exceptions.PreexistingEntityException;
import GIISIC.Controladores.exceptions.RollbackFailureException;
import GIISIC.Entidades.InfEvidencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import GIISIC.Entidades.MacUsuario;
import GIISIC.Entidades.MgiAsignarEvidencias;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pruebas_ECV
 */
public class InfEvidenciaJpaController implements Serializable {

    public InfEvidenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfEvidencia infEvidencia) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (infEvidencia.getMgiAsignarEvidenciasCollection() == null) {
            infEvidencia.setMgiAsignarEvidenciasCollection(new ArrayList<MgiAsignarEvidencias>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MacUsuario refUsuario = infEvidencia.getRefUsuario();
            if (refUsuario != null) {
                refUsuario = em.getReference(refUsuario.getClass(), refUsuario.getUsId());
                infEvidencia.setRefUsuario(refUsuario);
            }
            Collection<MgiAsignarEvidencias> attachedMgiAsignarEvidenciasCollection = new ArrayList<MgiAsignarEvidencias>();
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionMgiAsignarEvidenciasToAttach : infEvidencia.getMgiAsignarEvidenciasCollection()) {
                mgiAsignarEvidenciasCollectionMgiAsignarEvidenciasToAttach = em.getReference(mgiAsignarEvidenciasCollectionMgiAsignarEvidenciasToAttach.getClass(), mgiAsignarEvidenciasCollectionMgiAsignarEvidenciasToAttach.getGiaEId());
                attachedMgiAsignarEvidenciasCollection.add(mgiAsignarEvidenciasCollectionMgiAsignarEvidenciasToAttach);
            }
            infEvidencia.setMgiAsignarEvidenciasCollection(attachedMgiAsignarEvidenciasCollection);
            em.persist(infEvidencia);
            if (refUsuario != null) {
                refUsuario.getInfEvidenciaCollection().add(infEvidencia);
                refUsuario = em.merge(refUsuario);
            }
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionMgiAsignarEvidencias : infEvidencia.getMgiAsignarEvidenciasCollection()) {
                InfEvidencia oldRefEvidenciaOfMgiAsignarEvidenciasCollectionMgiAsignarEvidencias = mgiAsignarEvidenciasCollectionMgiAsignarEvidencias.getRefEvidencia();
                mgiAsignarEvidenciasCollectionMgiAsignarEvidencias.setRefEvidencia(infEvidencia);
                mgiAsignarEvidenciasCollectionMgiAsignarEvidencias = em.merge(mgiAsignarEvidenciasCollectionMgiAsignarEvidencias);
                if (oldRefEvidenciaOfMgiAsignarEvidenciasCollectionMgiAsignarEvidencias != null) {
                    oldRefEvidenciaOfMgiAsignarEvidenciasCollectionMgiAsignarEvidencias.getMgiAsignarEvidenciasCollection().remove(mgiAsignarEvidenciasCollectionMgiAsignarEvidencias);
                    oldRefEvidenciaOfMgiAsignarEvidenciasCollectionMgiAsignarEvidencias = em.merge(oldRefEvidenciaOfMgiAsignarEvidenciasCollectionMgiAsignarEvidencias);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInfEvidencia(infEvidencia.getEnId()) != null) {
                throw new PreexistingEntityException("InfEvidencia " + infEvidencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfEvidencia infEvidencia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            InfEvidencia persistentInfEvidencia = em.find(InfEvidencia.class, infEvidencia.getEnId());
            MacUsuario refUsuarioOld = persistentInfEvidencia.getRefUsuario();
            MacUsuario refUsuarioNew = infEvidencia.getRefUsuario();
            Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollectionOld = persistentInfEvidencia.getMgiAsignarEvidenciasCollection();
            Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollectionNew = infEvidencia.getMgiAsignarEvidenciasCollection();
            if (refUsuarioNew != null) {
                refUsuarioNew = em.getReference(refUsuarioNew.getClass(), refUsuarioNew.getUsId());
                infEvidencia.setRefUsuario(refUsuarioNew);
            }
            Collection<MgiAsignarEvidencias> attachedMgiAsignarEvidenciasCollectionNew = new ArrayList<MgiAsignarEvidencias>();
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionNewMgiAsignarEvidenciasToAttach : mgiAsignarEvidenciasCollectionNew) {
                mgiAsignarEvidenciasCollectionNewMgiAsignarEvidenciasToAttach = em.getReference(mgiAsignarEvidenciasCollectionNewMgiAsignarEvidenciasToAttach.getClass(), mgiAsignarEvidenciasCollectionNewMgiAsignarEvidenciasToAttach.getGiaEId());
                attachedMgiAsignarEvidenciasCollectionNew.add(mgiAsignarEvidenciasCollectionNewMgiAsignarEvidenciasToAttach);
            }
            mgiAsignarEvidenciasCollectionNew = attachedMgiAsignarEvidenciasCollectionNew;
            infEvidencia.setMgiAsignarEvidenciasCollection(mgiAsignarEvidenciasCollectionNew);
            infEvidencia = em.merge(infEvidencia);
            if (refUsuarioOld != null && !refUsuarioOld.equals(refUsuarioNew)) {
                refUsuarioOld.getInfEvidenciaCollection().remove(infEvidencia);
                refUsuarioOld = em.merge(refUsuarioOld);
            }
            if (refUsuarioNew != null && !refUsuarioNew.equals(refUsuarioOld)) {
                refUsuarioNew.getInfEvidenciaCollection().add(infEvidencia);
                refUsuarioNew = em.merge(refUsuarioNew);
            }
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionOldMgiAsignarEvidencias : mgiAsignarEvidenciasCollectionOld) {
                if (!mgiAsignarEvidenciasCollectionNew.contains(mgiAsignarEvidenciasCollectionOldMgiAsignarEvidencias)) {
                    mgiAsignarEvidenciasCollectionOldMgiAsignarEvidencias.setRefEvidencia(null);
                    mgiAsignarEvidenciasCollectionOldMgiAsignarEvidencias = em.merge(mgiAsignarEvidenciasCollectionOldMgiAsignarEvidencias);
                }
            }
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias : mgiAsignarEvidenciasCollectionNew) {
                if (!mgiAsignarEvidenciasCollectionOld.contains(mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias)) {
                    InfEvidencia oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias = mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias.getRefEvidencia();
                    mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias.setRefEvidencia(infEvidencia);
                    mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias = em.merge(mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias);
                    if (oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias != null && !oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias.equals(infEvidencia)) {
                        oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias.getMgiAsignarEvidenciasCollection().remove(mgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias);
                        oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias = em.merge(oldRefEvidenciaOfMgiAsignarEvidenciasCollectionNewMgiAsignarEvidencias);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infEvidencia.getEnId();
                if (findInfEvidencia(id) == null) {
                    throw new NonexistentEntityException("The infEvidencia with id " + id + " no longer exists.");
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
            InfEvidencia infEvidencia;
            try {
                infEvidencia = em.getReference(InfEvidencia.class, id);
                infEvidencia.getEnId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infEvidencia with id " + id + " no longer exists.", enfe);
            }
            MacUsuario refUsuario = infEvidencia.getRefUsuario();
            if (refUsuario != null) {
                refUsuario.getInfEvidenciaCollection().remove(infEvidencia);
                refUsuario = em.merge(refUsuario);
            }
            Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollection = infEvidencia.getMgiAsignarEvidenciasCollection();
            for (MgiAsignarEvidencias mgiAsignarEvidenciasCollectionMgiAsignarEvidencias : mgiAsignarEvidenciasCollection) {
                mgiAsignarEvidenciasCollectionMgiAsignarEvidencias.setRefEvidencia(null);
                mgiAsignarEvidenciasCollectionMgiAsignarEvidencias = em.merge(mgiAsignarEvidenciasCollectionMgiAsignarEvidencias);
            }
            em.remove(infEvidencia);
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

    public List<InfEvidencia> findInfEvidenciaEntities() {
        return findInfEvidenciaEntities(true, -1, -1);
    }

    public List<InfEvidencia> findInfEvidenciaEntities(int maxResults, int firstResult) {
        return findInfEvidenciaEntities(false, maxResults, firstResult);
    }

    private List<InfEvidencia> findInfEvidenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfEvidencia.class));
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

    public InfEvidencia findInfEvidencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfEvidencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfEvidenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfEvidencia> rt = cq.from(InfEvidencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
