package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.AppInitializer;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).
                getActualTypeArguments()[0]);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void delete(ID id) throws Exception {
        session.delete(session.load(entity, id));
    }

    @Override
    public List<T> findAll() throws Exception {
        return session.createQuery("FROM " + entity.getName()).list();
        //return session.createNativeQuery("SELECT * from Customer",entity).list();

    }

    @Override
    public T find(ID id) throws Exception {
        return session.get(entity, id);
    }

    @Override
    public void save(T entity) throws Exception {
        session.save(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        session.merge(entity);
    }


}
