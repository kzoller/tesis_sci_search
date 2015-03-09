/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Controladores;

import GIISIC.Controladores.exceptions.NonexistentEntityException;
import GIISIC.Controladores.exceptions.PreexistingEntityException;
import GIISIC.Controladores.exceptions.RollbackFailureException;
import GIISIC.Entidades.InfCatalogo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import GIISIC.Entidades.MacUsuario;
import java.util.ArrayList;
import java.util.Collection;
import GIISIC.Entidades.MgiAsignarTitulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pruebas_ECV
 */
public class InfCatalogoJpaController1 implements Serializable {

    public InfCatalogoJpaController1(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfCatalogo infCatalogo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (infCatalogo.getMacUsuarioCollection() == null) {
            infCatalogo.setMacUsuarioCollection(new ArrayList<MacUsuario>());
        }
        if (infCatalogo.getMacUsuarioCollection1() == null) {
            infCatalogo.setMacUsuarioCollection1(new ArrayList<MacUsuario>());
        }
        if (infCatalogo.getMacUsuarioCollection2() == null) {
            infCatalogo.setMacUsuarioCollection2(new ArrayList<MacUsuario>());
        }
        if (infCatalogo.getMacUsuarioCollection3() == null) {
            infCatalogo.setMacUsuarioCollection3(new ArrayList<MacUsuario>());
        }
        if (infCatalogo.getMgiAsignarTituloCollection() == null) {
            infCatalogo.setMgiAsignarTituloCollection(new ArrayList<MgiAsignarTitulo>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<MacUsuario> attachedMacUsuarioCollection = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollectionMacUsuarioToAttach : infCatalogo.getMacUsuarioCollection()) {
                macUsuarioCollectionMacUsuarioToAttach = em.getReference(macUsuarioCollectionMacUsuarioToAttach.getClass(), macUsuarioCollectionMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection.add(macUsuarioCollectionMacUsuarioToAttach);
            }
            infCatalogo.setMacUsuarioCollection(attachedMacUsuarioCollection);
            Collection<MacUsuario> attachedMacUsuarioCollection1 = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection1MacUsuarioToAttach : infCatalogo.getMacUsuarioCollection1()) {
                macUsuarioCollection1MacUsuarioToAttach = em.getReference(macUsuarioCollection1MacUsuarioToAttach.getClass(), macUsuarioCollection1MacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection1.add(macUsuarioCollection1MacUsuarioToAttach);
            }
            infCatalogo.setMacUsuarioCollection1(attachedMacUsuarioCollection1);
            Collection<MacUsuario> attachedMacUsuarioCollection2 = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection2MacUsuarioToAttach : infCatalogo.getMacUsuarioCollection2()) {
                macUsuarioCollection2MacUsuarioToAttach = em.getReference(macUsuarioCollection2MacUsuarioToAttach.getClass(), macUsuarioCollection2MacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection2.add(macUsuarioCollection2MacUsuarioToAttach);
            }
            infCatalogo.setMacUsuarioCollection2(attachedMacUsuarioCollection2);
            Collection<MacUsuario> attachedMacUsuarioCollection3 = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection3MacUsuarioToAttach : infCatalogo.getMacUsuarioCollection3()) {
                macUsuarioCollection3MacUsuarioToAttach = em.getReference(macUsuarioCollection3MacUsuarioToAttach.getClass(), macUsuarioCollection3MacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection3.add(macUsuarioCollection3MacUsuarioToAttach);
            }
            infCatalogo.setMacUsuarioCollection3(attachedMacUsuarioCollection3);
            Collection<MgiAsignarTitulo> attachedMgiAsignarTituloCollection = new ArrayList<MgiAsignarTitulo>();
            for (MgiAsignarTitulo mgiAsignarTituloCollectionMgiAsignarTituloToAttach : infCatalogo.getMgiAsignarTituloCollection()) {
                mgiAsignarTituloCollectionMgiAsignarTituloToAttach = em.getReference(mgiAsignarTituloCollectionMgiAsignarTituloToAttach.getClass(), mgiAsignarTituloCollectionMgiAsignarTituloToAttach.getATId());
                attachedMgiAsignarTituloCollection.add(mgiAsignarTituloCollectionMgiAsignarTituloToAttach);
            }
            infCatalogo.setMgiAsignarTituloCollection(attachedMgiAsignarTituloCollection);
            em.persist(infCatalogo);
            for (MacUsuario macUsuarioCollectionMacUsuario : infCatalogo.getMacUsuarioCollection()) {
                InfCatalogo oldRefPerfilOfMacUsuarioCollectionMacUsuario = macUsuarioCollectionMacUsuario.getRefPerfil();
                macUsuarioCollectionMacUsuario.setRefPerfil(infCatalogo);
                macUsuarioCollectionMacUsuario = em.merge(macUsuarioCollectionMacUsuario);
                if (oldRefPerfilOfMacUsuarioCollectionMacUsuario != null) {
                    oldRefPerfilOfMacUsuarioCollectionMacUsuario.getMacUsuarioCollection().remove(macUsuarioCollectionMacUsuario);
                    oldRefPerfilOfMacUsuarioCollectionMacUsuario = em.merge(oldRefPerfilOfMacUsuarioCollectionMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection1MacUsuario : infCatalogo.getMacUsuarioCollection1()) {
                InfCatalogo oldRefNacionalidadOfMacUsuarioCollection1MacUsuario = macUsuarioCollection1MacUsuario.getRefNacionalidad();
                macUsuarioCollection1MacUsuario.setRefNacionalidad(infCatalogo);
                macUsuarioCollection1MacUsuario = em.merge(macUsuarioCollection1MacUsuario);
                if (oldRefNacionalidadOfMacUsuarioCollection1MacUsuario != null) {
                    oldRefNacionalidadOfMacUsuarioCollection1MacUsuario.getMacUsuarioCollection1().remove(macUsuarioCollection1MacUsuario);
                    oldRefNacionalidadOfMacUsuarioCollection1MacUsuario = em.merge(oldRefNacionalidadOfMacUsuarioCollection1MacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection2MacUsuario : infCatalogo.getMacUsuarioCollection2()) {
                InfCatalogo oldRefCargoOfMacUsuarioCollection2MacUsuario = macUsuarioCollection2MacUsuario.getRefCargo();
                macUsuarioCollection2MacUsuario.setRefCargo(infCatalogo);
                macUsuarioCollection2MacUsuario = em.merge(macUsuarioCollection2MacUsuario);
                if (oldRefCargoOfMacUsuarioCollection2MacUsuario != null) {
                    oldRefCargoOfMacUsuarioCollection2MacUsuario.getMacUsuarioCollection2().remove(macUsuarioCollection2MacUsuario);
                    oldRefCargoOfMacUsuarioCollection2MacUsuario = em.merge(oldRefCargoOfMacUsuarioCollection2MacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection3MacUsuario : infCatalogo.getMacUsuarioCollection3()) {
                InfCatalogo oldRefTipoContratoOfMacUsuarioCollection3MacUsuario = macUsuarioCollection3MacUsuario.getRefTipoContrato();
                macUsuarioCollection3MacUsuario.setRefTipoContrato(infCatalogo);
                macUsuarioCollection3MacUsuario = em.merge(macUsuarioCollection3MacUsuario);
                if (oldRefTipoContratoOfMacUsuarioCollection3MacUsuario != null) {
                    oldRefTipoContratoOfMacUsuarioCollection3MacUsuario.getMacUsuarioCollection3().remove(macUsuarioCollection3MacUsuario);
                    oldRefTipoContratoOfMacUsuarioCollection3MacUsuario = em.merge(oldRefTipoContratoOfMacUsuarioCollection3MacUsuario);
                }
            }
            for (MgiAsignarTitulo mgiAsignarTituloCollectionMgiAsignarTitulo : infCatalogo.getMgiAsignarTituloCollection()) {
                InfCatalogo oldRefTituloOfMgiAsignarTituloCollectionMgiAsignarTitulo = mgiAsignarTituloCollectionMgiAsignarTitulo.getRefTitulo();
                mgiAsignarTituloCollectionMgiAsignarTitulo.setRefTitulo(infCatalogo);
                mgiAsignarTituloCollectionMgiAsignarTitulo = em.merge(mgiAsignarTituloCollectionMgiAsignarTitulo);
                if (oldRefTituloOfMgiAsignarTituloCollectionMgiAsignarTitulo != null) {
                    oldRefTituloOfMgiAsignarTituloCollectionMgiAsignarTitulo.getMgiAsignarTituloCollection().remove(mgiAsignarTituloCollectionMgiAsignarTitulo);
                    oldRefTituloOfMgiAsignarTituloCollectionMgiAsignarTitulo = em.merge(oldRefTituloOfMgiAsignarTituloCollectionMgiAsignarTitulo);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInfCatalogo(infCatalogo.getCatId()) != null) {
                throw new PreexistingEntityException("InfCatalogo " + infCatalogo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfCatalogo infCatalogo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            InfCatalogo persistentInfCatalogo = em.find(InfCatalogo.class, infCatalogo.getCatId());
            Collection<MacUsuario> macUsuarioCollectionOld = persistentInfCatalogo.getMacUsuarioCollection();
            Collection<MacUsuario> macUsuarioCollectionNew = infCatalogo.getMacUsuarioCollection();
            Collection<MacUsuario> macUsuarioCollection1Old = persistentInfCatalogo.getMacUsuarioCollection1();
            Collection<MacUsuario> macUsuarioCollection1New = infCatalogo.getMacUsuarioCollection1();
            Collection<MacUsuario> macUsuarioCollection2Old = persistentInfCatalogo.getMacUsuarioCollection2();
            Collection<MacUsuario> macUsuarioCollection2New = infCatalogo.getMacUsuarioCollection2();
            Collection<MacUsuario> macUsuarioCollection3Old = persistentInfCatalogo.getMacUsuarioCollection3();
            Collection<MacUsuario> macUsuarioCollection3New = infCatalogo.getMacUsuarioCollection3();
            Collection<MgiAsignarTitulo> mgiAsignarTituloCollectionOld = persistentInfCatalogo.getMgiAsignarTituloCollection();
            Collection<MgiAsignarTitulo> mgiAsignarTituloCollectionNew = infCatalogo.getMgiAsignarTituloCollection();
            Collection<MacUsuario> attachedMacUsuarioCollectionNew = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollectionNewMacUsuarioToAttach : macUsuarioCollectionNew) {
                macUsuarioCollectionNewMacUsuarioToAttach = em.getReference(macUsuarioCollectionNewMacUsuarioToAttach.getClass(), macUsuarioCollectionNewMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollectionNew.add(macUsuarioCollectionNewMacUsuarioToAttach);
            }
            macUsuarioCollectionNew = attachedMacUsuarioCollectionNew;
            infCatalogo.setMacUsuarioCollection(macUsuarioCollectionNew);
            Collection<MacUsuario> attachedMacUsuarioCollection1New = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection1NewMacUsuarioToAttach : macUsuarioCollection1New) {
                macUsuarioCollection1NewMacUsuarioToAttach = em.getReference(macUsuarioCollection1NewMacUsuarioToAttach.getClass(), macUsuarioCollection1NewMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection1New.add(macUsuarioCollection1NewMacUsuarioToAttach);
            }
            macUsuarioCollection1New = attachedMacUsuarioCollection1New;
            infCatalogo.setMacUsuarioCollection1(macUsuarioCollection1New);
            Collection<MacUsuario> attachedMacUsuarioCollection2New = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection2NewMacUsuarioToAttach : macUsuarioCollection2New) {
                macUsuarioCollection2NewMacUsuarioToAttach = em.getReference(macUsuarioCollection2NewMacUsuarioToAttach.getClass(), macUsuarioCollection2NewMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection2New.add(macUsuarioCollection2NewMacUsuarioToAttach);
            }
            macUsuarioCollection2New = attachedMacUsuarioCollection2New;
            infCatalogo.setMacUsuarioCollection2(macUsuarioCollection2New);
            Collection<MacUsuario> attachedMacUsuarioCollection3New = new ArrayList<MacUsuario>();
            for (MacUsuario macUsuarioCollection3NewMacUsuarioToAttach : macUsuarioCollection3New) {
                macUsuarioCollection3NewMacUsuarioToAttach = em.getReference(macUsuarioCollection3NewMacUsuarioToAttach.getClass(), macUsuarioCollection3NewMacUsuarioToAttach.getUsId());
                attachedMacUsuarioCollection3New.add(macUsuarioCollection3NewMacUsuarioToAttach);
            }
            macUsuarioCollection3New = attachedMacUsuarioCollection3New;
            infCatalogo.setMacUsuarioCollection3(macUsuarioCollection3New);
            Collection<MgiAsignarTitulo> attachedMgiAsignarTituloCollectionNew = new ArrayList<MgiAsignarTitulo>();
            for (MgiAsignarTitulo mgiAsignarTituloCollectionNewMgiAsignarTituloToAttach : mgiAsignarTituloCollectionNew) {
                mgiAsignarTituloCollectionNewMgiAsignarTituloToAttach = em.getReference(mgiAsignarTituloCollectionNewMgiAsignarTituloToAttach.getClass(), mgiAsignarTituloCollectionNewMgiAsignarTituloToAttach.getATId());
                attachedMgiAsignarTituloCollectionNew.add(mgiAsignarTituloCollectionNewMgiAsignarTituloToAttach);
            }
            mgiAsignarTituloCollectionNew = attachedMgiAsignarTituloCollectionNew;
            infCatalogo.setMgiAsignarTituloCollection(mgiAsignarTituloCollectionNew);
            infCatalogo = em.merge(infCatalogo);
            for (MacUsuario macUsuarioCollectionOldMacUsuario : macUsuarioCollectionOld) {
                if (!macUsuarioCollectionNew.contains(macUsuarioCollectionOldMacUsuario)) {
                    macUsuarioCollectionOldMacUsuario.setRefPerfil(null);
                    macUsuarioCollectionOldMacUsuario = em.merge(macUsuarioCollectionOldMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollectionNewMacUsuario : macUsuarioCollectionNew) {
                if (!macUsuarioCollectionOld.contains(macUsuarioCollectionNewMacUsuario)) {
                    InfCatalogo oldRefPerfilOfMacUsuarioCollectionNewMacUsuario = macUsuarioCollectionNewMacUsuario.getRefPerfil();
                    macUsuarioCollectionNewMacUsuario.setRefPerfil(infCatalogo);
                    macUsuarioCollectionNewMacUsuario = em.merge(macUsuarioCollectionNewMacUsuario);
                    if (oldRefPerfilOfMacUsuarioCollectionNewMacUsuario != null && !oldRefPerfilOfMacUsuarioCollectionNewMacUsuario.equals(infCatalogo)) {
                        oldRefPerfilOfMacUsuarioCollectionNewMacUsuario.getMacUsuarioCollection().remove(macUsuarioCollectionNewMacUsuario);
                        oldRefPerfilOfMacUsuarioCollectionNewMacUsuario = em.merge(oldRefPerfilOfMacUsuarioCollectionNewMacUsuario);
                    }
                }
            }
            for (MacUsuario macUsuarioCollection1OldMacUsuario : macUsuarioCollection1Old) {
                if (!macUsuarioCollection1New.contains(macUsuarioCollection1OldMacUsuario)) {
                    macUsuarioCollection1OldMacUsuario.setRefNacionalidad(null);
                    macUsuarioCollection1OldMacUsuario = em.merge(macUsuarioCollection1OldMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection1NewMacUsuario : macUsuarioCollection1New) {
                if (!macUsuarioCollection1Old.contains(macUsuarioCollection1NewMacUsuario)) {
                    InfCatalogo oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario = macUsuarioCollection1NewMacUsuario.getRefNacionalidad();
                    macUsuarioCollection1NewMacUsuario.setRefNacionalidad(infCatalogo);
                    macUsuarioCollection1NewMacUsuario = em.merge(macUsuarioCollection1NewMacUsuario);
                    if (oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario != null && !oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario.equals(infCatalogo)) {
                        oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario.getMacUsuarioCollection1().remove(macUsuarioCollection1NewMacUsuario);
                        oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario = em.merge(oldRefNacionalidadOfMacUsuarioCollection1NewMacUsuario);
                    }
                }
            }
            for (MacUsuario macUsuarioCollection2OldMacUsuario : macUsuarioCollection2Old) {
                if (!macUsuarioCollection2New.contains(macUsuarioCollection2OldMacUsuario)) {
                    macUsuarioCollection2OldMacUsuario.setRefCargo(null);
                    macUsuarioCollection2OldMacUsuario = em.merge(macUsuarioCollection2OldMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection2NewMacUsuario : macUsuarioCollection2New) {
                if (!macUsuarioCollection2Old.contains(macUsuarioCollection2NewMacUsuario)) {
                    InfCatalogo oldRefCargoOfMacUsuarioCollection2NewMacUsuario = macUsuarioCollection2NewMacUsuario.getRefCargo();
                    macUsuarioCollection2NewMacUsuario.setRefCargo(infCatalogo);
                    macUsuarioCollection2NewMacUsuario = em.merge(macUsuarioCollection2NewMacUsuario);
                    if (oldRefCargoOfMacUsuarioCollection2NewMacUsuario != null && !oldRefCargoOfMacUsuarioCollection2NewMacUsuario.equals(infCatalogo)) {
                        oldRefCargoOfMacUsuarioCollection2NewMacUsuario.getMacUsuarioCollection2().remove(macUsuarioCollection2NewMacUsuario);
                        oldRefCargoOfMacUsuarioCollection2NewMacUsuario = em.merge(oldRefCargoOfMacUsuarioCollection2NewMacUsuario);
                    }
                }
            }
            for (MacUsuario macUsuarioCollection3OldMacUsuario : macUsuarioCollection3Old) {
                if (!macUsuarioCollection3New.contains(macUsuarioCollection3OldMacUsuario)) {
                    macUsuarioCollection3OldMacUsuario.setRefTipoContrato(null);
                    macUsuarioCollection3OldMacUsuario = em.merge(macUsuarioCollection3OldMacUsuario);
                }
            }
            for (MacUsuario macUsuarioCollection3NewMacUsuario : macUsuarioCollection3New) {
                if (!macUsuarioCollection3Old.contains(macUsuarioCollection3NewMacUsuario)) {
                    InfCatalogo oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario = macUsuarioCollection3NewMacUsuario.getRefTipoContrato();
                    macUsuarioCollection3NewMacUsuario.setRefTipoContrato(infCatalogo);
                    macUsuarioCollection3NewMacUsuario = em.merge(macUsuarioCollection3NewMacUsuario);
                    if (oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario != null && !oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario.equals(infCatalogo)) {
                        oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario.getMacUsuarioCollection3().remove(macUsuarioCollection3NewMacUsuario);
                        oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario = em.merge(oldRefTipoContratoOfMacUsuarioCollection3NewMacUsuario);
                    }
                }
            }
            for (MgiAsignarTitulo mgiAsignarTituloCollectionOldMgiAsignarTitulo : mgiAsignarTituloCollectionOld) {
                if (!mgiAsignarTituloCollectionNew.contains(mgiAsignarTituloCollectionOldMgiAsignarTitulo)) {
                    mgiAsignarTituloCollectionOldMgiAsignarTitulo.setRefTitulo(null);
                    mgiAsignarTituloCollectionOldMgiAsignarTitulo = em.merge(mgiAsignarTituloCollectionOldMgiAsignarTitulo);
                }
            }
            for (MgiAsignarTitulo mgiAsignarTituloCollectionNewMgiAsignarTitulo : mgiAsignarTituloCollectionNew) {
                if (!mgiAsignarTituloCollectionOld.contains(mgiAsignarTituloCollectionNewMgiAsignarTitulo)) {
                    InfCatalogo oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo = mgiAsignarTituloCollectionNewMgiAsignarTitulo.getRefTitulo();
                    mgiAsignarTituloCollectionNewMgiAsignarTitulo.setRefTitulo(infCatalogo);
                    mgiAsignarTituloCollectionNewMgiAsignarTitulo = em.merge(mgiAsignarTituloCollectionNewMgiAsignarTitulo);
                    if (oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo != null && !oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo.equals(infCatalogo)) {
                        oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo.getMgiAsignarTituloCollection().remove(mgiAsignarTituloCollectionNewMgiAsignarTitulo);
                        oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo = em.merge(oldRefTituloOfMgiAsignarTituloCollectionNewMgiAsignarTitulo);
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
                Integer id = infCatalogo.getCatId();
                if (findInfCatalogo(id) == null) {
                    throw new NonexistentEntityException("The infCatalogo with id " + id + " no longer exists.");
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
            InfCatalogo infCatalogo;
            try {
                infCatalogo = em.getReference(InfCatalogo.class, id);
                infCatalogo.getCatId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infCatalogo with id " + id + " no longer exists.", enfe);
            }
            Collection<MacUsuario> macUsuarioCollection = infCatalogo.getMacUsuarioCollection();
            for (MacUsuario macUsuarioCollectionMacUsuario : macUsuarioCollection) {
                macUsuarioCollectionMacUsuario.setRefPerfil(null);
                macUsuarioCollectionMacUsuario = em.merge(macUsuarioCollectionMacUsuario);
            }
            Collection<MacUsuario> macUsuarioCollection1 = infCatalogo.getMacUsuarioCollection1();
            for (MacUsuario macUsuarioCollection1MacUsuario : macUsuarioCollection1) {
                macUsuarioCollection1MacUsuario.setRefNacionalidad(null);
                macUsuarioCollection1MacUsuario = em.merge(macUsuarioCollection1MacUsuario);
            }
            Collection<MacUsuario> macUsuarioCollection2 = infCatalogo.getMacUsuarioCollection2();
            for (MacUsuario macUsuarioCollection2MacUsuario : macUsuarioCollection2) {
                macUsuarioCollection2MacUsuario.setRefCargo(null);
                macUsuarioCollection2MacUsuario = em.merge(macUsuarioCollection2MacUsuario);
            }
            Collection<MacUsuario> macUsuarioCollection3 = infCatalogo.getMacUsuarioCollection3();
            for (MacUsuario macUsuarioCollection3MacUsuario : macUsuarioCollection3) {
                macUsuarioCollection3MacUsuario.setRefTipoContrato(null);
                macUsuarioCollection3MacUsuario = em.merge(macUsuarioCollection3MacUsuario);
            }
            Collection<MgiAsignarTitulo> mgiAsignarTituloCollection = infCatalogo.getMgiAsignarTituloCollection();
            for (MgiAsignarTitulo mgiAsignarTituloCollectionMgiAsignarTitulo : mgiAsignarTituloCollection) {
                mgiAsignarTituloCollectionMgiAsignarTitulo.setRefTitulo(null);
                mgiAsignarTituloCollectionMgiAsignarTitulo = em.merge(mgiAsignarTituloCollectionMgiAsignarTitulo);
            }
            em.remove(infCatalogo);
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

    public List<InfCatalogo> findInfCatalogoEntities() {
        return findInfCatalogoEntities(true, -1, -1);
    }

    public List<InfCatalogo> findInfCatalogoEntities(int maxResults, int firstResult) {
        return findInfCatalogoEntities(false, maxResults, firstResult);
    }

    private List<InfCatalogo> findInfCatalogoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfCatalogo.class));
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

    public InfCatalogo findInfCatalogo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfCatalogo.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfCatalogoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfCatalogo> rt = cq.from(InfCatalogo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
