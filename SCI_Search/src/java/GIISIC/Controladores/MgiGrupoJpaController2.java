/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Controladores;

import GIISIC.Controladores.exceptions.NonexistentEntityException;
import GIISIC.Controladores.exceptions.PreexistingEntityException;
import GIISIC.Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import GIISIC.Entidades.MacUsuario;
import GIISIC.Entidades.MgiGrupo;
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
public class MgiGrupoJpaController2 implements Serializable {

    public MgiGrupoJpaController2(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MgiGrupo mgiGrupo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (mgiGrupo.getMacUsuarioCollection() == null) {
            mgiGrupo.setMacUsuarioCollection(new ArrayList<MacUsuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<MacUsuario> attachedMacUsuarioCollection = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollectionMacUsuarioToAttach : mgiGrupo.getMacUsuarioCollection()) {
                macUsuarioCollectionMacUsuarioToAttach = em.getReference(macUsuarioCollectionMacUsuarioToAttach.getClass(), macUsuarioCollectionMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection.add(macUsuarioCollectionMacUsuarioToAttach);
            }
            mgiGrupo.setMacUsuarioCollection(attachedMacUsuarioCollection);
            em.persist(mgiGrupo);
            for (MacUsuario macUsuarioCollectionMacUsuario : mgiGrupo.getMacUsuarioCollection()) {
                MgiGrupo oldRefGrupoOfMacUsuarioCollectionMacUsuario = macUsuarioCollectionMacUsuario.getRefGrupo();
                macUsuarioCollectionMacUsuario.setRefGrupo(mgiGrupo);
                macUsuarioCollectionMacUsuario = em.merge(macUsuarioCollectionMacUsuario);
                if (oldRefGrupoOfMacUsuarioCollectionMacUsuario != null) {
                    oldRefGrupoOfMacUsuarioCollectionMacUsuario.getMacUsuarioCollection().remove(macUsuarioCollectionMacUsuario);
                    oldRefGrupoOfMacUsuarioCollectionMacUsuario = em.merge(oldRefGrupoOfMacUsuarioCollectionMacUsuario);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMgiGrupo(mgiGrupo.getGrId()) != null) {
                throw new PreexistingEntityException("MgiGrupo " + mgiGrupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MgiGrupo mgiGrupo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MgiGrupo persistentMgiGrupo = em.find(MgiGrupo.class, mgiGrupo.getGrId());
            Collection<MacUsuario> macUsuarioCollectionOld = persistentMgiGrupo.getMacUsuarioCollection();
            Collection<MacUsuario> macUsuarioCollectionNew = mgiGrupo.getMacUsuarioCollection();
            Collection<MacUsuario> attachedMacUsuarioCollectionNew = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollectionNewMacUsuarioToAttach : macUsuarioCollectionNew) {
                macUsuarioCollectionNewMacUsuarioToAttach = em.getReference(macUsuarioCollectionNewMacUsuarioToAttach.getClass(), macUsuarioCollectionNewMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollectionNew.add(macUsuarioCollectionNewMacUsuarioToAttach);
            }
            macUsuarioCollectionNew = attachedMacUsuarioCollectionNew;
            mgiGrupo.setMacUsuarioCollection(macUsuarioCollectionNew);
            mgiGrupo = em.merge(mgiGrupo);
            for (MacUsuario macUsuarioCollectionOldMacUsuario : macUsuarioCollectionOld) {
                if (!macUsuarioCollectionNew.contains(macUsuarioCollectionOldMacUsuario)) {
                    macUsuarioCollectionOldMacUsuario.setRefGrupo(null);
                    macUsuarioCollectionOldMacUsuario = em.merge(macUsuarioCollectionOldMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollectionNewMacUsuario : macUsuarioCollectionNew) {
                if (!macUsuarioCollectionOld.contains(macUsuarioCollectionNewMacUsuario)) {
                    MgiGrupo oldRefGrupoOfMacUsuarioCollectionNewMacUsuario = macUsuarioCollectionNewMacUsuario.getRefGrupo();
                    macUsuarioCollectionNewMacUsuario.setRefGrupo(mgiGrupo);
                    macUsuarioCollectionNewMacUsuario = em.merge(macUsuarioCollectionNewMacUsuario);
                    if (oldRefGrupoOfMacUsuarioCollectionNewMacUsuario != null && !oldRefGrupoOfMacUsuarioCollectionNewMacUsuario.equals(mgiGrupo)) {
                        oldRefGrupoOfMacUsuarioCollectionNewMacUsuario.getMacUsuarioCollection().remove(macUsuarioCollectionNewMacUsuario);
                        oldRefGrupoOfMacUsuarioCollectionNewMacUsuario = em.merge(oldRefGrupoOfMacUsuarioCollectionNewMacUsuario);
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
                Integer id = mgiGrupo.getGrId();
                if (findMgiGrupo(id) == null) {
                    throw new NonexistentEntityException("The mgiGrupo with id " + id + " no longer exists.");
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
            MgiGrupo mgiGrupo;
            try {
                mgiGrupo = em.getReference(MgiGrupo.class, id);
                mgiGrupo.getGrId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mgiGrupo with id " + id + " no longer exists.", enfe);
            }
            Collection<MacUsuario> macUsuarioCollection = mgiGrupo.getMacUsuarioCollection();
            for (MacUsuario macUsuarioCollectionMacUsuario : macUsuarioCollection) {
                macUsuarioCollectionMacUsuario.setRefGrupo(null);
                macUsuarioCollectionMacUsuario = em.merge(macUsuarioCollectionMacUsuario);
            }
            em.remove(mgiGrupo);
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

    public List<MgiGrupo> findMgiGrupoEntities() {
        return findMgiGrupoEntities(true, -1, -1);
    }

    public List<MgiGrupo> findMgiGrupoEntities(int maxResults, int firstResult) {
        return findMgiGrupoEntities(false, maxResults, firstResult);
    }

    private List<MgiGrupo> findMgiGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MgiGrupo.class));
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

    public MgiGrupo findMgiGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MgiGrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMgiGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MgiGrupo> rt = cq.from(MgiGrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
