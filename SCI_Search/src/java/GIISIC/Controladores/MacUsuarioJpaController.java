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
import GIISIC.Entidades.MgiGrupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pruebas_ECV
 */
public class MacUsuarioJpaController implements Serializable {

    public MacUsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MacUsuario macUsuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            InfCatalogo refPerfil = macUsuario.getRefPerfil();
            if (refPerfil != null) {
                refPerfil = em.getReference(refPerfil.getClass(), refPerfil.getCatId());
                macUsuario.setRefPerfil(refPerfil);
            }
            InfCatalogo refNacionalidad = macUsuario.getRefNacionalidad();
            if (refNacionalidad != null) {
                refNacionalidad = em.getReference(refNacionalidad.getClass(), refNacionalidad.getCatId());
                macUsuario.setRefNacionalidad(refNacionalidad);
            }
            InfCatalogo refCargo = macUsuario.getRefCargo();
            if (refCargo != null) {
                refCargo = em.getReference(refCargo.getClass(), refCargo.getCatId());
                macUsuario.setRefCargo(refCargo);
            }
            InfCatalogo refTipoContrato = macUsuario.getRefTipoContrato();
            if (refTipoContrato != null) {
                refTipoContrato = em.getReference(refTipoContrato.getClass(), refTipoContrato.getCatId());
                macUsuario.setRefTipoContrato(refTipoContrato);
            }
            MgiGrupo refGrupo = macUsuario.getRefGrupo();
            if (refGrupo != null) {
                refGrupo = em.getReference(refGrupo.getClass(), refGrupo.getGrId());
                macUsuario.setRefGrupo(refGrupo);
            }
            em.persist(macUsuario);
            if (refPerfil != null) {
                refPerfil.getMacUsuarioCollection().add(macUsuario);
                refPerfil = em.merge(refPerfil);
            }
            if (refNacionalidad != null) {
                refNacionalidad.getMacUsuarioCollection().add(macUsuario);
                refNacionalidad = em.merge(refNacionalidad);
            }
            if (refCargo != null) {
                refCargo.getMacUsuarioCollection().add(macUsuario);
                refCargo = em.merge(refCargo);
            }
            if (refTipoContrato != null) {
                refTipoContrato.getMacUsuarioCollection().add(macUsuario);
                refTipoContrato = em.merge(refTipoContrato);
            }
            if (refGrupo != null) {
                refGrupo.getMacUsuarioCollection().add(macUsuario);
                refGrupo = em.merge(refGrupo);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMacUsuario(macUsuario.getUsId()) != null) {
                throw new PreexistingEntityException("MacUsuario " + macUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MacUsuario macUsuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MacUsuario persistentMacUsuario = em.find(MacUsuario.class, macUsuario.getUsId());
            InfCatalogo refPerfilOld = persistentMacUsuario.getRefPerfil();
            InfCatalogo refPerfilNew = macUsuario.getRefPerfil();
            InfCatalogo refNacionalidadOld = persistentMacUsuario.getRefNacionalidad();
            InfCatalogo refNacionalidadNew = macUsuario.getRefNacionalidad();
            InfCatalogo refCargoOld = persistentMacUsuario.getRefCargo();
            InfCatalogo refCargoNew = macUsuario.getRefCargo();
            InfCatalogo refTipoContratoOld = persistentMacUsuario.getRefTipoContrato();
            InfCatalogo refTipoContratoNew = macUsuario.getRefTipoContrato();
            MgiGrupo refGrupoOld = persistentMacUsuario.getRefGrupo();
            MgiGrupo refGrupoNew = macUsuario.getRefGrupo();
            if (refPerfilNew != null) {
                refPerfilNew = em.getReference(refPerfilNew.getClass(), refPerfilNew.getCatId());
                macUsuario.setRefPerfil(refPerfilNew);
            }
            if (refNacionalidadNew != null) {
                refNacionalidadNew = em.getReference(refNacionalidadNew.getClass(), refNacionalidadNew.getCatId());
                macUsuario.setRefNacionalidad(refNacionalidadNew);
            }
            if (refCargoNew != null) {
                refCargoNew = em.getReference(refCargoNew.getClass(), refCargoNew.getCatId());
                macUsuario.setRefCargo(refCargoNew);
            }
            if (refTipoContratoNew != null) {
                refTipoContratoNew = em.getReference(refTipoContratoNew.getClass(), refTipoContratoNew.getCatId());
                macUsuario.setRefTipoContrato(refTipoContratoNew);
            }
            if (refGrupoNew != null) {
                refGrupoNew = em.getReference(refGrupoNew.getClass(), refGrupoNew.getGrId());
                macUsuario.setRefGrupo(refGrupoNew);
            }
            macUsuario = em.merge(macUsuario);
            if (refPerfilOld != null && !refPerfilOld.equals(refPerfilNew)) {
                refPerfilOld.getMacUsuarioCollection().remove(macUsuario);
                refPerfilOld = em.merge(refPerfilOld);
            }
            if (refPerfilNew != null && !refPerfilNew.equals(refPerfilOld)) {
                refPerfilNew.getMacUsuarioCollection().add(macUsuario);
                refPerfilNew = em.merge(refPerfilNew);
            }
            if (refNacionalidadOld != null && !refNacionalidadOld.equals(refNacionalidadNew)) {
                refNacionalidadOld.getMacUsuarioCollection().remove(macUsuario);
                refNacionalidadOld = em.merge(refNacionalidadOld);
            }
            if (refNacionalidadNew != null && !refNacionalidadNew.equals(refNacionalidadOld)) {
                refNacionalidadNew.getMacUsuarioCollection().add(macUsuario);
                refNacionalidadNew = em.merge(refNacionalidadNew);
            }
            if (refCargoOld != null && !refCargoOld.equals(refCargoNew)) {
                refCargoOld.getMacUsuarioCollection().remove(macUsuario);
                refCargoOld = em.merge(refCargoOld);
            }
            if (refCargoNew != null && !refCargoNew.equals(refCargoOld)) {
                refCargoNew.getMacUsuarioCollection().add(macUsuario);
                refCargoNew = em.merge(refCargoNew);
            }
            if (refTipoContratoOld != null && !refTipoContratoOld.equals(refTipoContratoNew)) {
                refTipoContratoOld.getMacUsuarioCollection().remove(macUsuario);
                refTipoContratoOld = em.merge(refTipoContratoOld);
            }
            if (refTipoContratoNew != null && !refTipoContratoNew.equals(refTipoContratoOld)) {
                refTipoContratoNew.getMacUsuarioCollection().add(macUsuario);
                refTipoContratoNew = em.merge(refTipoContratoNew);
            }
            if (refGrupoOld != null && !refGrupoOld.equals(refGrupoNew)) {
                refGrupoOld.getMacUsuarioCollection().remove(macUsuario);
                refGrupoOld = em.merge(refGrupoOld);
            }
            if (refGrupoNew != null && !refGrupoNew.equals(refGrupoOld)) {
                refGrupoNew.getMacUsuarioCollection().add(macUsuario);
                refGrupoNew = em.merge(refGrupoNew);
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
                Integer id = macUsuario.getUsId();
                if (findMacUsuario(id) == null) {
                    throw new NonexistentEntityException("The macUsuario with id " + id + " no longer exists.");
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
            MacUsuario macUsuario;
            try {
                macUsuario = em.getReference(MacUsuario.class, id);
                macUsuario.getUsId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The macUsuario with id " + id + " no longer exists.", enfe);
            }
            InfCatalogo refPerfil = macUsuario.getRefPerfil();
            if (refPerfil != null) {
                refPerfil.getMacUsuarioCollection().remove(macUsuario);
                refPerfil = em.merge(refPerfil);
            }
            InfCatalogo refNacionalidad = macUsuario.getRefNacionalidad();
            if (refNacionalidad != null) {
                refNacionalidad.getMacUsuarioCollection().remove(macUsuario);
                refNacionalidad = em.merge(refNacionalidad);
            }
            InfCatalogo refCargo = macUsuario.getRefCargo();
            if (refCargo != null) {
                refCargo.getMacUsuarioCollection().remove(macUsuario);
                refCargo = em.merge(refCargo);
            }
            InfCatalogo refTipoContrato = macUsuario.getRefTipoContrato();
            if (refTipoContrato != null) {
                refTipoContrato.getMacUsuarioCollection().remove(macUsuario);
                refTipoContrato = em.merge(refTipoContrato);
            }
            MgiGrupo refGrupo = macUsuario.getRefGrupo();
            if (refGrupo != null) {
                refGrupo.getMacUsuarioCollection().remove(macUsuario);
                refGrupo = em.merge(refGrupo);
            }
            em.remove(macUsuario);
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

    public List<MacUsuario> findMacUsuarioEntities() {
        return findMacUsuarioEntities(true, -1, -1);
    }

    public List<MacUsuario> findMacUsuarioEntities(int maxResults, int firstResult) {
        return findMacUsuarioEntities(false, maxResults, firstResult);
    }

    private List<MacUsuario> findMacUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MacUsuario.class));
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

    public MacUsuario findMacUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MacUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMacUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MacUsuario> rt = cq.from(MacUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
