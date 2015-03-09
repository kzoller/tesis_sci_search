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
import GIISIC.Entidades.InfCatalogo;
import GIISIC.Entidades.MacUsuario;
import GIISIC.Entidades.MgiAsignarTitulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pruebas_ECV
 */
public class MgiAsignarTituloJpaController implements Serializable {

    public MgiAsignarTituloJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MgiAsignarTitulo mgiAsignarTitulo) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            InfCatalogo refTitulo = mgiAsignarTitulo.getRefTitulo();
            if (refTitulo != null) {
                refTitulo = em.getReference(refTitulo.getClass(), refTitulo.getCatId());
                mgiAsignarTitulo.setRefTitulo(refTitulo);
            }
            MacUsuario refUsuario = mgiAsignarTitulo.getRefUsuario();
            if (refUsuario != null) {
                refUsuario = em.getReference(refUsuario.getClass(), refUsuario.getUsId());
                mgiAsignarTitulo.setRefUsuario(refUsuario);
            }
            em.persist(mgiAsignarTitulo);
            if (refTitulo != null) {
                refTitulo.getMgiAsignarTituloCollection().add(mgiAsignarTitulo);
                refTitulo = em.merge(refTitulo);
            }
            if (refUsuario != null) {
                refUsuario.getMgiAsignarTituloCollection().add(mgiAsignarTitulo);
                refUsuario = em.merge(refUsuario);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMgiAsignarTitulo(mgiAsignarTitulo.getATId()) != null) {
                throw new PreexistingEntityException("MgiAsignarTitulo " + mgiAsignarTitulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MgiAsignarTitulo mgiAsignarTitulo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MgiAsignarTitulo persistentMgiAsignarTitulo = em.find(MgiAsignarTitulo.class, mgiAsignarTitulo.getATId());
            InfCatalogo refTituloOld = persistentMgiAsignarTitulo.getRefTitulo();
            InfCatalogo refTituloNew = mgiAsignarTitulo.getRefTitulo();
            MacUsuario refUsuarioOld = persistentMgiAsignarTitulo.getRefUsuario();
            MacUsuario refUsuarioNew = mgiAsignarTitulo.getRefUsuario();
            if (refTituloNew != null) {
                refTituloNew = em.getReference(refTituloNew.getClass(), refTituloNew.getCatId());
                mgiAsignarTitulo.setRefTitulo(refTituloNew);
            }
            if (refUsuarioNew != null) {
                refUsuarioNew = em.getReference(refUsuarioNew.getClass(), refUsuarioNew.getUsId());
                mgiAsignarTitulo.setRefUsuario(refUsuarioNew);
            }
            mgiAsignarTitulo = em.merge(mgiAsignarTitulo);
            if (refTituloOld != null && !refTituloOld.equals(refTituloNew)) {
                refTituloOld.getMgiAsignarTituloCollection().remove(mgiAsignarTitulo);
                refTituloOld = em.merge(refTituloOld);
            }
            if (refTituloNew != null && !refTituloNew.equals(refTituloOld)) {
                refTituloNew.getMgiAsignarTituloCollection().add(mgiAsignarTitulo);
                refTituloNew = em.merge(refTituloNew);
            }
            if (refUsuarioOld != null && !refUsuarioOld.equals(refUsuarioNew)) {
                refUsuarioOld.getMgiAsignarTituloCollection().remove(mgiAsignarTitulo);
                refUsuarioOld = em.merge(refUsuarioOld);
            }
            if (refUsuarioNew != null && !refUsuarioNew.equals(refUsuarioOld)) {
                refUsuarioNew.getMgiAsignarTituloCollection().add(mgiAsignarTitulo);
                refUsuarioNew = em.merge(refUsuarioNew);
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
                Integer id = mgiAsignarTitulo.getATId();
                if (findMgiAsignarTitulo(id) == null) {
                    throw new NonexistentEntityException("The mgiAsignarTitulo with id " + id + " no longer exists.");
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
            MgiAsignarTitulo mgiAsignarTitulo;
            try {
                mgiAsignarTitulo = em.getReference(MgiAsignarTitulo.class, id);
                mgiAsignarTitulo.getATId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mgiAsignarTitulo with id " + id + " no longer exists.", enfe);
            }
            InfCatalogo refTitulo = mgiAsignarTitulo.getRefTitulo();
            if (refTitulo != null) {
                refTitulo.getMgiAsignarTituloCollection().remove(mgiAsignarTitulo);
                refTitulo = em.merge(refTitulo);
            }
            MacUsuario refUsuario = mgiAsignarTitulo.getRefUsuario();
            if (refUsuario != null) {
                refUsuario.getMgiAsignarTituloCollection().remove(mgiAsignarTitulo);
                refUsuario = em.merge(refUsuario);
            }
            em.remove(mgiAsignarTitulo);
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

    public List<MgiAsignarTitulo> findMgiAsignarTituloEntities() {
        return findMgiAsignarTituloEntities(true, -1, -1);
    }

    public List<MgiAsignarTitulo> findMgiAsignarTituloEntities(int maxResults, int firstResult) {
        return findMgiAsignarTituloEntities(false, maxResults, firstResult);
    }

    private List<MgiAsignarTitulo> findMgiAsignarTituloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MgiAsignarTitulo.class));
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

    public MgiAsignarTitulo findMgiAsignarTitulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MgiAsignarTitulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMgiAsignarTituloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MgiAsignarTitulo> rt = cq.from(MgiAsignarTitulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
